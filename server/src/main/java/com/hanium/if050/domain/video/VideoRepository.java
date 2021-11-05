package com.hanium.if050.domain.video;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface VideoRepository extends MongoRepository<Video, Long> {

    ArrayList<Video> findByCctvId(String cctvId);
}
