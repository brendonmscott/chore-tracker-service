package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.repository.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoreService {

    @Autowired
    private ChoreRepository choreRepository;

    public List<Chore> getChores(String assigneeId) {

        List<Chore> chores;

        if (assigneeId != null) {
            chores = choreRepository.findChoresByAssignee_Id(assigneeId);
        } else {
            chores = choreRepository.findAll();
        }

        return chores;
    }

    public Chore addChore(Chore chore) {

        return choreRepository.save(chore);
    }

    public Chore updateChore(Chore chore) {

        choreRepository.save(chore);

        return chore;
    }

    public void deleteChore(String id) {
        choreRepository.deleteById(id);
    }
}
