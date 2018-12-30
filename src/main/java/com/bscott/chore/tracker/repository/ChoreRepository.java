package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.Chore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChoreRepository extends MongoRepository<Chore, String> {

    List<Chore> findChoresByAssignee_Id(String assigneeId);
}
