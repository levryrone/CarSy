package com.carsy.controller;

import com.carsy.controller.dto.LoginDto;
import com.carsy.exception.ProductException;
import com.carsy.exception.UserException;
import com.carsy.model.product.Car;
import com.carsy.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin
@RequestMapping("/cars")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/garage")
    public ModelAndView showAllCars() {
        List<Car> foundCars;
        try {
            foundCars = productService.findAllCars();
        } catch (ProductException e) {
            return new ModelAndView("index", "exception", e.getMessage());
        }
        return new ModelAndView("index","foundCars",foundCars);
    }

    @GetMapping("/favorites")
    public ModelAndView showFavoriteCars() {
        List<Car> foundCars;
        try {
            foundCars = productService.showFavorites();
        } catch (ProductException | UserException e) {
            return new ModelAndView("favoritesPage", "exception", e.getMessage());
        }
        return new ModelAndView("favoritesPage","foundCars",foundCars);
    }

    @PostMapping("/favorites/remove/{carId}")
    public ModelAndView removeCarFromFavorites(@PathVariable String carId) {
        try {
            productService.removeCarFromFavorite(Long.parseLong(carId));
        } catch (ProductException e) {
            return new ModelAndView("index", "exception", e.getMessage());
        }
        return new ModelAndView("index");
    }

    @PostMapping("/favorites/add/{carId}")
    public ModelAndView addCarToFavorites(@PathVariable String carId) {
        productService.AddCarToFavorites(Long.parseLong(carId));
        return new ModelAndView("index");
    }

    @GetMapping("/garage/{carId}")
    public ModelAndView showConcreteCar(@PathVariable String carId) {
        Car foundCar;
        try {
            foundCar = productService.findConcreteCarByCarId(Long.parseLong(carId));
        } catch (ProductException e) {
            return new ModelAndView("index", "exception", e.getMessage());
        }
        return new ModelAndView("carPage","foundCar", foundCar);
    }
}