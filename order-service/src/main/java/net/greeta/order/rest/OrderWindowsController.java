package net.greeta.order.rest;

import net.greeta.order.domain.OrdersCountPerStoreByWindowsDTO;
import net.greeta.order.domain.OrdersRevenuePerStoreByWindowsDTO;
import net.greeta.order.service.OrdersWindowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/windows")
@Slf4j
public class OrderWindowsController {

    private OrdersWindowService ordersWindowService;

    public OrderWindowsController(OrdersWindowService ordersWindowService) {
        this.ordersWindowService = ordersWindowService;
    }


    @GetMapping("/windows/count/{order_type}")
    public List<OrdersCountPerStoreByWindowsDTO> getAllOrdersCountByWindowsType(
            @PathVariable("order_type") String orderType
    ) {
        return ordersWindowService.getOrdersCountWindowsByType(orderType);

    }

    @GetMapping("/windows/count")
    public List<OrdersCountPerStoreByWindowsDTO> getAllOrdersCountByWindows(
            @RequestParam(value = "from_time", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fromTime,
            @RequestParam(value = "to_time", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime toTime
    ) {
        log.info("fromTime : {} , toTime : {}", fromTime, toTime);
        if (fromTime != null && toTime != null) {
            return ordersWindowService.getAllOrdersCountByWindows(fromTime, toTime);
        }
        return ordersWindowService.getAllOrdersCountByWindows();

    }

    @GetMapping("/windows/revenue/{order_type}")
    public List<OrdersRevenuePerStoreByWindowsDTO> getAllOrdersRevenueByWindowsType(
            @PathVariable("order_type") String orderType
    ) {
        return ordersWindowService.getOrdersRevenueWindowsByType(orderType);

    }



}
