package com.gumiel.code_generator.services;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.PersonDto_bk;
import com.gumiel.code_generator.entities.Person;
import com.gumiel.code_generator.filters.PersonFilter_bk;
import com.gumiel.code_generator.pojos.PersonPojo_bk;

import java.util.List;

public interface PersonService_bk {

  List<PersonPojo_bk> getAll();

  PersonPojo_bk create(PersonDto_bk dto);

  PersonPojo_bk update(Integer id, PersonDto_bk dto);

  PersonPojo_bk getById(Integer id);

  void delete(Integer id);

  void disabled(Integer id);

  PagePojo<PersonPojo_bk> pageable(Integer page, Integer size, String sortField, String sortOrder,
      PersonFilter_bk filter);

}
