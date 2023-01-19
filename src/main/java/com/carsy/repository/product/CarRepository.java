package com.carsy.repository.product;

import com.carsy.model.product.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findCarByCarId(Long carId);

    @Query(nativeQuery = true, value = "SELECT * FROM cars")
    Optional<List<Car>> findAllCars();

    Optional<Void> removeCarByCarId(Long carId);
}
