package org.sparta.shopping.repository;

import org.sparta.shopping.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            "select o from Order o "+
                    "join fetch o.product "+
                    "where o.id = :orderId"
    )
    Optional<Order> findByIdWithProduct(@Param(value = "orderId") Long orderId);


    @Query(
            value = "select o from Order o "+
                    "join fetch o.product ",
            countQuery = "select count(o) from Order o"
    )
    Page<Order> findAllWithProduct(Pageable pageable);


}
