package com.example.demo;

import com.example.demo.exception.NotEnoughCapacityException;
import com.example.demo.modle.Order;
import com.example.demo.modle.ParkingLot;
import com.example.demo.service.OrderService;
import com.example.demo.service.ParkingLotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_order_when_parking_car_to_useful_parkingLot() throws Exception {
        Order order1  = new Order("20190720","C888888",new Date().getTime());
        Order order2  = new Order("20190720","C888888",new Date().getTime());
        order2.setId((long)1);
        ParkingLot parkingLot = new ParkingLot("ParkingLotA",23,"zha");
        parkingLot.setId((long) 1);

        order2.setParkingLot(parkingLot);

        given(orderService.addOrder((long) 1,order1))
                .willReturn(order2);

        mvc.perform(post("/orders/parkingLots/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(order1)))
                .andExpect(content().string(objectMapper.writeValueAsString(order2)));
    }

    @Test
    public void should_return_cahnged_order_when_order_status_is_distoyed() throws Exception {
        Order order  = new Order("20190720","C888888",new Date().getTime());
        order.setId((long) 1);
        order.setEndTime(new Date().getTime());
        ParkingLot parkingLot = new ParkingLot("ParkingLotA",23,"zha");
        parkingLot.setId((long) 1);


        given(orderService.updateStatus((long) 1))
                .willReturn(order);

        mvc.perform(post("/orders/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void should_return_exception_message_when_add_bad_order() throws Exception {
        Order order1  = new Order("20190720","C888888",new Date().getTime());
        Order order2  = new Order("20190720","C888888",new Date().getTime());
        order2.setId((long) 2);
        ParkingLot parkingLot = new ParkingLot("aaa", 10, "ccc");
        parkingLot.setId((long) 1);
        order2.setParkingLot(parkingLot);

        given(orderService.addOrder((long) 1,order1))
                .willThrow(new NotEnoughCapacityException("停车场已满"));

        mvc.perform(post("/orders/parkingLots/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(order1)))
                .andExpect(content().string("停车场已满"));
    }
}
