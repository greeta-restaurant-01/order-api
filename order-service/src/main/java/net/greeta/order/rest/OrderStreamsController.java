package net.greeta.order.rest;

import lombok.extern.slf4j.Slf4j;
import net.greeta.order.domain.AllOrdersCountPerStoreDTO;
import net.greeta.order.domain.OrderRevenueDTO;
import net.greeta.order.producer.OrdersMockDataProducer;
import net.greeta.order.producer.StoresMockDataProducer;
import net.greeta.order.service.OrderStreamService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/streams")
@Slf4j
public class OrderStreamsController {

    private OrderStreamService orderStreamService;

    private KafkaProducer<String, String> producer;

    public OrderStreamsController(KafkaProducer<String, String> producer, OrderStreamService orderStreamService) {
        this.producer = producer;
        this.orderStreamService = orderStreamService;
    }

    @GetMapping("/count/{order_type}")
    public ResponseEntity<?> ordersCount(
            @PathVariable("order_type") String orderType,
            @RequestParam(value = "location_id", required = false) String locationId
    ) {
        if (StringUtils.hasLength(locationId)) {
            return ResponseEntity.ok(orderStreamService.getOrdersCountByLocationId(orderType, locationId));
        } else {

            return ResponseEntity.ok(orderStreamService.getOrdersCount(orderType));

        }
    }

    @GetMapping("/count")
    public List<AllOrdersCountPerStoreDTO> allOrdersCount(
    ) {
        return orderStreamService.getAllOrdersCount();

    }

    @GetMapping("/revenue/{order_type}")
    public ResponseEntity<?> revenueByOrderType(
            @PathVariable("order_type") String orderType,
            @RequestParam(value = "location_id", required = false) String locationId
    ) {
        if (StringUtils.hasLength(locationId)) {
            return ResponseEntity.ok(orderStreamService.getRevenueByLocationId(orderType, locationId));
        } else {

            return ResponseEntity.ok(orderStreamService.revenueByOrderType(orderType));

        }
    }

    @GetMapping("/revenue")
    public List<OrderRevenueDTO> allRevenue() {
        return orderStreamService.allRevenue();
    }

    @PostMapping("/init-mock")
    public ResponseEntity<Void> initMock() {
        StoresMockDataProducer.main(producer);
        OrdersMockDataProducer.main(producer);
        return ResponseEntity.ok().build();
    }



}
