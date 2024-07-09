package br.com.project.api.controller;

import br.com.project.api.model.Person;
import br.com.project.api.repository.Repository;
import br.com.project.api.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = {"application/json"})
@Tag(name = "API Tutorial")
public class Controller {

    @Autowired
    private Repository repository;

    @Autowired
    private Service service;

    @Operation(summary = "Registra novas pessoas no banco", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "A requisição está com valores inválidos"),
            @ApiResponse(responseCode = "201", description = "Requisição concluída com sucesso.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register (@RequestBody Person person) {
        return service.create(person);
    }

    @Operation(summary = "Mostra todos os registros de pessoas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping()
    public ResponseEntity<?> select() {
        return service.select();
    }

    @Operation(summary = "Busca uma pessoa pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> selectById(@PathVariable int id) {
        return service.selectById(id);
    }

    @Operation(summary = "Atualiza uma pessoa existente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "A requisição está com valores inválidos"),
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Person person) {
        return service.update(person);
    }

    @Operation(summary = "Deleta uma pessoa pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @Operation(summary = "Ordena pessoas por nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/orderNames")
    public ResponseEntity<?> orderNames() {
        return service.orderNames();
    }

    @Operation(summary = "Ordena pessoas chamadas '' por idade", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/orderNames2")
    public ResponseEntity<?> orderNames2() {
        return service.orderNames2();
    }

    @Operation(summary = "Busca pessoas cujo nome contém ''", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/nameContains")
    public ResponseEntity<?> nameContains() {
        return service.nameContains();
    }

    @Operation(summary = "Busca pessoas cujo nome começa com ''", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/nameStartsWith")
    public ResponseEntity<?> nameStartsWith() {
        return service.nameStartsWith();
    }

    @Operation(summary = "Busca pessoas cujo nome termina com ''", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/nameEndsWith")
    public ResponseEntity<?> nameEndsWith() {
        return service.nameEndsWith();
    }

    @Operation(summary = "Soma das idades de todas as pessoas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/sumAge")
    public ResponseEntity<?> sumAge() {
        return service.sumAge();
    }

    @Operation(summary = "Busca pessoas com idade maior ou igual a 18", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição concluída com sucesso.")
    })
    @GetMapping("/ageGreaterOrEqual")
    public ResponseEntity<?> ageGreaterOrEqual() {
        return service.ageGreaterOrEqual();
    }

}
