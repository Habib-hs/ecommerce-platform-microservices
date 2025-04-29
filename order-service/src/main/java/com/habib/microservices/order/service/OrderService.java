package com.habib.microservices.order.service;

import com.habib.microservices.order.client.InventoryClient;
import com.habib.microservices.order.dto.OrderRequest;
import com.habib.microservices.order.model.Order;
import com.habib.microservices.order.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
            var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

            if (isProductInStock) {
                Order order = new Order();
                order.setOrderNumber(UUID.randomUUID().toString());
                order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
                order.setSkuCode(orderRequest.skuCode());
                order.setQuantity(orderRequest.quantity());
                orderRepository.save(order);
            }else{
                throw new RuntimeException("PRODUCT with skuCode: " + orderRequest.skuCode() + " is not in stock");
            }
    }
}
