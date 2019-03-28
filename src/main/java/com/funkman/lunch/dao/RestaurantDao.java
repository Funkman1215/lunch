package com.funkman.lunch.dao;

import com.funkman.lunch.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDao extends JpaRepository<Restaurant,Integer> {
}
