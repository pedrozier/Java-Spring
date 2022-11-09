package com.standart.jasperreport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.standart.jasperreport.models.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, Long> {

}
