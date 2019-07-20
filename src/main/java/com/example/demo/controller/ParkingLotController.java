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

    @GetMapping(value = "/parkingLots",params = {"page","pageSize"})
    public List<ParkingLot> getParkingLots(@RequestParam(name = "page")Integer page,@RequestParam("pageSize")Integer pageSize){
        return parkingLotService.getParkingLots(page,pageSize);
    }

    @PutMapping("/parkingLots/{id}")
    public ParkingLot updateParkingLot(@PathVariable("id")Long id,@RequestBody ParkingLot parkingLot){
        return parkingLotService.updateParkingLot(id,parkingLot);
    }

    @GetMapping("/parkingLots/{id}")
    public ParkingLot getParkingLot(@PathVariable("id")Long id){
        return parkingLotService.getParkingLot(id);
    }

    @DeleteMapping("/parkingLots/{id}")
    public void deleteParkingLot(@PathVariable("id")Long id) throws Exception {
        parkingLotService.deleteParkingLot(id);
    }

}
