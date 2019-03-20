package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.FrequencyType;
import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.domain.RewardType;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.ChoreDto;
import com.bscott.chore.tracker.dto.RewardDto;
import com.bscott.chore.tracker.dto.UserDto;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChoreTranslatorTest {

    private ChoreTranslator choreTranslator = new ChoreTranslator();

    @Test
    public void testToDto(){

        User user = new User();
        user.setId("583a9be33004dfd16b956697");
        user.setFirstName("Emery");
        user.setLastName("Scott");
        user.setBirthDate(new LocalDate("2016-04-09"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(100);

        Reward reward = new Reward();
        reward.setRewardType(RewardType.POINTS);
        reward.setRewardValue(10);

        Chore chore = new Chore();
        chore.setId("583a9be33004dfd16b956698");
        chore.setName("Clean Dishes");
        chore.setDescription("Clean the Dishes and put them away");
        chore.setFrequency(FrequencyType.DAILY);
        chore.setAssignee(user);
        chore.setReward(reward);

        ChoreDto choreDto = choreTranslator.toDto(chore);

        assertEquals(chore.getId(), choreDto.getId());
        assertEquals(chore.getName(), choreDto.getName());
        assertEquals(chore.getDescription(), choreDto.getDescription());
        assertEquals(chore.getFrequency().toString(), choreDto.getFrequency());

        assertEquals(chore.getAssignee().getId(), choreDto.getAssignee().getId());
        assertEquals(chore.getAssignee().getFirstName(), choreDto.getAssignee().getFirstName());
        assertEquals(chore.getAssignee().getLastName(), choreDto.getAssignee().getLastName());
        assertEquals(chore.getAssignee().getBirthDate().toString(), choreDto.getAssignee().getBirthDate());
        assertEquals(chore.getAssignee().getMoneyEarned(), choreDto.getAssignee().getMoneyEarned());
        assertEquals(chore.getAssignee().getPointsEarned(), choreDto.getAssignee().getPointsEarned());

        assertEquals(chore.getReward().getId(), choreDto.getReward().getId());
        assertEquals(chore.getReward().getName(), choreDto.getReward().getName());
        assertEquals(chore.getReward().getDescription(), choreDto.getReward().getDescription());
        assertEquals(chore.getReward().getRewardType().toString(), choreDto.getReward().getRewardType());
        assertEquals(chore.getReward().getRewardValue(), choreDto.getReward().getRewardValue());
    }

    @Test
    public void testToDto_Null(){

        ChoreDto choreDto = choreTranslator.toDto(null);
        assertEquals(null, choreDto);
    }

    @Test
    public void testToDtos(){

        List<Chore> choreList = new ArrayList<>();

        User user = new User();
        user.setId("583a9be33004dfd16b956697");
        user.setFirstName("Emery");
        user.setLastName("Scott");
        user.setBirthDate(new LocalDate("2016-04-09"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(100);

        Reward reward = new Reward();
        reward.setRewardType(RewardType.POINTS);
        reward.setRewardValue(10);

        Chore chore = new Chore();
        chore.setId("583a9be33004dfd16b956698");
        chore.setName("Clean Dishes");
        chore.setDescription("Clean the Dishes and put them away");
        chore.setFrequency(FrequencyType.DAILY);
        chore.setAssignee(user);
        chore.setReward(reward);

        Chore chore2 = new Chore();
        chore2.setId("583a9be33004dfd16b956699");
        chore2.setName("Clean Your Room");
        chore2.setDescription("Clean your room and make the bed");
        chore2.setFrequency(FrequencyType.DAILY);
        chore2.setAssignee(user);
        chore2.setReward(reward);

        choreList.add(chore);
        choreList.add(chore2);

        List<ChoreDto> choreDtos = choreTranslator.toDtos(choreList);

        assertEquals(chore.getId(), choreDtos.get(0).getId());
        assertEquals(chore.getName(), choreDtos.get(0).getName());
        assertEquals(chore.getDescription(), choreDtos.get(0).getDescription());
        assertEquals(chore.getFrequency().toString(), choreDtos.get(0).getFrequency());

        assertEquals(chore.getAssignee().getId(), choreDtos.get(0).getAssignee().getId());
        assertEquals(chore.getAssignee().getFirstName(), choreDtos.get(0).getAssignee().getFirstName());
        assertEquals(chore.getAssignee().getLastName(), choreDtos.get(0).getAssignee().getLastName());
        assertEquals(chore.getAssignee().getBirthDate().toString(), choreDtos.get(0).getAssignee().getBirthDate());
        assertEquals(chore.getAssignee().getMoneyEarned(), choreDtos.get(0).getAssignee().getMoneyEarned());
        assertEquals(chore.getAssignee().getPointsEarned(), choreDtos.get(0).getAssignee().getPointsEarned());

        assertEquals(chore.getReward().getId(), choreDtos.get(0).getReward().getId());
        assertEquals(chore.getReward().getName(), choreDtos.get(0).getReward().getName());
        assertEquals(chore.getReward().getDescription(), choreDtos.get(0).getReward().getDescription());
        assertEquals(chore.getReward().getRewardType().toString(), choreDtos.get(0).getReward().getRewardType());
        assertEquals(chore.getReward().getRewardValue(), choreDtos.get(0).getReward().getRewardValue());

        assertEquals(chore2.getId(), choreDtos.get(1).getId());
        assertEquals(chore2.getName(), choreDtos.get(1).getName());
        assertEquals(chore2.getDescription(), choreDtos.get(1).getDescription());
        assertEquals(chore2.getFrequency().toString(), choreDtos.get(1).getFrequency());

        assertEquals(chore2.getAssignee().getId(), choreDtos.get(1).getAssignee().getId());
        assertEquals(chore2.getAssignee().getFirstName(), choreDtos.get(1).getAssignee().getFirstName());
        assertEquals(chore2.getAssignee().getLastName(), choreDtos.get(1).getAssignee().getLastName());
        assertEquals(chore2.getAssignee().getBirthDate().toString(), choreDtos.get(1).getAssignee().getBirthDate());
        assertEquals(chore2.getAssignee().getMoneyEarned(), choreDtos.get(1).getAssignee().getMoneyEarned());
        assertEquals(chore2.getAssignee().getPointsEarned(), choreDtos.get(1).getAssignee().getPointsEarned());

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
        userDto.setId("583a9be33004dfd16b956697");
        userDto.setFirstName("Emery");
        userDto.setLastName("Scott");
        userDto.setBirthDate("2016-04-09");
        userDto.setMoneyEarned(BigDecimal.ZERO);
        userDto.setPointsEarned(100);

        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardType("POINTS");
        rewardDto.setRewardValue(10);

        ChoreDto choreDto = new ChoreDto();
        choreDto.setId("583a9be33004dfd16b956698");
        choreDto.setName("Clean Dishes");
        choreDto.setDescription("Clean the Dishes and put them away");
        choreDto.setFrequency("DAILY");
        choreDto.setAssignee(userDto);
        choreDto.setReward(rewardDto);

        Chore chore = choreTranslator.toEntity(choreDto);

        assertEquals(choreDto.getId(), chore.getId());
        assertEquals(choreDto.getName(), chore.getName());
        assertEquals(choreDto.getDescription(), chore.getDescription());
        assertEquals(choreDto.getFrequency(), chore.getFrequency().toString());

        assertEquals(choreDto.getAssignee().getId(), chore.getAssignee().getId());
        assertEquals(choreDto.getAssignee().getFirstName(), chore.getAssignee().getFirstName());
        assertEquals(choreDto.getAssignee().getLastName(), chore.getAssignee().getLastName());
        assertEquals(choreDto.getAssignee().getBirthDate(), chore.getAssignee().getBirthDate().toString());
        assertEquals(choreDto.getAssignee().getMoneyEarned(), chore.getAssignee().getMoneyEarned());
        assertEquals(choreDto.getAssignee().getPointsEarned(), chore.getAssignee().getPointsEarned());

        assertEquals(choreDto.getReward().getId(), chore.getReward().getId());
        assertEquals(choreDto.getReward().getName(), chore.getReward().getName());
        assertEquals(choreDto.getReward().getDescription(), chore.getReward().getDescription());
        assertEquals(choreDto.getReward().getRewardType(), chore.getReward().getRewardType().toString());
        assertEquals(choreDto.getReward().getRewardValue(), chore.getReward().getRewardValue());
    }

    @Test
    public void testToEntity_Null(){

        Chore chore = choreTranslator.toEntity(null);
        assertEquals(null, chore);
    }
}
