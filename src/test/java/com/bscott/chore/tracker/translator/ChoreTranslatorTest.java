package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.FrequencyType;
import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.domain.RewardType;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.ChoreDto;
import com.bscott.chore.tracker.dto.RewardDto;
import com.bscott.chore.tracker.dto.UserDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ChoreTranslatorTest {

    private static final Integer USER_ID = 12345;
    private static final Integer CHORE_ID = 55555;
    private static final Integer CHORE2_ID = 66666;

    private ChoreTranslator choreTranslator = new ChoreTranslator();

    @Test
    public void testToDto(){

        User user = new User();
        user.setId(USER_ID);
        user.setFirstName("Emery");
        user.setLastName("Scott");
        user.setBirthDate(LocalDate.parse("2016-04-09"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(100);

        Reward reward = new Reward();
        reward.setRewardType(RewardType.POINTS);
        reward.setRewardValue(10);

        Chore chore = new Chore();
        chore.setId(CHORE_ID);
        chore.setName("Clean Dishes");
        chore.setDescription("Clean the Dishes and put them away");
        chore.setFrequency(FrequencyType.DAILY);
        chore.setAssignees(new ArrayList<>());
        chore.getAssignees().add(user);
        chore.setReward(reward);

        ChoreDto choreDto = choreTranslator.toDto(chore);

        assertEquals(chore.getId(), choreDto.getId());
        assertEquals(chore.getName(), choreDto.getName());
        assertEquals(chore.getDescription(), choreDto.getDescription());
        assertEquals(chore.getFrequency().toString(), choreDto.getFrequency());

        assertEquals(chore.getAssignees().get(0).getFirstName(), choreDto.getAssignees().get(0).getFirstName());
        assertEquals(chore.getAssignees().get(0).getLastName(), choreDto.getAssignees().get(0).getLastName());
        assertEquals(chore.getAssignees().get(0).getBirthDate().toString(), choreDto.getAssignees().get(0).getBirthDate());
        assertEquals(chore.getAssignees().get(0).getMoneyEarned(), choreDto.getAssignees().get(0).getMoneyEarned());
        assertEquals(chore.getAssignees().get(0).getPointsEarned(), choreDto.getAssignees().get(0).getPointsEarned());

        assertEquals(chore.getReward().getId(), choreDto.getReward().getId());
        assertEquals(chore.getReward().getName(), choreDto.getReward().getName());
        assertEquals(chore.getReward().getDescription(), choreDto.getReward().getDescription());
        assertEquals(chore.getReward().getRewardType().toString(), choreDto.getReward().getRewardType());
        assertEquals(chore.getReward().getRewardValue(), choreDto.getReward().getRewardValue());
    }

    @Test
    public void testToDto_Null(){

        ChoreDto choreDto = choreTranslator.toDto(null);
        assertNull(choreDto);
    }

    @Test
    public void testToDtos(){

        List<Chore> choreList = new ArrayList<>();

        User user = new User();
        user.setId(USER_ID);
        user.setFirstName("Emery");
        user.setLastName("Scott");
        user.setBirthDate(LocalDate.parse("2016-04-09"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(100);

        Reward reward = new Reward();
        reward.setRewardType(RewardType.POINTS);
        reward.setRewardValue(10);

        Chore chore = new Chore();
        chore.setId(CHORE_ID);
        chore.setName("Clean Dishes");
        chore.setDescription("Clean the Dishes and put them away");
        chore.setFrequency(FrequencyType.DAILY);
        chore.setAssignees(new ArrayList<>());
        chore.getAssignees().add(user);
        chore.setReward(reward);

        Chore chore2 = new Chore();
        chore2.setId(CHORE2_ID);
        chore2.setName("Clean Your Room");
        chore2.setDescription("Clean your room and make the bed");
        chore2.setFrequency(FrequencyType.DAILY);
        chore2.setAssignees(new ArrayList<>());
        chore2.getAssignees().add(user);
        chore2.setReward(reward);

        choreList.add(chore);
        choreList.add(chore2);

        List<ChoreDto> choreDtos = choreTranslator.toDtos(choreList);

        assertEquals(chore.getId(), choreDtos.get(0).getId());
        assertEquals(chore.getName(), choreDtos.get(0).getName());
        assertEquals(chore.getDescription(), choreDtos.get(0).getDescription());
        assertEquals(chore.getFrequency().toString(), choreDtos.get(0).getFrequency());

        assertEquals(chore.getAssignees().get(0).getId(), choreDtos.get(0).getAssignees().get(0).getId());
        assertEquals(chore.getAssignees().get(0).getFirstName(), choreDtos.get(0).getAssignees().get(0).getFirstName());
        assertEquals(chore.getAssignees().get(0).getLastName(), choreDtos.get(0).getAssignees().get(0).getLastName());
        assertEquals(chore.getAssignees().get(0).getBirthDate().toString(), choreDtos.get(0).getAssignees().get(0).getBirthDate());
        assertEquals(chore.getAssignees().get(0).getMoneyEarned(), choreDtos.get(0).getAssignees().get(0).getMoneyEarned());
        assertEquals(chore.getAssignees().get(0).getPointsEarned(), choreDtos.get(0).getAssignees().get(0).getPointsEarned());

        assertEquals(chore.getReward().getId(), choreDtos.get(0).getReward().getId());
        assertEquals(chore.getReward().getName(), choreDtos.get(0).getReward().getName());
        assertEquals(chore.getReward().getDescription(), choreDtos.get(0).getReward().getDescription());
        assertEquals(chore.getReward().getRewardType().toString(), choreDtos.get(0).getReward().getRewardType());
        assertEquals(chore.getReward().getRewardValue(), choreDtos.get(0).getReward().getRewardValue());

        assertEquals(chore2.getId(), choreDtos.get(1).getId());
        assertEquals(chore2.getName(), choreDtos.get(1).getName());
        assertEquals(chore2.getDescription(), choreDtos.get(1).getDescription());
        assertEquals(chore2.getFrequency().toString(), choreDtos.get(1).getFrequency());

        assertEquals(chore2.getAssignees().get(0).getId(), choreDtos.get(1).getAssignees().get(0).getId());
        assertEquals(chore2.getAssignees().get(0).getFirstName(), choreDtos.get(1).getAssignees().get(0).getFirstName());
        assertEquals(chore2.getAssignees().get(0).getLastName(), choreDtos.get(1).getAssignees().get(0).getLastName());
        assertEquals(chore2.getAssignees().get(0).getBirthDate().toString(), choreDtos.get(1).getAssignees().get(0).getBirthDate());
        assertEquals(chore2.getAssignees().get(0).getMoneyEarned(), choreDtos.get(1).getAssignees().get(0).getMoneyEarned());
        assertEquals(chore2.getAssignees().get(0).getPointsEarned(), choreDtos.get(1).getAssignees().get(0).getPointsEarned());

        assertEquals(chore2.getReward().getId(), choreDtos.get(1).getReward().getId());
        assertEquals(chore2.getReward().getName(), choreDtos.get(1).getReward().getName());
        assertEquals(chore2.getReward().getDescription(), choreDtos.get(1).getReward().getDescription());
        assertEquals(chore2.getReward().getRewardType().toString(), choreDtos.get(1).getReward().getRewardType());
        assertEquals(chore2.getReward().getRewardValue(), choreDtos.get(1).getReward().getRewardValue());
    }

    @Test
    public void testToDtos_Null(){

        List<ChoreDto> choreDtos = choreTranslator.toDtos(null);
        assertTrue(choreDtos.isEmpty());
    }

    @Test
    public void testToDtos_EmptyList(){

        List<ChoreDto> choreDtos = choreTranslator.toDtos(new ArrayList<>());
        assertEquals(new ArrayList<ChoreDto>(), choreDtos);
    }

    @Test
    public void testToEntity(){

        UserDto userDto = new UserDto();
        userDto.setId(USER_ID);
        userDto.setFirstName("Emery");
        userDto.setLastName("Scott");
        userDto.setBirthDate("2016-04-09");
        userDto.setMoneyEarned(BigDecimal.ZERO);
        userDto.setPointsEarned(100);

        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardType("POINTS");
        rewardDto.setRewardValue(10);

        ChoreDto choreDto = new ChoreDto();
        choreDto.setId(CHORE_ID);
        choreDto.setName("Clean Dishes");
        choreDto.setDescription("Clean the Dishes and put them away");
        choreDto.setFrequency("DAILY");
        choreDto.setAssignees(new ArrayList<>());
        choreDto.getAssignees().add(userDto);
        choreDto.setReward(rewardDto);

        Chore chore = choreTranslator.toEntity(choreDto);

        assertEquals(choreDto.getId(), chore.getId());
        assertEquals(choreDto.getName(), chore.getName());
        assertEquals(choreDto.getDescription(), chore.getDescription());
        assertEquals(choreDto.getFrequency(), chore.getFrequency().toString());

        assertEquals(choreDto.getAssignees().get(0).getId(), chore.getAssignees().get(0).getId());
        assertEquals(choreDto.getAssignees().get(0).getFirstName(), chore.getAssignees().get(0).getFirstName());
        assertEquals(choreDto.getAssignees().get(0).getLastName(), chore.getAssignees().get(0).getLastName());
        assertEquals(choreDto.getAssignees().get(0).getBirthDate(), chore.getAssignees().get(0).getBirthDate().toString());
        assertEquals(choreDto.getAssignees().get(0).getMoneyEarned(), chore.getAssignees().get(0).getMoneyEarned());
        assertEquals(choreDto.getAssignees().get(0).getPointsEarned(), chore.getAssignees().get(0).getPointsEarned());

        assertEquals(choreDto.getReward().getId(), chore.getReward().getId());
        assertEquals(choreDto.getReward().getName(), chore.getReward().getName());
        assertEquals(choreDto.getReward().getDescription(), chore.getReward().getDescription());
        assertEquals(choreDto.getReward().getRewardType(), chore.getReward().getRewardType().toString());
        assertEquals(choreDto.getReward().getRewardValue(), chore.getReward().getRewardValue());
    }

    @Test
    public void testToEntity_Null(){

        Chore chore = choreTranslator.toEntity(null);
        assertNull(chore);
    }
}
