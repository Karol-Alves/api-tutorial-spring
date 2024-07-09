package br.com.project.api.service;

import br.com.project.api.model.Message;
import br.com.project.api.model.Person;
import br.com.project.api.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Message message;

    @Autowired
    private Repository repository;

    //Create people
    public ResponseEntity<?> create(Person person){
        if (person.getName().isEmpty()){
            message.setMessage("The name cannot be empty");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (person.getAge() < 0){
            message.setMessage("The age cannot be negative");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
        }
    }

    //Select prople (Read)
    public ResponseEntity<?> select(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    //Select people by id
    public ResponseEntity<?> selectById(int id){
        if (repository.countById(id) == 0){
            message.setMessage("No record found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
        }
    }

    //Update people data
    public ResponseEntity<?> update(Person person){
        if (repository.countById(person.getId())== 0){
            message.setMessage("No record found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (person.getName().isEmpty()){
            message.setMessage("The name cannot be empty");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (person.getAge() < 0){
            message.setMessage("The age cannot be negative");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        }
    }

    //Remove register
    public ResponseEntity<?> delete(int id){
        if (repository.countById(id) == 0){
            message.setMessage("No record found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            Person person = repository.findById(id);
            repository.delete(person);
            message.setMessage("Record deleted");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> orderNames() {
        List<Person> people = repository.findByOrderByName();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }


    public ResponseEntity<?> orderNames2() {
        List<Person> people = repository.findByNameOrderByAge("Ana");
        return new ResponseEntity<>(people, HttpStatus.OK);
    }


    public ResponseEntity<?> nameContains() {
        List<Person> people = repository.findByNameContaining("P");
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    public ResponseEntity<?> nameStartsWith() {
        List<Person> people = repository.findByNameStartsWith("P");
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    public ResponseEntity<?> nameEndsWith() {
        List<Person> people = repository.findByNameEndsWith("A");
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    public ResponseEntity<?> sumAge() {
        int sum = repository.sumAge();
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    public ResponseEntity<?> ageGreaterOrEqual() {
        List<Person> people = repository.ageGreaterOrEqual(18);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
}


