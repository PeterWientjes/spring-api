package io.trackbee.test.service;

import io.trackbee.test.controller.request.OrderRequest;
import io.trackbee.test.domain.Order;
import io.trackbee.test.domain.Store;
import io.trackbee.test.mapper.OrderMapper;
import io.trackbee.test.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    /**
     * Saves given order for given store
     *
     */
    public void placeOrder(Store store, OrderRequest orderRequest) {
        Order order = OrderMapper.orderRequestToOrder(orderRequest);
        order.setStore(store);

        this.orderRepository.save(order);
    }
}
