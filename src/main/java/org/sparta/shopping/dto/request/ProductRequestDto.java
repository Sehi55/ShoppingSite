package org.sparta.shopping.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    @NotBlank
    @Schema(description = "상품명")
    private String name;
    @NotNull
    @PositiveOrZero
    @Schema(description = "재고수량")
    private int stock;
    @NotNull
    @PositiveOrZero
    @Schema(description = "가격")
    private int price;
}
