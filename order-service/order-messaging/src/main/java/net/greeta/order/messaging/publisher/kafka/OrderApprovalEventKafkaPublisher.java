package net.greeta.order.messaging.publisher.kafka;

import net.greeta.order.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import net.greeta.order.kafka.producer.KafkaMessageHelper;
import net.greeta.order.kafka.producer.service.KafkaProducer;
import net.greeta.order.domain.config.OrderServiceConfigData;
import net.greeta.order.domain.outbox.model.approval.OrderApprovalEventPayload;
import net.greeta.order.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import net.greeta.order.domain.ports.output.message.publisher.restaurantapproval.RestaurantApprovalRequestMessagePublisher;
import net.greeta.order.messaging.mapper.OrderMessagingDataMapper;
import net.greeta.order.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class OrderApprovalEventKafkaPublisher implements RestaurantApprovalRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public OrderApprovalEventKafkaPublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                            KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer,
                                            OrderServiceConfigData orderServiceConfigData,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }


    @Override
    public void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                        BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback) {
        OrderApprovalEventPayload orderApprovalEventPayload =
                kafkaMessageHelper.getOrderEventPayload(orderApprovalOutboxMessage.getPayload(),
                        OrderApprovalEventPayload.class);

        String sagaId = orderApprovalOutboxMessage.getSagaId().toString();

        log.info("Received OrderApprovalOutboxMessage for order id: {} and saga id: {}",
                orderApprovalEventPayload.getOrderId(),
                sagaId);

        try {
            RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel =
                    orderMessagingDataMapper
                            .orderApprovalEventToRestaurantApprovalRequestAvroModel(sagaId,
                                    orderApprovalEventPayload);

            kafkaProducer.send(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                    sagaId,
                    restaurantApprovalRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                            restaurantApprovalRequestAvroModel,
                            orderApprovalOutboxMessage,
                            outboxCallback,
                            orderApprovalEventPayload.getOrderId(),
                            "RestaurantApprovalRequestAvroModel"));

            log.info("OrderApprovalEventPayload sent to kafka for order id: {} and saga id: {}",
                    restaurantApprovalRequestAvroModel.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending OrderApprovalEventPayload to kafka for order id: {} and saga id: {}," +
                    " error: {}", orderApprovalEventPayload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
