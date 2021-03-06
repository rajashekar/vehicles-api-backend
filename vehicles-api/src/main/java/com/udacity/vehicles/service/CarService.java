package com.udacity.vehicles.service;

import java.util.List;
import java.util.Optional;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;

import org.springframework.stereotype.Service;

/**
 * Implements the car service create, read, update or delete information about
 * vehicles, as well as gather related location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient maps;
    private final PriceClient pricing;

    public CarService(CarRepository repository, MapsClient maps, PriceClient pricing) {
        this.repository = repository;
        this.maps = maps;
        this.pricing = pricing;
    }

    /**
     * Gathers a list of all vehicles
     * 
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        List<Car> cars = repository.findAll();
        for (Car car : cars) {
            car.setPrice(pricing.getPrice(car.getId()));
            car.setLocation(maps.getAddress(car.getLocation()));
        }
        return cars;
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * 
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        Optional<Car> optionalCar = repository.findById(id);
        Car car = optionalCar.orElseThrow(CarNotFoundException::new);
        car.setPrice(pricing.getPrice(id));
        car.setLocation(maps.getAddress(car.getLocation()));
        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * 
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId()).map(carToBeUpdated -> {
                carToBeUpdated.setDetails(car.getDetails());
                carToBeUpdated.setLocation(car.getLocation());
                carToBeUpdated.setCondition(car.getCondition());
                carToBeUpdated.setModifiedAt(car.getModifiedAt());
                carToBeUpdated.setPrice(pricing.getPrice(car.getId()));
                carToBeUpdated.setLocation(maps.getAddress(car.getLocation()));
                return repository.save(carToBeUpdated);
            }).orElseThrow(CarNotFoundException::new);
        }

        Car carCreated = repository.save(car);
        carCreated.setPrice(pricing.getPrice(carCreated.getId()));
        carCreated.setLocation(maps.getAddress(carCreated.getLocation()));
        return carCreated;
    }

    /**
     * Deletes a given car by ID
     * 
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        Optional<Car> optionalCar = repository.findById(id);
        Car car = optionalCar.orElseThrow(CarNotFoundException::new);
        repository.delete(car);
    }
}
