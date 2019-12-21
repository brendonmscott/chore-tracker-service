package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChoreRepository extends JpaRepository<Chore, Integer> {

    Optional<Chore> findById(Integer id);
    List<Chore> findChoresByAssigneesEquals(User assignee);
    List<Chore> findChoresByOwnerId(Integer ownerId);
}
