package com.podcastle.task.service;

import com.podcastle.task.repository.PhotoMetaDataRepository;
import com.podcastle.task.type.Color;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UnsplashAPIService unsplashAPIService;
    private final DataLoadConfiguration dataLoadConfiguration;
    private final PhotoMetaDataRepository metaDataRepository;
    private final PhotoMetaDataRepository photoMetaDataRepository;

    @Override
    public void run(String... args) throws Exception {
        fetchAndStorePhotos();
    }

    private void fetchAndStorePhotos() {
        if (dataLoadConfiguration.isUpdateData()) {
            log.info("Deleting existing photo metadata.");
            metaDataRepository.deleteAll();
            int totalPages = (dataLoadConfiguration.getTotalCountPhotos() / dataLoadConfiguration.getPerPagePhotos()) / Color.values().length;
            log.info("Fetching and storing photos by color.");
            Arrays.stream(Color.values()).forEach(color -> fetchAndSave(color, totalPages, dataLoadConfiguration.getPerPagePhotos()));
        } else {
            log.info("Data update is disabled. Skipping fetch and store operation.");
        }
    }

    public void fetchAndSave(Color color, int totalPages, int perPage) {
        Flux.range(1, totalPages)
                .flatMap(page -> unsplashAPIService.fetchDataByColor(color, page, perPage))
                .flatMap(photoMetaDataRepository::save)
                .subscribe(photoMetaData -> log.info("Saved metadata for photo: {}", photoMetaData.getId()));
    }
}
