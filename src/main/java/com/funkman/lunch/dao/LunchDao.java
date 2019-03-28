package com.funkman.lunch.dao;

import com.funkman.lunch.entity.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchDao extends JpaRepository<Lunch,Integer> {

}
