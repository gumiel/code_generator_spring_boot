package com.gumiel.code_generator.repositories;

import com.gumiel.code_generator.entities.Product;
import com.gumiel.code_generator.filters.ProductFilter_bk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository_bk extends JpaRepository<Product, Integer> {

  boolean existsByCodeAndActiveIsTrue(String code);

  List<Product> findByActiveTrue();

  Optional<Product> findByIdAndActiveIsTrue(Integer id);

  Page<Product> findAll(Pageable pageable);

  @Query("""
          SELECT P
          FROM Product P
          WHERE P.active = TRUE
          AND ( :#{#filter.id} IS NULL OR P.id= :#{#filter.id} )
          AND ( :#{#filter.code} IS NULL OR lower(P.code) LIKE lower(concat('%', :#{#filter.code}, '%')) )
          AND ( :#{#filter.name} IS NULL OR lower(P.name) LIKE lower(concat('%', :#{#filter.name}, '%')) )
          AND ( :#{#filter.description} IS NULL OR lower(P.description) LIKE lower(concat('%', :#{#filter.description}, '%')) )
          AND ( :#{#filter.approximateUnitPrice} IS NULL OR P.approximateUnitPrice= :#{#filter.approximateUnitPrice} )
          AND (
            (:#{#filter.search} IS NULL OR :#{#filter.search} = '')
            OR lower(P.code) LIKE lower(concat('%', :#{#filter.search}, '%'))
            OR lower(P.name) LIKE lower(concat('%', :#{#filter.search}, '%'))
            OR lower(P.description) LIKE lower(concat('%', :#{#filter.search}, '%'))
          )
  """)
  Page<Product> pageable(ProductFilter_bk filter, Pageable pageable);
}
