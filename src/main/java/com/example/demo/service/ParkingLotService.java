package com.example.demo.service;

import com.example.demo.modle.ParkingLot;
import com.example.demo.repository.ParkingLotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> getParkingLots(Integer page, Integer pageSize) {
        return parkingLotRepository.findAll()
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public ParkingLot updateParkingLot(Long id,ParkingLot parkingLot) {
        ParkingLot parkingLot1 = (ParkingLot) parkingLotRepository.findAllById(Collections.singleton(id));
        if (parkingLot1!=null){
            BeanUtils.copyProperties(parkingLot,parkingLot1);
        }
        return parkingLotRepository.saveAndFlush(parkingLot1);
    }
}
