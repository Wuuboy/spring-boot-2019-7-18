package com.example.demo;

import com.example.demo.modle.ParkingLot;
import com.example.demo.repository.ParkingLotRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void should_return_parking_lots_when_get_page_and_pageSize() throws Exception {

        ParkingLot parkingLot = new ParkingLot("parkingLotA", 12, "zha");
        ParkingLot parkingLot1 = new ParkingLot("parkingLotB", 12, "zha");
        List<ParkingLot> expectResult = new ArrayList<ParkingLot>();
        expectResult.add(parkingLot);
        expectResult.add(parkingLot1);

        mvc.perform(get("/parkingLots?page=1&pageSize=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(expectResult, List.class)));
    }
    @Test
    public void should_return_update_count_when_update_capacity() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot = new ParkingLot("parkingLotB ",12,"zha");
        ParkingLot expectParkingLot = new ParkingLot("parkingLotB ",24,"zha");
        mvc.perform(put("/parkingLots",1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(parkingLot))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(expectParkingLot)));
    }

}
