package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.repository.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoreService {

    @Autowired
    private ChoreRepository choreRepository;

    public Chore findChore(String choreId) {

        Optional<Chore> chore = choreRepository.findById(choreId);

        return chore.orElse(null);
    }

    public List<Chore> getChores(String assigneeId) {

        List<Chore> chores;

        if (assigneeId != null) {
            chores = choreRepository.findChoresByAssigneeId(assigneeId);
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

    public void deleteChore(String id) {
        choreRepository.deleteById(id);
    }
}
