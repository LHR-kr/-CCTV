package com.hanium.if050.domain.cctv;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CctvRepository extends MongoRepository<Cctv, String> {
}
