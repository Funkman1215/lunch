package com.funkman.lunch.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch=FetchType.LAZY)
    private List<Lunch> lunches;

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

    public List<Lunch> getLunches() {
        return lunches;
    }

    public void setLunches(List<Lunch> lunches) {
        this.lunches = lunches;
    }
}
