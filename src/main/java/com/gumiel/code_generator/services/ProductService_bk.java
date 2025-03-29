package com.gumiel.code_generator.services;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.ProductDto_bk;
import com.gumiel.code_generator.entities.Product;
import com.gumiel.code_generator.filters.ProductFilter_bk;
import com.gumiel.code_generator.pojos.ProductPojo_bk;

import java.util.List;

public interface ProductService_bk {

  List<ProductPojo_bk> getAll();

  ProductPojo_bk create(ProductDto_bk dto);

  ProductPojo_bk update(Integer id, ProductDto_bk dto);

  ProductPojo_bk getById(Integer id);

  void delete(Integer id);

  void disabled(Integer id);

  PagePojo<ProductPojo_bk> pageable(Integer page, Integer size, String sortField, String sortOrder,
      ProductFilter_bk filter);

}
