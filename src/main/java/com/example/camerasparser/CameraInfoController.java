package com.example.camerasparser;


import com.example.camerasparser.json.Camera;
import com.example.camerasparser.json.CameraInfo;
import com.example.camerasparser.json.SourceDataUrlInfo;
import com.example.camerasparser.json.TokenDataUrlInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/")
public class CameraInfoController {


    @GetMapping("/")
    public List<CameraInfo> getInfo() throws IOException {

        URL url = new URL("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
        ObjectMapper objectMapper = new ObjectMapper();

        return
                objectMapper.readValue(url, new TypeReference<List<Camera>>() {
                        })
                        .parallelStream()
                        .map(camera -> parseCameras(objectMapper, camera))
                        .collect(Collectors.toList());
    }


    private CameraInfo parseCameras(ObjectMapper objectMapper, Camera camera) {

        CameraInfo cameraInfo = new CameraInfo();

        try {
            cameraInfo.setId(camera.getId());

            cameraInfo.setUrlType(objectMapper.readValue(camera.getSourceDataUrl(),
                    SourceDataUrlInfo.class).getUrlType());

            cameraInfo.setVideoUrl(objectMapper.readValue(camera.getSourceDataUrl(),
                    SourceDataUrlInfo.class).getVideoUrl());

            cameraInfo.setTtl(objectMapper.readValue(camera.getTokenDataUrl(),
                    TokenDataUrlInfo.class).getTtl());

            cameraInfo.setValue(objectMapper.readValue(camera.getTokenDataUrl(),
                    TokenDataUrlInfo.class).getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cameraInfo;
    }

}
