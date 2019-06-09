package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.BannerMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerMessageRepository extends JpaRepository<BannerMessage, Integer> {
}
