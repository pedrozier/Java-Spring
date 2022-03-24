package com.standart.springproject.services;

import com.standart.ExternalClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

    @Autowired
    private ExternalClass externalClass;

    public void teste() {
        externalClass.teste();
    }

}
