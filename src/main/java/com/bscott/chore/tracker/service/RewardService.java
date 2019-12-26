package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    private RewardRepository rewardRepository;
    private UserService userService;

    @Autowired
    public RewardService(RewardRepository rewardRepository, UserService userService) {
        this.rewardRepository = rewardRepository;
        this.userService = userService;
    }

    public Reward findReward(Integer choreId) {

        Optional<Reward> reward = rewardRepository.findById(choreId);

        return reward.orElse(null);
    }

    public List<Reward> getRewardsByOwner(Integer ownerId) {

        return rewardRepository.findRewardsByOwnerId(ownerId);
    }

    public List<Reward> getRewardsByAssignee(Integer assigneeId) {

        User assignee = userService.findUserById(assigneeId);
        return rewardRepository.findRewardsByAssigneesEquals(assignee);
    }

    public Reward addReward(Reward reward) {

        return rewardRepository.save(reward);
    }

    public Reward updateReward(Reward reward) {

        rewardRepository.save(reward);
        return reward;
    }

    public void deleteReward(Integer id) {
        rewardRepository.deleteById(id);
    }
}
