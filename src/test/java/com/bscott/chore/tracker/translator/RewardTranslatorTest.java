package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.domain.RewardType;
import com.bscott.chore.tracker.dto.RewardDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RewardTranslatorTest {

    private static final Integer REWARD1_ID = 12345;
    private static final Integer REWARD2_ID = 67890;

    private RewardTranslator rewardTranslator = new RewardTranslator();

    @Test
    public void testToDto(){

        Reward reward = new Reward();
        reward.setId(REWARD1_ID);
        reward.setName("New Toy");
        reward.setDescription("You get to shop for a new toy");
        reward.setRewardType(RewardType.PRIZE);
        reward.setRewardValue(null);

        RewardDto rewardDto = rewardTranslator.toDto(reward);
        assertEquals(reward.getId(), rewardDto.getId());
        assertEquals(reward.getName(), rewardDto.getName());
        assertEquals(reward.getDescription(), rewardDto.getDescription());
        assertEquals(reward.getRewardType().toString(), rewardDto.getRewardType());
        assertEquals(reward.getRewardValue(), rewardDto.getRewardValue());
    }

    @Test
    public void testToDto_Null(){

        RewardDto rewardDto = rewardTranslator.toDto(null);
        assertNull(rewardDto);
    }

    @Test
    public void testToDtos(){

        Reward reward1 = new Reward();
        reward1.setId(REWARD1_ID);
        reward1.setName("New Toy");
        reward1.setDescription("You get to shop for a new toy");
        reward1.setRewardType(RewardType.PRIZE);
        reward1.setRewardValue(null);

        Reward reward2 = new Reward();
        reward2.setId(REWARD2_ID);
        reward2.setName("Money");
        reward2.setDescription("You get 5 dollars");
        reward2.setRewardType(RewardType.MONEY);
        reward2.setRewardValue(5);

        List<Reward> rewards = new ArrayList<>();
        rewards.add(reward1);
        rewards.add(reward2);

        List<RewardDto> rewardDtos = rewardTranslator.toDtos(rewards);

        // Reward 1
        assertEquals(rewardDtos.get(0).getId(), rewards.get(0).getId());
        assertEquals(rewardDtos.get(0).getName(), rewards.get(0).getName());
        assertEquals(rewardDtos.get(0).getDescription(), rewards.get(0).getDescription());
        assertEquals(rewardDtos.get(0).getRewardType(), rewards.get(0).getRewardType().toString());
        assertEquals(rewardDtos.get(0).getRewardValue(), rewards.get(0).getRewardValue());

        // Reward 2
        assertEquals(rewardDtos.get(1).getId(), rewards.get(1).getId());
        assertEquals(rewardDtos.get(1).getName(), rewards.get(1).getName());
        assertEquals(rewardDtos.get(1).getDescription(), rewards.get(1).getDescription());
        assertEquals(rewardDtos.get(1).getRewardType(), rewards.get(1).getRewardType().toString());
        assertEquals(rewardDtos.get(1).getRewardValue(), rewards.get(1).getRewardValue());
    }

    @Test
    public void testToDtos_Null(){

        List<RewardDto> rewardDtos = rewardTranslator.toDtos(null);
        assertTrue(rewardDtos.isEmpty());
    }

    @Test
    public void testToDtos_EmptyList(){

        List<RewardDto> rewardDtos = rewardTranslator.toDtos(new ArrayList<>());
        assertTrue(rewardDtos.isEmpty());
    }

    @Test
    public void testToEntity(){

        RewardDto rewardDto = new RewardDto();
        rewardDto.setId(REWARD1_ID);
        rewardDto.setName("New Toy");
        rewardDto.setDescription("You get to shop for a new toy");
        rewardDto.setRewardType(RewardType.PRIZE.toString());
        rewardDto.setRewardValue(null);

        Reward reward = rewardTranslator.toEntity(rewardDto);
        assertEquals(rewardDto.getId(), reward.getId());
        assertEquals(rewardDto.getName(), reward.getName());
        assertEquals(rewardDto.getDescription(), reward.getDescription());
        assertEquals(rewardDto.getRewardType(), reward.getRewardType().toString());
        assertEquals(rewardDto.getRewardValue(), reward.getRewardValue());
    }

    @Test
    public void testToEntity_Null(){

        Reward reward = rewardTranslator.toEntity(null);
        assertNull(reward);
    }
}
