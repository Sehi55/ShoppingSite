package org.sparta.shopping.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.sparta.shopping.entity.Product;

@Getter
@Builder
public class ProductResponseDto {
    @Schema(description = "상품 ID")
    private Long productId;
    @Schema(description = "상품 이름")
    private String productName;
    @Schema(description = "상품 가격")
    private Integer productPrice;
    @Schema(description = "상품 재고 수량")
    private Integer productStock;

    public static ProductResponseDto of(Product product){
        return ProductResponseDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productStock(product.getStock())
                .build();
    }
}
