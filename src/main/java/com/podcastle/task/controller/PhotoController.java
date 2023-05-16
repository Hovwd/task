package com.podcastle.task.controller;

import com.podcastle.task.dto.PhotoMetaDataWithStatisticsDTO;
import com.podcastle.task.type.Color;
import com.podcastle.task.service.PhotoMetaDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/photo")
@AllArgsConstructor
public class PhotoController {

    final PhotoMetaDataService photoMetaDataService;

    @GetMapping("/{color}")
    public Flux<PhotoMetaDataWithStatisticsDTO> getProduct(@PathVariable Color color){
        return photoMetaDataService.getPhotosMetadataByColor(color);
    }
}
