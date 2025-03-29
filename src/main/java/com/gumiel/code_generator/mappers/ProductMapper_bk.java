package com.gumiel.code_generator.mappers;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.ProductDto_bk;
import com.gumiel.code_generator.entities.Product;
import com.gumiel.code_generator.pojos.ProductPojo_bk;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper_bk {

  public Product fromDtoToEntity(ProductDto_bk dto, Product entityFound) {

    Product entity = (entityFound != null) ?
            entityFound :
            new Product();

    entity.setCode( dto.getCode() );
    entity.setName( dto.getName() );
    entity.setDescription( dto.getDescription() );
    entity.setUnitMeasurementId( dto.getUnitMeasurementId() );
    entity.setApproximateUnitPrice( dto.getApproximateUnitPrice() );
    return entity;
  }

  public ProductPojo_bk fromEntityToPojo(Product entity) {
    ProductPojo_bk pojo = new ProductPojo_bk();
    pojo.setId( entity.getId() );
    pojo.setCode( entity.getCode() );
    pojo.setName( entity.getName() );
    pojo.setDescription( entity.getDescription() );
    pojo.setUnitMeasurementId( entity.getUnitMeasurementId() );
    pojo.setApproximateUnitPrice( entity.getApproximateUnitPrice() );
    return pojo;
  }

  public List<ProductPojo_bk> fromEntityListToPojoList(List<Product> entityList){
    return  entityList.stream()
            .map(this::fromEntityToPojo)
            .collect(Collectors.toList());
  }

  public PagePojo<ProductPojo_bk> toPagePojo(Page<Product> page) {
    PagePojo<ProductPojo_bk> dto = new PagePojo<>();

    dto.setContent(fromEntityListToPojoList(page.getContent()));
    dto.setLast(page.isLast());
    dto.setPageNumber(page.getNumber());
    dto.setPageSize(page.getSize());
    dto.setTotalPages(page.getTotalPages());
    dto.setTotalElements(page.getTotalElements());
    return dto;
  }

}
