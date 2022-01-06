package com.example.camerasparser.service;

import com.example.camerasparser.json.Camera;
import com.example.camerasparser.json.CameraInfo;
import com.example.camerasparser.json.SourceDataUrlInfo;
import com.example.camerasparser.json.TokenDataUrlInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CameraInfoServiceTest {

    @Mock
    CameraInfo cameraInfo;

    @Mock
    RestTemplate restTemplate;



    @Test
    public void getCameras() throws MalformedURLException, URISyntaxException {

        Camera[] cameras = new Camera[4];

        URL url = new URL("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
        assertNotNull(url);

        lenient().when(restTemplate.getForObject(url.toURI(), Camera[].class)).thenReturn(cameras);
        doReturn(cameras).when(restTemplate).getForObject(url.toURI(), Camera[].class);
        assertNotNull(restTemplate);
        assertEquals(restTemplate.getForObject(url.toURI(), Camera[].class), cameras);
    }

    @Test
    public void getSourceDataUrlInfo() throws URISyntaxException, MalformedURLException {

        SourceDataUrlInfo tokenDataUrlInfo = new SourceDataUrlInfo();

        URL url = new URL("http://www.mocky.io/v2/5c51b230340000094f129f5d");
        assertNotNull(url);

        assertNotEquals(restTemplate.getForObject(url.toURI(), SourceDataUrlInfo.class), new SourceDataUrlInfo());
        lenient().doReturn(tokenDataUrlInfo).when(restTemplate).getForObject(url.toURI(), SourceDataUrlInfo.class);
    }

    @Test
    public void getTokenDataUrlInfo() throws MalformedURLException, URISyntaxException {

        TokenDataUrlInfo tokenDataUrlInfo = new TokenDataUrlInfo();

        URL url = new URL("http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s");
        assertNotNull(url);

        assertNotEquals(restTemplate.getForObject(url.toURI(), TokenDataUrlInfo.class), new TokenDataUrlInfo());
        lenient().doReturn(tokenDataUrlInfo).when(restTemplate).getForObject(url.toURI(), TokenDataUrlInfo.class);

    }


    @Test
    public void getFilledCameraInfo() {

        cameraInfo.setId(1);
        cameraInfo.setValue("fa4b588e-249b-11e9-ab14-d663bd873d93");
        cameraInfo.setTtl(120);
        cameraInfo.setVideoUrl("rtsp://127.0.0.1/1");
        cameraInfo.setUrlType("LIVE");

        assertNotNull(cameraInfo);
        assertNotEquals(cameraInfo, new CameraInfo());

    }
}