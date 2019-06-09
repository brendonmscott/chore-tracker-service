package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Integer> {

    List<Reward> findRewardsByRewardType(String type);
}
