package com.podcastle.task.repository;

import com.podcastle.task.type.Color;
import com.podcastle.task.entyty.PhotoMetaData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PhotoMetaDataRepository extends ReactiveMongoRepository<PhotoMetaData, String> {
    Flux<PhotoMetaData> findAllByColorName(Color colorName);
}
