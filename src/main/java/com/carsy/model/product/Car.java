package com.carsy.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Builder

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false, unique = true)
    private Long carId;

    @Column(name = "production_date", nullable = false)
    private Date productionDate;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "cars_details",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id")
    )
    private List<Detail> details = new ArrayList<>();
}
