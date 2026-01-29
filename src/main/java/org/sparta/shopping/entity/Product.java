package org.sparta.shopping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer stock;
    private Integer price;

    @UpdateTimestamp
    private Instant updatedAt;
    private Instant deletedAt;
    @CreationTimestamp
    private Instant createdAt;

    public Product(String name, Integer stock, Integer price){
        this.name = name;
        this.stock = stock;
        this.price= price;
    }

    public Product() {
    }

    public void updateProduct(String name, Integer stock, Integer price){
        if(this.deletedAt!=null)
            throw new IllegalStateException("이미 삭제된 상품입니다");

        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void deleteProduct(){
        if(this.deletedAt!=null)
            throw new IllegalStateException("이미 삭제된 상품입니다");
        this.deletedAt = Instant.now();
    }

    public void reduceStock(){
        if(this.stock<=0){
            throw new IllegalStateException("재고가 없어 구매할 수 없습니다.");
        }
        this.stock--;
    }
}
