package com.podcastle.task.service;

import com.podcastle.task.dto.PhotoMetaDataWithStatisticsDTO;
import com.podcastle.task.dto.PhotoUrlsDTO;
import com.podcastle.task.type.Color;
import com.podcastle.task.entyty.PhotoMetaData;
import com.podcastle.task.repository.PhotoMetaDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoMetaDataService {

    private final PhotoMetaDataRepository photoMetaDataRepository;
    private final UnsplashAPIService unsplashAPIService;
    public Flux<PhotoMetaDataWithStatisticsDTO> getPhotosMetadataByColor(Color colorName) {
        Flux<PhotoMetaData> photoMetaDataFlux = photoMetaDataRepository.findAllByColorName(colorName);
        return photoMetaDataFlux.flatMap(photoMetaData -> {
            PhotoMetaDataWithStatisticsDTO photoMetaDataWithStatisticsDTO = new PhotoMetaDataWithStatisticsDTO();
            BeanUtils.copyProperties(photoMetaData, photoMetaDataWithStatisticsDTO);
            photoMetaDataWithStatisticsDTO.setUrls(new PhotoUrlsDTO());
            BeanUtils.copyProperties(photoMetaData.getUrls(), photoMetaDataWithStatisticsDTO.getUrls());
            photoMetaDataWithStatisticsDTO.setStatistic(unsplashAPIService.fetchStatisticOfPhoto(photoMetaData.getId()));
            log.info("Retrieved metadata for photo: {}", photoMetaData.getId());
            return Mono.just(photoMetaDataWithStatisticsDTO);
        });
    }

}
