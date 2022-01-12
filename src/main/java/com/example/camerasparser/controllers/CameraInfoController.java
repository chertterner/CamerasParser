package com.example.camerasparser.controllers;



import com.example.camerasparser.json.CameraInfo;
import com.example.camerasparser.service.CameraInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CameraInfoController {


    private final CameraInfoService cameraInfoService;

    @GetMapping("/get")
    public List<CameraInfo> getInfoList() {

        return Objects.requireNonNull(cameraInfoService.getResultCameraInfoList());
    }

}
