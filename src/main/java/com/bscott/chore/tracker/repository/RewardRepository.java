package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Integer> {

    List<Reward> findRewardsByAssigneesEquals(User assignee);
    List<Reward> findRewardsByOwnerId(Integer ownerId);
}
