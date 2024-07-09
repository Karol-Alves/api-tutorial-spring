package br.com.project.api.service;

import br.com.project.api.model.Message;
import br.com.project.api.model.Person;
import br.com.project.api.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Message message;

    @Autowired
    private Repository repository;

    //Create people
    public ResponseEntity<?> create(Person person){
        if (person.getName().equals("")){
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
        } else if (person.getName().equals("")){
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
}


