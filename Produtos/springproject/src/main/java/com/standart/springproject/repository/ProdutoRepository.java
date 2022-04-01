package com.standart.springproject.repository;

import com.standart.springproject.model.produto.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
