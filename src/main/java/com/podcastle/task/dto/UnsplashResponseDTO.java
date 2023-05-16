package com.podcastle.task.dto;

import lombok.Data;
import java.util.List;

@Data
public class UnsplashResponseDTO {
    int intTotal;
    int totalPages;
    List<PhotoMetaTataDTO> results;
}
