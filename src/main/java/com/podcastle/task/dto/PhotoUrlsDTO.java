package com.podcastle.task.dto;

import lombok.Data;

@Data
public class PhotoUrlsDTO {
    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;
    private String smallS3;
}
