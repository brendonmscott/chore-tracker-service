package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoreService {

    private ChoreRepository choreRepository;
    private UserService userService;

    @Autowired
    public ChoreService(ChoreRepository choreRepository, UserService userService) {
        this.choreRepository = choreRepository;
        this.userService = userService;
    }

    public Chore findChore(Integer choreId) {

        Optional<Chore> chore = choreRepository.findById(choreId);

        return chore.orElse(null);
    }

    public List<Chore> getChoresByAssignee(Integer assigneeId) {

        User assignee = userService.findUserById(assigneeId);

        return choreRepository.findChoresByAssigneesEquals(assignee);
    }

    public List<Chore> getChoresByOwner(Integer ownerId) {

        return choreRepository.findChoresByOwnerId(ownerId);
    }

    public Chore addChore(Chore chore) {

        return choreRepository.save(chore);
    }

    public Chore updateChore(Chore updatedChore) {

        Chore chore = findChore(updatedChore.getId());

        if (chore != null) {
            choreRepository.save(updatedChore);
        }

        return updatedChore;
    }

    public void deleteChore(Integer id) {
        choreRepository.deleteById(id);
    }
}
