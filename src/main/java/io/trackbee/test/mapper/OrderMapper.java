package io.trackbee.test.mapper;

import io.trackbee.test.controller.request.OrderRequest;
import io.trackbee.test.domain.Order;

public class OrderMapper {
    public static Order orderRequestToOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setId(orderRequest.getId());
        order.setPrice(orderRequest.getPrice());
        order.setTimestamp(orderRequest.getTimestamp());

        return order;
    }
}
