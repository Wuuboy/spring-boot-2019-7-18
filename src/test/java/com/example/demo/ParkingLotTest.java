package com.example.demo;

import com.example.demo.modle.ParkingLot;
import com.example.demo.repository.ParkingLotRepository;
import com.example.demo.service.ParkingLotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    private ParkingLotService parkingLotService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_parking_lots_when_get_page_and_pageSize() throws Exception {

        ParkingLot parkingLot = new ParkingLot("parkingLotA", 12, "zha");
        List<ParkingLot> expectResult = new ArrayList<ParkingLot>();
       for (int i=0;i<3;i++){
           expectResult.add(parkingLot);
       }

       given(parkingLotService.getParkingLots(1,2))
               .willReturn(expectResult);

        mvc.perform(get("/parkingLots?page=1&pageSize=2"))
                .andExpect(content().string(objectMapper.writeValueAsString(expectResult)));
    }
    @Test
    public void should_return_update_count_when_update_capacity() throws Exception {
        ParkingLot parkingLotExpected = new ParkingLot("parkingLotB ",12,"zha");
        parkingLotExpected.setId((long) 2);

        ParkingLot parkingLotpdated= new ParkingLot("parkingLotB ",23,"zha");
        parkingLotExpected.setId((long) 2);

        given(parkingLotService.updateParkingLot((long) 2,parkingLotpdated))
        .willReturn(parkingLotExpected);

        mvc.perform(put("/parkingLots/2",parkingLotpdated))
                .andExpect(content().string(objectMapper.writeValueAsString(parkingLotExpected)));
    }

    @Test
    public void should_return_parkingLot_when_request_by_id() throws Exception {

        ParkingLot parkingLotExpected = new ParkingLot("ParkingLotA",23,"zha");
        parkingLotExpected.setId((long) 1);

        given(parkingLotService.getParkingLot((long) 1))
                .willReturn(parkingLotExpected);

        mvc.perform(get("/parkingLots/1"))
                .andExpect(content().string(objectMapper.writeValueAsString(parkingLotExpected)));
    }
    @Test
    //not  test pass
    public void should_throw_exception_when_delete_parkingLot_faled_by_id() throws Exception {

        ParkingLot parkingLotExpected = new ParkingLot("ParkingLotA",23,"zha");
        parkingLotExpected.setId((long) 1);

        given(parkingLotService.deleteParkingLot((long) 1))
                .willThrow(new Exception());

        mvc.perform(delete("/parkingLots/1"))
                .andExpect(content().string(objectMapper.writeValueAsString(parkingLotExpected)));
    }
}
