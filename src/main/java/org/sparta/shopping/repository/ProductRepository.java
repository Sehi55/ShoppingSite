package org.sparta.shopping.repository;

import jakarta.persistence.LockModeType;
import org.sparta.shopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByIdAndDeletedAtIsNull(Long Id);

    Page<Product> findAllByDeletedAtIsNull(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
            "select p from Product  p "+
                    "where p.id =:id "+
            "and p.deletedAt IS null "
    )
    Optional<Product> findByIdAndDeletedAtisNullWithLock(Long id);
}
