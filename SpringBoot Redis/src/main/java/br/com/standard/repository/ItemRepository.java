package br.com.standard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.com.standard.model.Item;

@Repository
@EnableJpaRepositories
public interface ItemRepository extends JpaRepository<Item, Long> {

}
