package com.podcastle.task.entyty;

import com.podcastle.task.type.Color;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "photo_meta_data")
public class PhotoMetaData {

    @Id
    private String id;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime promotedAt;
    private Color colorName;
    private int width;
    private int height;
    private String blurHash;
    private String description;
    private String altDescription;
    private PhotoUrls urls;
    private int likes;
    private boolean likedByUser;


}

