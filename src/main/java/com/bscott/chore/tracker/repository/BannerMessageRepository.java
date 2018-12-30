package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.BannerMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannerMessageRepository extends MongoRepository<BannerMessage, String> {
}
