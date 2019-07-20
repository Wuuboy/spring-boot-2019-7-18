package com.example.demo.service;

import com.example.demo.exception.NotEnoughCapacityException;
import com.example.demo.modle.Order;
import com.example.demo.modle.ParkingLot;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public Order addOrder(Long id, Order order) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElse(null);
        if (parkingLot!=null && parkingLot.getCapacity()>0){
            order.setParkingLot(parkingLot);
            parkingLot.setCapacity(parkingLot.getCapacity()-1);
            order.setCreateTime(new Date().getTime());
            order.setOrderStatus("ACTIVE");
        }else{
            throw new NotEnoughCapacityException("停车场已经满");
        }
        return (Order) orderRepository.save(order);
    }

    public Order updateStatus(Long id) {
        Order order = (Order) orderRepository.findById(id).orElse(null);
        order.setOrderStatus("DESTROYED");
        ParkingLot parkingLot = order.getParkingLot();
        parkingLot.setCapacity(parkingLot.getCapacity()+1);
        return (Order) orderRepository.save(order);
    }
}
