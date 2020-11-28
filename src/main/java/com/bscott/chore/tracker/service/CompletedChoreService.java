package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.CompletedChore;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.CompletedChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompletedChoreService {

    private final CompletedChoreRepository completedChoreRepository;
    private final UserService userService;

    @Autowired
    public CompletedChoreService(CompletedChoreRepository completedChoreRepository, UserService userService) {
        this.completedChoreRepository = completedChoreRepository;
        this.userService = userService;
    }

    public CompletedChore findChore(Integer choreId) {

        Optional<CompletedChore> completedChore = completedChoreRepository.findById(choreId);

        return completedChore.orElse(null);
    }

    public List<CompletedChore> getCompletedChoresByAssignee(Integer assigneeId) {

        return completedChoreRepository.findCompletedChoresByAssigneeIdEquals(assigneeId);
    }

    public List<CompletedChore> getCompletedChoresForFamily(Integer ownerId) {
        List<CompletedChore> completedChores = new ArrayList<>();

        User owner = userService.findUserById(ownerId);
        owner.getFamilyMembers().forEach(familyMember ->
                completedChores.addAll(completedChoreRepository.findCompletedChoresByAssigneeIdEquals(familyMember.getId())));
        return completedChores;
    }

    public CompletedChore addCompletedChore(CompletedChore completedChore) {

        return completedChoreRepository.save(completedChore);
    }

    public CompletedChore updateCompletedChore(CompletedChore updatedCompletedChore) {

        CompletedChore completedChore = findChore(updatedCompletedChore.getId());

        if (completedChore != null) {
            completedChoreRepository.save(updatedCompletedChore);
        }

        return updatedCompletedChore;
    }

    public void deleteCompletedChore(Integer id) {
        completedChoreRepository.deleteById(id);
    }
}
