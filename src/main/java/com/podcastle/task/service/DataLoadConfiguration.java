package com.podcastle.task.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "data-load")

public class DataLoadConfiguration {

    private boolean updateData;
    private String unsplashUri;
    private String accessKey;
    private int perPagePhotos;
    private int totalCountPhotos;


    // Getters and setters for other properties
}