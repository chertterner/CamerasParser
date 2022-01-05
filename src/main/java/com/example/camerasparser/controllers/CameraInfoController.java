package com.example.camerasparser.controllers;


import com.example.camerasparser.json.Camera;
import com.example.camerasparser.json.CameraInfo;
import com.example.camerasparser.json.SourceDataUrlInfo;
import com.example.camerasparser.json.TokenDataUrlInfo;
import com.example.camerasparser.service.CameraInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/")
public class CameraInfoController {

    @Autowired
    private CameraInfoService cameraInfoService;

    @GetMapping("/get")
    public List<CameraInfo> getInfoList() throws URISyntaxException, MalformedURLException {

        return cameraInfoService.getResultCameraInfoList();
    }

}
