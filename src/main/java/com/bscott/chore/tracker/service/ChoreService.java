package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.ChoreRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class ChoreService {

    private ChoreRepository choreRepository;
    private UserService userService;

    @Inject
    public ChoreService(ChoreRepository choreRepository, UserService userService) {
        this.choreRepository = choreRepository;
        this.userService = userService;
    }

    public Chore findChore(Integer choreId) {

        Optional<Chore> chore = choreRepository.findById(choreId);

        return chore.orElse(null);
    }

    public List<Chore> getChores(Integer assigneeId) {

        List<Chore> chores;

        User assignee = userService.findUserById(assigneeId);

        if (assigneeId != null) {
            chores = choreRepository.findChoresByAssigneesEquals(assignee);
        } else {
            chores = choreRepository.findAll();
        }

        return chores;
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
