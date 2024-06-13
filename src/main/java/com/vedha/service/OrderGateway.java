package com.vedha.service;

import com.vedha.dto.OrderDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface OrderGateway {

    @Gateway(requestChannel = "orderChannel")
    Message<OrderDTO> placeOrder(OrderDTO orderDTO);
}
