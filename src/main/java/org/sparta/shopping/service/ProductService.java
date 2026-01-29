package org.sparta.shopping.service;

import lombok.RequiredArgsConstructor;
import org.sparta.shopping.dto.request.ProductRequestDto;
import org.sparta.shopping.dto.response.ProductResponseDto;
import org.sparta.shopping.entity.Product;
import org.sparta.shopping.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto request) {
        Product product = new Product(request.getName(), request.getStock(), request.getPrice());
        productRepository.save(product);

        return ProductResponseDto.of(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 수정: 존재하지 않는 상품 아이디 입니다."));
        product.updateProduct(request.getName(), request.getStock(), request.getPrice());
        return ProductResponseDto.of(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 삭제: 존재하지 않는 상품 아이디 입니다."));
        product.deleteProduct();
    }

    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findByIdAndDeletedAtIsNull(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 조회: 존재하지 않는 상품 아이디 입니다."));
        return ProductResponseDto.of(product);
    }

    public Page<Product> getProductList(Pageable pageable) {
        return productRepository.findAllByDeletedAtIsNull(pageable);

    }
}
