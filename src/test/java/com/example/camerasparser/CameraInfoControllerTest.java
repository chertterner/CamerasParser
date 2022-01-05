package com.example.camerasparser;

import com.example.camerasparser.controllers.CameraInfoController;
import com.example.camerasparser.service.CameraInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CameraInfoController.class)
@RunWith(SpringRunner.class)
public class CameraInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CameraInfoService cameraInfoService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getInfoList() throws Exception {

        mockMvc.perform(get("/get")).andExpect(status().isOk());
    }

}