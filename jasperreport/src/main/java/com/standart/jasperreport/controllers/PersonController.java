package com.standart.jasperreport.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.standart.jasperreport.models.PersonModel;
import com.standart.jasperreport.repositories.PersonRepository;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonRepository repository;

    @GetMapping
    public ResponseEntity<List<PersonModel>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<PersonModel> find(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@RequestBody PersonModel personModel) {
        personModel.setCreationTime(new Date());
        repository.save(personModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable(value = "id") Long id,
            @RequestBody PersonModel personModel) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
