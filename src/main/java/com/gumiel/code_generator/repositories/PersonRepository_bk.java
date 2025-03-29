package com.gumiel.code_generator.repositories;

import com.gumiel.code_generator.entities.Person;
import com.gumiel.code_generator.filters.PersonFilter_bk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository_bk extends JpaRepository<Person, Integer> {

  boolean existsByCodeAndActiveIsTrue(String code);

  List<Person> findByActiveTrue();

  Optional<Person> findByIdAndActiveIsTrue(Integer id);

  Page<Person> findAll(Pageable pageable);

  @Query("""
          SELECT P
          FROM Person P
          WHERE P.active = TRUE
          AND ( :#{#filter.id} IS NULL OR P.id= :#{#filter.id} )
          AND ( :#{#filter.lastaName} IS NULL OR lower(P.lastaName) LIKE lower(concat('%', :#{#filter.lastaName}, '%')) )
          AND ( :#{#filter.firstName} IS NULL OR lower(P.firstName) LIKE lower(concat('%', :#{#filter.firstName}, '%')) )
          AND ( :#{#filter.age} IS NULL OR P.age= :#{#filter.age} )
          AND ( :#{#filter.gender} IS NULL OR lower(P.gender) LIKE lower(concat('%', :#{#filter.gender}, '%')) )
          AND ( :#{#filter.birthdate} IS NULL OR P.birthdate= :#{#filter.birthdate} )
          AND ( :#{#filter.registrationDate} IS NULL OR P.registrationDate= :#{#filter.registrationDate} )
          AND ( :#{#filter.registrationTime} IS NULL OR P.registrationTime= :#{#filter.registrationTime} )
          AND ( :#{#filter.endDateTime} IS NULL OR P.endDateTime= :#{#filter.endDateTime} )
          AND (
            (:#{#filter.search} IS NULL OR :#{#filter.search} = '')
            OR lower(P.lastaName) LIKE lower(concat('%', :#{#filter.search}, '%'))
            OR lower(P.firstName) LIKE lower(concat('%', :#{#filter.search}, '%'))
            OR lower(P.gender) LIKE lower(concat('%', :#{#filter.search}, '%'))
          )
  """)
  Page<Person> pageable(PersonFilter_bk filter, Pageable pageable);
}
