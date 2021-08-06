package com.hanium.if050.domain.video;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, Long> {
}
