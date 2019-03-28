package com.funkman.lunch.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer Sweet;
    @Column
    private Integer cheep;
    @Column
    private Integer distance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Lunch> lunches;

    public List<Lunch> getLunches() {
        return lunches;
    }

    public void setLunches(List<Lunch> lunches) {
        this.lunches = lunches;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSweet() {
        return Sweet;
    }

    public void setSweet(Integer sweet) {
        Sweet = sweet;
    }

    public Integer getCheep() {
        return cheep;
    }

    public void setCheep(Integer cheep) {
        this.cheep = cheep;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

}
