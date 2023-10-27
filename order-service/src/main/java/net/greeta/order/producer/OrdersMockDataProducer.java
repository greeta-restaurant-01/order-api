package net.greeta.order.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.greeta.order.domain.Order;
import net.greeta.order.domain.OrderLineItem;
import net.greeta.order.domain.OrderType;
import net.greeta.order.topology.OrdersTopology;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static net.greeta.order.producer.ProducerUtil.publishMessageSync;
import static java.lang.Thread.sleep;

@Slf4j
public class OrdersMockDataProducer {

    public static void main(String[] args) throws InterruptedException {
        main(ProducerUtil.staticProducer);
    }

    public static void main(KafkaProducer<String, String> producer) {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        publishOrders(producer, objectMapper, buildOrders());
        //publishBulkOrders(objectMapper);

        /**
         * To test grace period.
         * 1. Run the {@link #publishOrders(ObjectMapper, List)} function during the start of the minute.
         * 2. Wait until the next minute and run the {@link #publishOrders(ObjectMapper, List)}
         *      and then the {@link #publishOrdersToTestGrace(ObjectMapper, List)} function before the 15th second.
         *      - This should allow the aggregation to be added to the window before
         *
         */
        publishOrdersToTestGrace(producer, objectMapper, buildOrdersToTestGrace());


        //Future and Old Records
//        publishFutureRecords(objectMapper);
//        publishExpiredRecords(objectMapper);


    }

    private static void publishFutureRecords(KafkaProducer<String, String> producer, ObjectMapper objectMapper) {
        var localDateTime = LocalDateTime.now().plusDays(1);

        var newOrders = buildOrders()
                .stream()
                .map(order ->
                        new Order(order.orderId(),
                                order.locationId(),
                                order.finalAmount(),
                                order.orderType(),
                                order.orderLineItems(),
                                localDateTime))
                .toList();
        publishOrders(producer, objectMapper, newOrders);
    }

    private static void publishExpiredRecords(KafkaProducer<String, String> producer, ObjectMapper objectMapper) {

        var localDateTime = LocalDateTime.now().minusDays(1);

        var newOrders = buildOrders()
                .stream()
                .map(order ->
                        new Order(order.orderId(),
                                order.locationId(),
                                order.finalAmount(),
                                order.orderType(),
                                order.orderLineItems(),
                                localDateTime))
                .toList();
        publishOrders(producer, objectMapper, newOrders);

    }

    private static void publishOrdersForGracePeriod(KafkaProducer<String, String> producer, ObjectMapper objectMapper, List<Order> orders) {

        var localTime = LocalDateTime.now().toLocalTime();
        var modifiedTime = LocalTime.of(localTime.getHour(), localTime.getMinute(), 18);
        var localDateTime = LocalDateTime.now().with(modifiedTime);

        //With Grace Period
        //[general_orders_revenue_window]: , TotalRevenue[locationId=store_4567, runnuingOrderCount=1, runningRevenue=27.00]
        //[general_orders_revenue_window]: TotalRevenue[locationId=store_1234, runnuingOrderCount=1, runningRevenue=27.00]
        //[general_orders_revenue_window]:  TotalRevenue[locationId=store_4567, runnuingOrderCount=1, runningRevenue=27.00]
        //[general_orders_revenue_window]:  TotalRevenue[locationId=store_4567, runnuingOrderCount=1, runningRevenue=27.00]

        //Without Grace Period
        //[general_orders_revenue_window]: , TotalRevenue[locationId=store_4567, runnuingOrderCount=1, runningRevenue=27.00]
        //[general_orders_revenue_window]: TotalRevenue[locationId=store_1234, runnuingOrderCount=1, runningRevenue=27.00]
        //[general_orders_revenue_window]:  TotalRevenue[locationId=store_4567, runnuingOrderCount=1, runningRevenue=27.00]
        //[general_orders_revenue_window]:  TotalRevenue[locationId=store_4567, runnuingOrderCount=1, runningRevenue=27.00]



        var generalOrdersWithCustomTime = orders
                .stream()
                .filter(order -> order.orderType().equals(OrderType.GENERAL))
                .map(order ->
                        new Order(order.orderId(),
                                order.locationId(),
                                order.finalAmount(),
                                order.orderType(),
                                order.orderLineItems(),
                                localDateTime))
                .toList();

        var generalOrders = orders
                .stream()
                .filter(order -> order.orderType().equals(OrderType.GENERAL))
                .toList();

        publishOrders(producer, objectMapper, generalOrders);

        //orders with the timestamp as 18th second
        publishRecordsWithDelay(producer, generalOrdersWithCustomTime, localDateTime, objectMapper, 18);

    }

    private static void publishRecordsWithDelay(KafkaProducer<String, String> producer, List<Order> newOrders, LocalDateTime localDateTime, ObjectMapper objectMapper) {

        publishOrders(producer, objectMapper, newOrders);
    }

    private static void publishRecordsWithDelay(KafkaProducer<String, String> producer, List<Order> newOrders, LocalDateTime localDateTime, ObjectMapper objectMapper, int timeToPublish) {

        var flag = true;
        while (flag) {
            var dateTime = LocalDateTime.now();
            if (dateTime.toLocalTime().getMinute() == localDateTime.getMinute()
                    && dateTime.toLocalTime().getSecond() == timeToPublish) {
                System.out.printf("Publishing the record with delay ");
                publishOrders(producer, objectMapper, newOrders);
                flag = false;
            } else {
                System.out.println(" Current Time is  and the record will be published at the 16th second: " + dateTime);
                System.out.println("Record Date Time : " + localDateTime);
            }
        }
    }

    private static List<Order> buildOrdersForGracePeriod() {

        var orderItems = List.of(
                new OrderLineItem("Bananas", 2, new BigDecimal("2.00")),
                new OrderLineItem("Iphone Charger", 1, new BigDecimal("25.00"))
        );

        var orderItemsRestaurant = List.of(
                new OrderLineItem("Pizza", 2, new BigDecimal("12.00")),
                new OrderLineItem("Coffee", 1, new BigDecimal("3.00"))
        );

        var order1 = new Order(12345, "store_999",
                new BigDecimal("27.00"),
                OrderType.RESTAURANT,
                orderItems,
                LocalDateTime.parse("2023-01-06T18:50:21")
        );

        var order2 = new Order(54321, "store_999",
                new BigDecimal("15.00"),
                OrderType.RESTAURANT,
                orderItemsRestaurant,
                LocalDateTime.parse("2023-01-06T18:50:21")
        );

        var order3 = new Order(54321, "store_999",
                new BigDecimal("15.00"),
                OrderType.RESTAURANT,
                orderItemsRestaurant,
                LocalDateTime.parse("2023-01-06T18:50:22")
        );

        return List.of(
                order1,
                order2,
                order3
        );

    }

    private static List<Order> buildOrders() {
        var orderItems = List.of(
                new OrderLineItem("Bananas", 2, new BigDecimal("2.00")),
                new OrderLineItem("Iphone Charger", 1, new BigDecimal("25.00"))
        );

        var orderItemsRestaurant = List.of(
                new OrderLineItem("Pizza", 2, new BigDecimal("12.00")),
                new OrderLineItem("Coffee", 1, new BigDecimal("3.00"))
        );

        var order1 = new Order(12345, "store_1234",
                new BigDecimal("27.00"),
                OrderType.GENERAL,
                orderItems,
                LocalDateTime.now()
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        var order2 = new Order(54321, "store_1234",
                new BigDecimal("15.00"),
                OrderType.RESTAURANT,
                orderItemsRestaurant,
                LocalDateTime.now()
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        var order3 = new Order(12345, "store_4567",
                new BigDecimal("27.00"),
                OrderType.GENERAL,
                orderItems,
                LocalDateTime.now()
                //LocalDateTime.parse("2023-02-25T05:02:01")
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        var order4 = new Order(12345, "store_4567",
                new BigDecimal("27.00"),
                OrderType.RESTAURANT,
                orderItems,
                LocalDateTime.now()
                //LocalDateTime.parse("2023-02-25T05:02:01")
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        return List.of(
                order1,
                order2,
                order3,
                order4
        );
    }

    private static List<Order> buildOrdersToTestGrace() {
        var orderItems = List.of(
                new OrderLineItem("Bananas", 2, new BigDecimal("2.00")),
                new OrderLineItem("Iphone Charger", 1, new BigDecimal("25.00"))
        );

        var orderItemsRestaurant = List.of(
                new OrderLineItem("Pizza", 2, new BigDecimal("12.00")),
                new OrderLineItem("Coffee", 1, new BigDecimal("3.00"))
        );

        var order1 = new Order(12345, "store_1234",
                new BigDecimal("27.00"),
                OrderType.GENERAL,
                orderItems,
                LocalDateTime.parse("2023-02-27T05:40:58")
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        var order2 = new Order(54321, "store_1234",
                new BigDecimal("15.00"),
                OrderType.RESTAURANT,
                orderItemsRestaurant,
                LocalDateTime.parse("2023-02-27T05:40:58")
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        var order3 = new Order(12345, "store_4567",
                new BigDecimal("27.00"),
                OrderType.GENERAL,
                orderItems,
                //LocalDateTime.now()
                LocalDateTime.parse("2023-02-27T05:40:58")
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        var order4 = new Order(12345, "store_4567",
                new BigDecimal("27.00"),
                OrderType.RESTAURANT,
                orderItems,
                //LocalDateTime.now()
                LocalDateTime.parse("2023-02-27T05:40:58")
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        return List.of(
                order1,
                order2,
                order3,
                order4
        );
    }

    private static void publishBulkOrders(KafkaProducer<String, String> producer, ObjectMapper objectMapper) throws InterruptedException {

        int count = 0;
        while (count < 100) {
            var orders = buildOrders();
            publishOrders(producer, objectMapper, orders);
            sleep(1000);
            count++;
        }
    }

    private static void publishOrdersToTestGrace(KafkaProducer<String, String> producer, ObjectMapper objectMapper, List<Order> orders) {

        orders
                .forEach(order -> {
                    try {
                        var ordersJSON = objectMapper.writeValueAsString(order);
                        var recordMetaData = publishMessageSync(producer, OrdersTopology.ORDERS, order.orderId() + "", ordersJSON);
                        log.info("Published the order message : {} ", recordMetaData);
                    } catch (JsonProcessingException e) {
                        log.error("JsonProcessingException : {} ", e.getMessage(), e);
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        log.error("Exception : {} ", e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                });
    }

    private static void publishOrders(KafkaProducer<String, String> producer, ObjectMapper objectMapper, List<Order> orders) {

        orders
                .forEach(order -> {
                    try {
                        var ordersJSON = objectMapper.writeValueAsString(order);
                        var recordMetaData = publishMessageSync(producer, OrdersTopology.ORDERS, order.orderId() + "", ordersJSON);
                        log.info("Published the order message : {} ", recordMetaData);
                    } catch (JsonProcessingException e) {
                        log.error("JsonProcessingException : {} ", e.getMessage(), e);
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        log.error("Exception : {} ", e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                });
    }


}
