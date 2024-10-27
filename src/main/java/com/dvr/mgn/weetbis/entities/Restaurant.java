package com.dvr.mgn.weetbis.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_kosher")
    private Boolean isKosher;

    @Column(name = "total_raters")
    private int totalRaters;

    @Column(name = "average_rating")
    private double averageRating;

    @ElementCollection
    @CollectionTable(name = "restaurant_tags", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "tag")
    private List<String> tags;
}
