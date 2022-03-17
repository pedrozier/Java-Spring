package com.standart.springProject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarVehicleService implements VehicleService {

    private Logger log = LoggerFactory.getLogger(CarVehicleService.class);

    @Override
    public void startEngine() {
        log.info("Starting car engine...");
    }

    @Override
    public void move() {
        log.info("moving away...");
    }

    @Override
    public void stopEngine() {
        log.info("Stopping car...");
    }

}
