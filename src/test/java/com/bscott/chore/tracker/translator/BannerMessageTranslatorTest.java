package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.BannerMessage;
import com.bscott.chore.tracker.dto.BannerMessageDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BannerMessageTranslatorTest {

    private BannerMessageTranslator bannerMessageTranslator = new BannerMessageTranslator();

    @Test
    public void testToDto(){

        BannerMessage bannerMessage = new BannerMessage();
        bannerMessage.setHeadlineOne("Headline One");
        bannerMessage.setHeadlineOneText("Text for Headline One");
        bannerMessage.setHeadlineTwo("Headline Two");
        bannerMessage.setHeadlineTwoText("Text for Headline Two");
        bannerMessage.setHeadlineThree("Headline Three");
        bannerMessage.setHeadlineThreeText("Text for Headline Three");

        BannerMessageDto bannerMessageDto = bannerMessageTranslator.toDto(bannerMessage);

        assertEquals(bannerMessage.getHeadlineOne(), bannerMessageDto.getHeadlineOne());
        assertEquals(bannerMessage.getHeadlineTwo(), bannerMessageDto.getHeadlineTwo());
        assertEquals(bannerMessage.getHeadlineThree(), bannerMessageDto.getHeadlineThree());
        assertEquals(bannerMessage.getHeadlineOneText(), bannerMessageDto.getHeadlineOneText());
        assertEquals(bannerMessage.getHeadlineTwoText(), bannerMessageDto.getHeadlineTwoText());
        assertEquals(bannerMessage.getHeadlineThreeText(), bannerMessageDto.getHeadlineThreeText());
    }

    @Test
    public void testToDto_Null(){

        BannerMessageDto bannerMessageDto = bannerMessageTranslator.toDto(null);
        assertNull(bannerMessageDto);
    }

    @Test
    public void testToDtos(){

        BannerMessage bannerMessage1 = new BannerMessage();
        bannerMessage1.setHeadlineOne("Headline One");
        bannerMessage1.setHeadlineOneText("Text for Headline One");
        bannerMessage1.setHeadlineTwo("Headline Two");
        bannerMessage1.setHeadlineTwoText("Text for Headline Two");
        bannerMessage1.setHeadlineThree("Headline Three");
        bannerMessage1.setHeadlineThreeText("Text for Headline Three");

        BannerMessage bannerMessage2 = new BannerMessage();
        bannerMessage2.setHeadlineOne("Another Headline One");
        bannerMessage2.setHeadlineOneText("Another Text for Headline One");
        bannerMessage2.setHeadlineTwo("Another Headline Two");
        bannerMessage2.setHeadlineTwoText("Another Text for Headline Two");
        bannerMessage2.setHeadlineThree("Another Headline Three");
        bannerMessage2.setHeadlineThreeText("Another Text for Headline Three");

        List<BannerMessage> bannerMessages = new ArrayList<>();
        bannerMessages.add(bannerMessage1);
        bannerMessages.add(bannerMessage2);

        List<BannerMessageDto> bannerMessageDtos = bannerMessageTranslator.toDtos(bannerMessages);

        assertEquals(bannerMessages.get(0).getHeadlineOne(), bannerMessageDtos.get(0).getHeadlineOne());
        assertEquals(bannerMessages.get(0).getHeadlineTwo(), bannerMessageDtos.get(0).getHeadlineTwo());
        assertEquals(bannerMessages.get(0).getHeadlineThree(), bannerMessageDtos.get(0).getHeadlineThree());
        assertEquals(bannerMessages.get(0).getHeadlineOneText(), bannerMessageDtos.get(0).getHeadlineOneText());
        assertEquals(bannerMessages.get(0).getHeadlineTwoText(), bannerMessageDtos.get(0).getHeadlineTwoText());
        assertEquals(bannerMessages.get(0).getHeadlineThreeText(), bannerMessageDtos.get(0).getHeadlineThreeText());

        assertEquals(bannerMessages.get(1).getHeadlineOne(), bannerMessageDtos.get(1).getHeadlineOne());
        assertEquals(bannerMessages.get(1).getHeadlineTwo(), bannerMessageDtos.get(1).getHeadlineTwo());
        assertEquals(bannerMessages.get(1).getHeadlineThree(), bannerMessageDtos.get(1).getHeadlineThree());
        assertEquals(bannerMessages.get(1).getHeadlineOneText(), bannerMessageDtos.get(1).getHeadlineOneText());
        assertEquals(bannerMessages.get(1).getHeadlineTwoText(), bannerMessageDtos.get(1).getHeadlineTwoText());
        assertEquals(bannerMessages.get(1).getHeadlineThreeText(), bannerMessageDtos.get(1).getHeadlineThreeText());
    }

    @Test
    public void testToDtos_Null(){

        List<BannerMessageDto> bannerMessageDtos = bannerMessageTranslator.toDtos(null);
        assertTrue(bannerMessageDtos.isEmpty());
    }

    @Test
    public void testToDtos_EmptyList(){

        List<BannerMessageDto> bannerMessageDtos = bannerMessageTranslator.toDtos(new ArrayList<>());
        assertTrue(bannerMessageDtos.isEmpty());
    }

    @Test
    public void testToEntity(){

        BannerMessageDto bannerMessageDto = new BannerMessageDto();
        bannerMessageDto.setHeadlineOne("Headline One");
        bannerMessageDto.setHeadlineOneText("Text for Headline One");
        bannerMessageDto.setHeadlineTwo("Headline Two");
        bannerMessageDto.setHeadlineTwoText("Text for Headline Two");
        bannerMessageDto.setHeadlineThree("Headline Three");
        bannerMessageDto.setHeadlineThreeText("Text for Headline Three");

        BannerMessage bannerMessage = bannerMessageTranslator.toEntity(bannerMessageDto);

        assertEquals(bannerMessageDto.getHeadlineOne(), bannerMessage.getHeadlineOne());
        assertEquals(bannerMessageDto.getHeadlineTwo(), bannerMessage.getHeadlineTwo());
        assertEquals(bannerMessageDto.getHeadlineThree(), bannerMessage.getHeadlineThree());
        assertEquals(bannerMessageDto.getHeadlineOneText(), bannerMessage.getHeadlineOneText());
        assertEquals(bannerMessageDto.getHeadlineTwoText(), bannerMessage.getHeadlineTwoText());
        assertEquals(bannerMessageDto.getHeadlineThreeText(), bannerMessage.getHeadlineThreeText());
    }

    @Test
    public void testToEntity_Null(){

        BannerMessage bannerMessage = bannerMessageTranslator.toEntity(null);
        assertNull(bannerMessage);
    }
}
