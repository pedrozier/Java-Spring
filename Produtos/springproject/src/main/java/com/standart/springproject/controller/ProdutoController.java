package com.standart.springproject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.standart.springproject.model.produto.Produto;
import com.standart.springproject.repository.ProdutoRepository;

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

@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtoList = produtoRepository.findAll();
        if (produtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Produto>>(produtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getOneProduto(@PathVariable(value = "id") long id) {

        Optional<Produto> produto0 = produtoRepository.findById(id);

        if (!produto0.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Produto>(produto0.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> saveProduto(@RequestBody @Valid Produto produto) {
        return new ResponseEntity<Produto>((Produto) produtoRepository.save(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable(value = "id") long id) {
        Optional<Produto> produto0 = produtoRepository.findById(id);
        if (!produto0.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            produtoRepository.delete(produto0.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable(value = "id") long id,
            @RequestBody @Valid Produto produto) {
        Optional<Produto> produto0 = produtoRepository.findById(id);
        if (!produto0.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            produto.setId(produto0.get().getId());
            return new ResponseEntity<Produto>((Produto) produtoRepository.save(produto), HttpStatus.OK);
        }
    }

}
