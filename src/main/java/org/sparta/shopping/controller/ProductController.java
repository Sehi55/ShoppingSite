package org.sparta.shopping.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.shopping.dto.common.ApiResponse;
import org.sparta.shopping.dto.request.ProductRequestDto;
import org.sparta.shopping.dto.response.ProductResponseDto;
import org.sparta.shopping.entity.Product;
import org.sparta.shopping.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "상품", description = "상품 등록/수정/삭제/조회 api")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "상품 등록",
            description = "상품 정보를 받아 상품을 신규 등록 합니다."
    )
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ProductResponseDto>> addProduct(@Valid @RequestBody ProductRequestDto request){
        ProductResponseDto data = productService.addProduct(request);
        return ResponseEntity.ok(ApiResponse.success(data,"상품 등록에 성공하였습니다."));
    }

    @Operation(
            summary = "상품 수정",
            description = "상품 정보를 받아 상품 정보를 수정합니다."
    )
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductRequestDto request){
        ProductResponseDto data = productService.updateProduct(productId,request);
        return ResponseEntity.ok(ApiResponse.success(data,"상품 수정에 성공하였습니다."));
    }

    @Operation(
            summary = "상품 삭제",
            description = "상품 ID를 받아 상품 레코드에 삭제 날짜를 추가합니다."
    )
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(null,"상품 삭제에 성공하였습니다."));
    }

    @Operation(
            summary = "상품 조회",
            description = "상품 ID를 받아 상품 정보를 조회합니다."
    )
    @GetMapping("/get/{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable Long productId){
        ProductResponseDto data = productService.getProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(data,"상품 조회에 성공하였습니다."));
    }

    @Operation(
            summary = "상품 리스트 조회",
            description = "페이지 정보를 받아 상품 리스트를 조회합니다."
    )
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<Page<Product>>> getProductList(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        Page<Product> products = productService.getProductList(pageable);
        return ResponseEntity.ok(ApiResponse.success(products,"상품 리스트 조회에 성공하였습니다."));
    }

}
