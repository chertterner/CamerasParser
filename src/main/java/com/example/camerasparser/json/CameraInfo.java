package com.example.camerasparser.json;

import lombok.Data;

@Data
public class CameraInfo {

    private long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private long ttl;
}
