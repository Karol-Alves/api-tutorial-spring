package br.com.project.api.repository;

import br.com.project.api.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Repository extends CrudRepository <Person, Integer> {

   Person findById(int id);

   List<Person> findByOrderByName();

   List<Person> findByNameOrderByAge(String name);

   List<Person> findByNameContaining(String term);

   List<Person> findByNameStartsWith(String term);

   List<Person> findByNameEndsWith(String term);

   @Query (value = "SELECT SUM(age) FROM people", nativeQuery = true)
    int sumAge();

   @Query (value = "SELECT * FROM people WHERE age >= :age", nativeQuery = true)
   List<Person> ageGreaterOrEqual(int age);


   int countById (int id);

}
