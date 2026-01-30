package org.sparta.shopping.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sparta.shopping.dto.common.ApiResponse;
import org.sparta.shopping.dto.response.OrderResponseDto;
import org.sparta.shopping.entity.Order;
import org.sparta.shopping.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "주문",
        description = "주문 생성, 주문 조회 API"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Operation(
            summary = "주문 생성",
            description = "상품 ID를 받아 주문을 생성합니다."
    )
    @PostMapping("/create/{productId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder(@PathVariable(value = "productId") Long productId){
        OrderResponseDto data = orderService.createOrder(productId);
        return ResponseEntity.ok(ApiResponse.success(data,"주문 생성에 성공하였습니다."));
    }

    @Operation(
            summary = "주문 조회",
            description = "주문 ID를 받아 주문을 조회합니다."
    )
    @GetMapping("/get/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrder(@PathVariable(value = "orderId") Long orderId){
        OrderResponseDto data = orderService.getOrder(orderId);
        return ResponseEntity.ok(ApiResponse.success(data,"주문 조회에 성공하였습니다."));
    }

    @Operation(
            summary = "주문 목록 조회",
            description = "page와 size를 받아 주문 목록을 조회합니다."
    )
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<Page<Order>>> getOrders(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Order> orders = orderService.getOrderList(pageable);
        return ResponseEntity.ok(ApiResponse.success(orders,"주문 목록 조회에 성공하였습니다."));
    }
}
