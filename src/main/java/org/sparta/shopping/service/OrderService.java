package org.sparta.shopping.service;

import lombok.RequiredArgsConstructor;
import org.sparta.shopping.dto.response.OrderResponseDto;
import org.sparta.shopping.entity.Order;
import org.sparta.shopping.entity.Product;
import org.sparta.shopping.repository.OrderRepository;
import org.sparta.shopping.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto createOrder(Long productId){
        Product product = productRepository.findByIdAndDeletedAtisNullWithLock(productId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품 아이디 입니다."));

        Order order = new Order(product);
        orderRepository.save(order);
        product.reduceStock();
        return OrderResponseDto.of(order, product);
    }
    public OrderResponseDto getOrder(Long orderId){
        Order order = orderRepository.findByIdWithProduct(orderId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 주문 아이디 입니다."));
        return OrderResponseDto.of(order, order.getProduct());
    }

    public Page<Order> getOrderList(Pageable pageable){
        return orderRepository.findAllWithProduct(pageable);
    }
}
