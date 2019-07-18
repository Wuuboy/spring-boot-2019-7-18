package com.example.demo.repository;

import com.example.demo.modle.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {
}
