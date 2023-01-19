package com.carsy.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Builder

@Entity
@Table(name = "favorites_list")
public class Favorite {

    @Id
    @Column(name = "car_id", nullable = false)
    private Long carId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
