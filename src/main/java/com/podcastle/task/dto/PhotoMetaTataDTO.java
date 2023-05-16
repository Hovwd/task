package com.podcastle.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhotoMetaTataDTO {

    private String id;
    private String slug;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("promoted_at")
    private LocalDateTime promotedAt;
    private int width;
    private int height;
    private String color;
    @JsonProperty("blur_hash")
    private String blurHash;
    private String description;
    @JsonProperty("alt_description")
    private String altDescription;
    private PhotoUrlsDTO urls;
    private int likes;
    @JsonProperty("liked_by_user")
    private boolean likedByUser;
}
