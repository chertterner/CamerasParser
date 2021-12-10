package com.example.camerasparser.json;

import lombok.Data;

import java.net.URL;

@Data
public class CameraInfo {

    private long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private long ttl;
}
