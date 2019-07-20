package com.example.demo.modle;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;
    private String carNumber;
    private Long createTime;
    private Long endTime;
    private String orderStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private ParkingLot parkingLot;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Order() {
    }

    public Order(String orderNumber, String carNumber, Long createTime) {
        this.orderNumber = orderNumber;
        this.carNumber = carNumber;
        this.createTime = createTime;
    }

    public Order(String orderNumber, String carNumber, Long createTime, Long endTime, String orderStatus) {
        this.orderNumber = orderNumber;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
    }

    public Order(String orderNumber, String carNumber, Long createTime, Long endTime, String orderStatus, ParkingLot parkingLot) {
        this.orderNumber = orderNumber;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
        this.parkingLot = parkingLot;
    }
}
