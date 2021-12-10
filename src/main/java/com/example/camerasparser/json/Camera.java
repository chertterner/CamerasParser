package com.example.camerasparser.json;

import lombok.Data;

import java.net.URL;

@Data
public class Camera {

    private long id;
    private URL sourceDataUrl;
    private URL tokenDataUrl;
}
