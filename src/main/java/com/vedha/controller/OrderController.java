package com.vedha.controller;

import com.vedha.dto.OrderDTO;
import com.vedha.service.OrderGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Order Controller")
public class OrderController {

    private final OrderGateway orderGateway;

    @Operation(summary = "Place Order", description = "Place Order")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @PostMapping(value = "/place", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE) // for xml support jackson-dataformat-xml
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {

        Message<OrderDTO> orderDTOMessage = orderGateway.placeOrder(orderDTO);
        return ResponseEntity.ok(orderDTOMessage.getPayload());
    }
}
