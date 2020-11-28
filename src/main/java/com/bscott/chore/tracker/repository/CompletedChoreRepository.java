package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.CompletedChore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompletedChoreRepository extends JpaRepository<CompletedChore, Integer> {

    Optional<CompletedChore> findById(Integer id);
    List<CompletedChore> findCompletedChoresByAssigneeIdEquals(Integer assigneeId);
}
