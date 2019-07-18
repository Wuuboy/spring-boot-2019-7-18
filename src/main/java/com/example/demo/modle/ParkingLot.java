package com.example.demo.modle;

import javax.persistence.*;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    private  Integer capacity;
    private String location;

    public ParkingLot() {
    }

    public ParkingLot(String name, Integer capacity, String location) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }
}
