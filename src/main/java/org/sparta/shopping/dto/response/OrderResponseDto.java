package org.sparta.shopping.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.sparta.shopping.entity.Order;
import org.sparta.shopping.entity.Product;

import java.time.Instant;

@Getter
@Builder
public class OrderResponseDto {
    @Schema(description = "주문 ID")
    private Long orderId;
    @JsonUnwrapped
    private ProductResponseDto data;
    @Schema(description = "주문 생성 일자(utc)")
    private Instant createdAt;


    public static OrderResponseDto of(Order order, Product product){
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .data(
                        ProductResponseDto.of(product)
                )
                .createdAt(order.getCreatedAt())
                .build();
    }
}
