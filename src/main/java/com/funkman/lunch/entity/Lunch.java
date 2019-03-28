package com.funkman.lunch.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Lunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Timestamp eatTime;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getEatTime() {
        return eatTime;
    }

    public void setEatTime(Timestamp eatTime) {
        this.eatTime = eatTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
