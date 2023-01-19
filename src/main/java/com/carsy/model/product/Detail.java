package com.carsy.model.product;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Builder

@Entity
@Table(name = "details")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id", nullable = false, unique = true)
    private Long detailId;

    @Column(name = "type", length = 70)
    private String type;

    @Column(name = "name", length = 100)
    private String name;
}
