package com.gumiel.code_generator.servicesImpls;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.PersonDto_bk;
import com.gumiel.code_generator.entities.*;
import com.gumiel.code_generator.filters.PersonFilter_bk;
import com.gumiel.code_generator.mappers.PersonMapper_bk;
import com.gumiel.code_generator.pojos.PersonPojo_bk;
import com.gumiel.code_generator.repositories.*;
import com.gumiel.code_generator.services.PersonService_bk;
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
public class PersonServiceImpl_bk implements
        PersonService_bk {

  private final PersonRepository_bk personRepository_bk;
  private final PersonMapper_bk personMapper_bk;

  @Override
  public List<PersonPojo_bk> getAll() {
    return personMapper_bk.fromEntityListToPojoList(
            personRepository_bk.findByActiveTrue()
    );
  }

  @Override
  public PersonPojo_bk create(PersonDto_bk personDto_bk) {

    Person person = personMapper_bk.fromDtoToEntity(personDto_bk, null);

    return personMapper_bk.fromEntityToPojo(
            personRepository_bk.save(person)
    );
  }


  @Override
  public PersonPojo_bk update(Integer id, PersonDto_bk personDto_bk) {
    Person personFound = this.findPersonById(id);

    Person person = personMapper_bk.fromDtoToEntity(personDto_bk, personFound);

    return personMapper_bk.fromEntityToPojo(
            personRepository_bk.save(person)
    );

  }

  @Override
  public PersonPojo_bk getById(Integer id) {
    return personMapper_bk.fromEntityToPojo(
            this.findPersonById(id)
    );
  }


  @Override
  public void delete(Integer id) {
    Person person = this.findPersonById(id);
    personRepository_bk.delete(person);
  }


  @Override
  public void disabled(Integer id) {
    Person person = this.findPersonById(id);
    if (Boolean.TRUE.equals(person.getActive())) {
      person.setActive(false);
      personRepository_bk.save(person);
    } else {
      throw new RuntimeException("El identificador ya fue eliminado");
    }
  }


  @Override
  public PagePojo<PersonPojo_bk> pageable(Integer pageNumber, Integer pageSize, String sortField,
      String sortOrder, PersonFilter_bk filter) {

    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

    Page<Person> personPage = personRepository_bk.findAll(pageable);

    return personMapper_bk.toPagePojo(personPage);
  }

  private Person findPersonById(Integer id) {
    return personRepository_bk.findById(id).orElseThrow(
        () -> new EntityNotFoundException("No fue encontrado el identificador"+ id.toString())
    );
  }

}
