package com.gumiel.code_generator.servicesImpls;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.ProductDto_bk;
import com.gumiel.code_generator.entities.*;
import com.gumiel.code_generator.filters.ProductFilter_bk;
import com.gumiel.code_generator.mappers.ProductMapper_bk;
import com.gumiel.code_generator.pojos.ProductPojo_bk;
import com.gumiel.code_generator.repositories.*;
import com.gumiel.code_generator.services.ProductService_bk;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class ProductServiceImpl_bk implements
        ProductService_bk {

  private final ProductRepository_bk productRepository_bk;
  private final ProductMapper_bk productMapper_bk;

  @Override
  public List<ProductPojo_bk> getAll() {
    return productMapper_bk.fromEntityListToPojoList(
            productRepository_bk.findByActiveTrue()
    );
  }

  @Override
  public ProductPojo_bk create(ProductDto_bk productDto_bk) {

    Product product = productMapper_bk.fromDtoToEntity(productDto_bk, null);

    return productMapper_bk.fromEntityToPojo(
            productRepository_bk.save(product)
    );
  }


  @Override
  public ProductPojo_bk update(Integer id, ProductDto_bk productDto_bk) {
    Product productFound = this.findProductById(id);

    Product product = productMapper_bk.fromDtoToEntity(productDto_bk, productFound);

    return productMapper_bk.fromEntityToPojo(
            productRepository_bk.save(product)
    );

  }

  @Override
  public ProductPojo_bk getById(Integer id) {
    return productMapper_bk.fromEntityToPojo(
            this.findProductById(id)
    );
  }


  @Override
  public void delete(Integer id) {
    Product product = this.findProductById(id);
    productRepository_bk.delete(product);
  }


  @Override
  public void disabled(Integer id) {
    Product product = this.findProductById(id);
    if (Boolean.TRUE.equals(product.getActive())) {
      product.setActive(false);
      productRepository_bk.save(product);
    } else {
      throw new RuntimeException("El identificador ya fue eliminado");
    }
  }


  @Override
  public PagePojo<ProductPojo_bk> pageable(Integer pageNumber, Integer pageSize, String sortField,
      String sortOrder, ProductFilter_bk filter) {

    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

    Page<Product> productPage = productRepository_bk.findAll(pageable);

    return productMapper_bk.toPagePojo(productPage);
  }

  private Product findProductById(Integer id) {
    return productRepository_bk.findById(id).orElseThrow(
        () -> new EntityNotFoundException("No fue encontrado el identificador"+ id.toString())
    );
  }

}
