package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.Reward;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RewardRepository extends MongoRepository<Reward, String> {

    List<Reward> findRewardsByRewardType(String type);
}
