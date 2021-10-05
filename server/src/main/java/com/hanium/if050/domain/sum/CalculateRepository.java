package com.hanium.if050.domain.sum;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface CalculateRepository extends MongoRepository<Calculate,Long> {

    Calculate findFirstByCctvIdOrderById(String cctvId);
}
