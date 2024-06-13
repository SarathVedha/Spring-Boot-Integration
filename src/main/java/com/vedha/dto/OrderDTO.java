package com.vedha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "OrderDTO", description = "Order Data Transfer Object")
public class OrderDTO {

    @Schema(description = "Order ID", accessMode = Schema.AccessMode.READ_ONLY)
    private String id = UUID.randomUUID().toString();

    @Schema(description = "Order Name")
    private String name;

    @Schema(description = "Order Description")
    private String description;

    @Schema(description = "Order Price")
    private Double price;

    @Schema(description = "Order Quantity")
    private Integer quantity;

    @Schema(description = "Order Status", accessMode = Schema.AccessMode.READ_ONLY)
    private String status;
}
