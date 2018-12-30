package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.BannerMessage;
import com.bscott.chore.tracker.repository.BannerMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private BannerMessageRepository bannerMessageRepository;

    public BannerMessage findBannerMessageById(String id){
        Optional<BannerMessage> bannerMessage = bannerMessageRepository.findById(id);
        return bannerMessage.orElse(null);
    }

    public List<BannerMessage> getBannerMessages(){
        List<BannerMessage> bannerMessages;
        bannerMessages = bannerMessageRepository.findAll();

        return bannerMessages;
    }

    public BannerMessage addBannerMessage(BannerMessage bannerMessage) {

        return bannerMessageRepository.save(bannerMessage);
    }

    public BannerMessage updateBannerMessage(BannerMessage bannerMessage) {

        bannerMessageRepository.save(bannerMessage);

        return bannerMessage;
    }

    public void deleteBannerMessage(String id) {
        bannerMessageRepository.deleteById(id);
    }
}
