package br.com.standard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.standard.model.Item;
import br.com.standard.repository.ItemRepository;

@RestController
@RequestMapping("item/")
public class ItemController {

    @Autowired
    ItemRepository repository;

    @PostMapping("save")
    public ResponseEntity saveItem(@RequestBody @Valid Item item) {
        return new ResponseEntity<>(repository.save(item), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Cacheable(value = "itemCache")
    @CacheEvict(value = "itemName")
    public ResponseEntity getItemByID(@PathVariable(value = "id") @Valid long id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

}
