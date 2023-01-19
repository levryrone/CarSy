package com.carsy.service.impl;

import com.carsy.exception.CarNotFoundException;
import com.carsy.exception.FavoritesNotFoundException;
import com.carsy.model.product.Car;
import com.carsy.model.product.Favorite;
import com.carsy.repository.product.CarRepository;
import com.carsy.repository.product.FavoriteRepository;
import com.carsy.service.ProductService;
import com.carsy.util.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CarRepository carRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserDetailsImpl userDetails;

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAllCars()
                .orElseThrow(() -> new CarNotFoundException("Car for given criteria is not found"));
    }

    @Override
    public List<Car> showFavorites() {
        List<Favorite> favoriteList = this.findAllFavoritesByUserId();
        List<Car> foundCars = new ArrayList<>();
        for(Favorite favorite : favoriteList) {
            foundCars.add(this.findConcreteCarByCarId(favorite.getCarId()));
        }
        return foundCars;
    }

    @Override
    public void removeCarFromFavorite(Long carId) {
        carRepository.removeCarByCarId(carId)
                .orElseThrow(() -> new CarNotFoundException("Car for given criteria is not found"));
    }

    private List<Favorite> findAllFavoritesByUserId() {
        return favoriteRepository.findAllByUserId(userDetails.getUserId())
                .orElseThrow(() -> new FavoritesNotFoundException(
                        String.format("%s doesn't have a favorite car yet", userDetails.getUsername())));
    }

    public Car findConcreteCarByCarId(Long carId) {
        return carRepository.findCarByCarId(carId)
                .orElseThrow(() -> new CarNotFoundException("Car for given criteria is not found"));
    }

    @Override
    public void AddCarToFavorites(Long carId) {
        Favorite newFavorite = Favorite.builder()
                .carId(carId)
                .userId(userDetails.getUserId())
                .build();
        favoriteRepository.save(newFavorite);
    }
}
