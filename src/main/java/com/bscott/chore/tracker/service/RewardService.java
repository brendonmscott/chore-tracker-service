package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public List<Reward> getRewards(String type) {

        List<Reward> rewards;

        if (type != null) {
            rewards = rewardRepository.findRewardsByRewardType(type);
        } else {
            rewards = rewardRepository.findAll();
        }

        return rewards;
    }

    public Reward addReward(Reward reward) {

        return rewardRepository.save(reward);
    }

    public Reward updateReward(Reward reward) {

        rewardRepository.save(reward);

        return reward;
    }

    public void deleteReward(String id) {
        rewardRepository.deleteById(id);
    }
}
