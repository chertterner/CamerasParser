package com.example.camerasparser.service;

import com.example.camerasparser.json.Camera;
import com.example.camerasparser.json.CameraInfo;
import com.example.camerasparser.json.SourceDataUrlInfo;
import com.example.camerasparser.json.TokenDataUrlInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CameraInfoService {

    private RestTemplate restTemplate = new RestTemplate();


    public List<CameraInfo> getResultCameraInfoList() throws URISyntaxException, MalformedURLException {

        Camera[] cameras = getCameras();

        return getCameraInfoList(cameras);

    }

    public Camera[] getCameras() throws MalformedURLException, URISyntaxException {

        URL url = new URL("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");

        return restTemplate.getForObject(url.toURI(), Camera[].class);
    }

    public SourceDataUrlInfo getSourceDataUrlInfo(URL url) throws URISyntaxException {

        return restTemplate.getForObject(url.toURI(), SourceDataUrlInfo.class);
    }

    public TokenDataUrlInfo getTokenDataUrlInfo(URL url) throws URISyntaxException {

        return restTemplate.getForObject(url.toURI(), TokenDataUrlInfo.class);
    }

    public List<CameraInfo> getCameraInfoList(Camera[] cameras) {

        return Stream.of(cameras).parallel().map(camera -> {

            CameraInfo cameraInfo = new CameraInfo();

            try {

                cameraInfo = getFilledCameraInfo(camera, getSourceDataUrlInfo(camera.getSourceDataUrl()),
                        getTokenDataUrlInfo(camera.getTokenDataUrl()));

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return cameraInfo;

        }).collect(Collectors.toList());
    }

    public CameraInfo getFilledCameraInfo(Camera camera, SourceDataUrlInfo sourceDataUrlInfo, TokenDataUrlInfo tokenDataUrlInfo) {

        CameraInfo cameraInfo = new CameraInfo();
        cameraInfo.setId(camera.getId());
        cameraInfo.setUrlType(sourceDataUrlInfo.getUrlType());
        cameraInfo.setVideoUrl(sourceDataUrlInfo.getVideoUrl());
        cameraInfo.setValue(tokenDataUrlInfo.getValue());
        cameraInfo.setTtl(tokenDataUrlInfo.getTtl());

        return cameraInfo;
    }
}
