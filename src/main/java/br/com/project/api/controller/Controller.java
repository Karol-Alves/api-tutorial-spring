package br.com.project.api.controller;

import br.com.project.api.model.Client;
import br.com.project.api.model.Person;
import br.com.project.api.repository.Repository;
import br.com.project.api.service.Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repository repository;

    @Autowired
    private Service service;

    @PostMapping("/api")
    public ResponseEntity<?> register (@RequestBody Person person) {
        return service.create(person);
    }

    @GetMapping("/api")
    public ResponseEntity<?> select() {
        return service.select();
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<?> selectById(@PathVariable int id) {
        return service.selectById(id);
    }

    @PutMapping("/api")
    public ResponseEntity<?> update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/api/orderNames")
    public List<Person> orderNames(){
        return repository.findByOrderByName();
    }

    @GetMapping("/api/orderNames2")
    public List<Person> orderNames2(){
        return repository.findByNameOrderByAge("Ana");
    }

    @GetMapping("/api/nameContains")
    public List<Person> nameContains(){
        return repository.findByNameContaining("P");
    }

    @GetMapping("/api/nameStartsWith")
    public List<Person> nameStartsWith(){
        return repository.findByNameStartsWith("P");
    }

    @GetMapping("/api/nameEndsWith")
    public List<Person> nameEndsWith(){
        return repository.findByNameEndsWith("A");
    }

    @GetMapping("/api/sumAge")
    public int sumAge (){
        return repository.sumAge();
    }

    @GetMapping("/api/ageGreaterOrEqual")
    public List <Person> ageGreaterOrEqual(){
        return repository.ageGreaterOrEqual(18);
    }

    @GetMapping("/status")
    public ResponseEntity<?> status (){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public String message (){
        return "Hello World";
    }


    @GetMapping("/welcome")
    public String welcome (){
        return "Welcome to the API";
    }

    @GetMapping("/welcome/{name}")
    public String welcome (@PathVariable String name){
        return "Welcome to the API, " +name;
    }

    @PostMapping("/person")
    public Person person (@RequestBody Person person){
        return person;
    }

    @PostMapping("/client")
    public void client( @Valid @RequestBody Client client){

    }


}
