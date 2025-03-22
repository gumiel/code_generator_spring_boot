package com.gumiel.code_generator.mappers;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.PersonDto_bk;
import com.gumiel.code_generator.entities.Person;
import com.gumiel.code_generator.pojos.PersonPojo_bk;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper_bk {

  public Person fromDtoToEntity(PersonDto_bk dto, Person entityFound) {

    Person entity = (entityFound != null) ?
            entityFound :
            new Person();

    entity.setLastaName( dto.getLastaName() );
    entity.setFirstName( dto.getFirstName() );
    entity.setAge( dto.getAge() );
    entity.setGender( dto.getGender() );
    entity.setDisability( dto.getDisability() );
    entity.setBirthdate( dto.getBirthdate() );
    entity.setRegistrationDate( dto.getRegistrationDate() );
    entity.setRegistrationTime( dto.getRegistrationTime() );
    entity.setEndDateTime( dto.getEndDateTime() );
    entity.setActive( dto.getActive() );
    return entity;
  }

  public PersonPojo_bk fromEntityToPojo(Person entity) {
    PersonPojo_bk pojo = new PersonPojo_bk();
    pojo.setId( entity.getId() );
    pojo.setLastaName( entity.getLastaName() );
    pojo.setFirstName( entity.getFirstName() );
    pojo.setAge( entity.getAge() );
    pojo.setGender( entity.getGender() );
    pojo.setDisability( entity.getDisability() );
    pojo.setBirthdate( entity.getBirthdate() );
    pojo.setRegistrationDate( entity.getRegistrationDate() );
    pojo.setRegistrationTime( entity.getRegistrationTime() );
    pojo.setEndDateTime( entity.getEndDateTime() );
    pojo.setActive( entity.getActive() );
    return pojo;
  }

  public List<PersonPojo_bk> fromEntityListToPojoList(List<Person> entityList){
    return  entityList.stream()
            .map(this::fromEntityToPojo)
            .collect(Collectors.toList());
  }

  public PagePojo<PersonPojo_bk> toPagePojo(Page<Person> page) {
    PagePojo<PersonPojo_bk> dto = new PagePojo<>();

    dto.setContent(fromEntityListToPojoList(page.getContent()));
    dto.setLast(page.isLast());
    dto.setPageNumber(page.getNumber());
    dto.setPageSize(page.getSize());
    dto.setTotalPages(page.getTotalPages());
    dto.setTotalElements(page.getTotalElements());
    return dto;
  }

}
