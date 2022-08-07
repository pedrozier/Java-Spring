package com.standard.springhateoas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.standard.springhateoas.models.Product;
import com.standard.springhateoas.repositories.ProductRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("products")
    public ResponseEntity<List<Product>> findAllProducts() {

        List<Product> products = productRepository.findAll();

        if (!products.isEmpty()) {
            for (Product product : products) {
                long id = product.getId();

                product.add(linkTo(methodOn(ProductController.class).findProductById(id)).withSelfRel());
            }
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable(value = "id") long id) {
        Optional<Product> productO = productRepository.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productO.get().add(linkTo(methodOn(ProductController.class).findAllProducts()).withRel("Products List"));
        return new ResponseEntity<>(productO.get(), HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> saveProduct(@PathVariable(value = "id") long id, @RequestBody Product product) {
        Optional<Product> productO = productRepository.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productRepository.save(productO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") long id) {
        Optional<Product> productO = productRepository.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productRepository.delete(productO.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
