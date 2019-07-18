package com.example.demo.controller;

import com.example.demo.modle.ParkingLot;
import com.example.demo.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping(value = "/parkinglots",params = {"page","pageSize"})
    public List<ParkingLot> getParkingLots(@PathVariable(name = "page")Integer page,@PathVariable("pageSize")Integer pageSize){
        return parkingLotService.getParkingLots(page,pageSize);
    }

    @PutMapping("/parkingLots/{id}")
    public ParkingLot updateParkingLot(@PathVariable("id")Long id,@RequestBody ParkingLot parkingLot){
        return parkingLotService.updateParkingLot(id,parkingLot);
    }

}
