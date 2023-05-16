package com.podcastle.task.service;

import com.podcastle.task.dto.PhotoMetaTataDTO;
import com.podcastle.task.dto.StatisticDTO;
import com.podcastle.task.dto.UnsplashResponseDTO;
import com.podcastle.task.entyty.PhotoMetaData;
import com.podcastle.task.entyty.PhotoUrls;
import com.podcastle.task.repository.PhotoMetaDataRepository;
import com.podcastle.task.type.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class UnsplashAPIService {

    private final PhotoMetaDataRepository photoMetaDataRepository;
    private final WebClient webClient;
    private final DataLoadConfiguration dataLoadConfiguration;

    @Autowired
    public UnsplashAPIService(PhotoMetaDataRepository photoMetaDataRepository, DataLoadConfiguration dataLoadConfiguration) {
        this.photoMetaDataRepository = photoMetaDataRepository;
        this.dataLoadConfiguration = dataLoadConfiguration;
        this.webClient = WebClient.create(dataLoadConfiguration.getUnsplashUri());
    }

    public Flux<PhotoMetaData> fetchDataByColor(Color color, int page, int perPage) {
        return webClient.get()
                .uri("/search/photos?query=flower&page={page}&per_page={perPage}&color={color}", page, perPage, color.getColor())
                .header("Authorization", "Client-ID " + dataLoadConfiguration.getAccessKey())
                .retrieve()
                .bodyToMono(UnsplashResponseDTO.class)
                .flatMapMany(response -> Flux.fromIterable(response.getResults()))
                .map(unsplashPhoto -> convert(unsplashPhoto, color));
    }


    private PhotoMetaData convert(PhotoMetaTataDTO unsplashPhoto, Color color) {
        PhotoMetaData photoMetaData = new PhotoMetaData();
        BeanUtils.copyProperties(unsplashPhoto, photoMetaData);
        photoMetaData.setColorName(color);
        photoMetaData.setUrls(new PhotoUrls());
        BeanUtils.copyProperties(unsplashPhoto.getUrls(), photoMetaData.getUrls());
        return photoMetaData;
    }

    public StatisticDTO fetchStatisticOfPhoto(String id) {
        return webClient.get()
                .uri("/photos/{id}/statistics", id)
                .header("Authorization", "Client-ID " + dataLoadConfiguration.getAccessKey())
                .retrieve()
                .bodyToMono(StatisticDTO.class)
                .block();
    }
}
