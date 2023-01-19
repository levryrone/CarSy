package com.carsy.service;

import com.carsy.model.product.Car;

import java.util.List;

/**
 * Service that provide methods for working with car collections
 */
public interface ProductService {

    /**
     * Method for displaying all cars
     *
     * @return list of all cars
     */
    List<Car> findAllCars();

    /**
     * Method for displaying favorite cars of current user
     *
     * @return list of found cars
     */
    List<Car> showFavorites();

    /**
     * Method for removing chosen car from favorite list of current user
     *
     * @param carId id of removed car
     */
    void removeCarFromFavorite(Long carId);

    /**
     * Method for searching Car by Car ID
     *
     * @param carId id of searched car
     * @return found Car
     */
    Car findConcreteCarByCarId(Long carId);

    /**
     * Method for adding existing car to users favorite list
     *
     * @param carId id of added car
     */
    void AddCarToFavorites(Long carId);
}
