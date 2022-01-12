package com.example.camerasparser.service;

import com.example.camerasparser.json.Camera;
import com.example.camerasparser.json.CameraInfo;
import com.example.camerasparser.json.SourceDataUrlInfo;
import com.example.camerasparser.json.TokenDataUrlInfo;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.logging.*;




@Service
@AllArgsConstructor
public class CameraInfoService {

    private static Logger logger;
    private final RestTemplate restTemplate;

    public List<CameraInfo> getResultCameraInfoList() {

            return getCameraInfoList(Objects.requireNonNull(getCameras()));

    }

    @Nullable
    public Camera[] getCameras() {

        try {

            return getObjectFromRestTemplate(new URL("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"),
                    Camera[].class);

        } catch(MalformedURLException exception) {

            logger.log(Level.WARNING, "In getCameras method of CameraInfoService class ", exception);
        }

        return null;
    }

    public SourceDataUrlInfo getSourceDataUrlInfo(URL url) {

        return getObjectFromRestTemplate(url, SourceDataUrlInfo.class);

    }

    public TokenDataUrlInfo getTokenDataUrlInfo(URL url) {

        return getObjectFromRestTemplate(url, TokenDataUrlInfo.class);

    }

    public List<CameraInfo> getCameraInfoList(Camera[] cameras) {

        return Stream.of(cameras).parallel().map(camera -> getFilledCameraInfo(camera, getSourceDataUrlInfo(
                camera.getSourceDataUrl()), getTokenDataUrlInfo(camera.getTokenDataUrl()))).collect(Collectors.toList());

    }

    public CameraInfo getFilledCameraInfo(Camera camera, SourceDataUrlInfo sourceDataUrlInfo,
                                          TokenDataUrlInfo tokenDataUrlInfo) {

        CameraInfo cameraInfo = new CameraInfo();
        cameraInfo.setId(camera.getId());
        cameraInfo.setUrlType(sourceDataUrlInfo.getUrlType());
        cameraInfo.setVideoUrl(sourceDataUrlInfo.getVideoUrl());
        cameraInfo.setValue(tokenDataUrlInfo.getValue());
        cameraInfo.setTtl(tokenDataUrlInfo.getTtl());

        return cameraInfo;
    }


    private URI getURIFromURL(URL url) {

        try {
            return url.toURI();
        } catch (URISyntaxException exception) {

            logger.log(Level.WARNING,"In getURIFromURL method of CameraInfoService class ", exception);
        }

        return null;
    }

    private <T> T getObjectFromRestTemplate(URL url, Class<T> tClass) {

        return restTemplate.getForObject(Objects.requireNonNull(getURIFromURL(url)), tClass);
    }


}
