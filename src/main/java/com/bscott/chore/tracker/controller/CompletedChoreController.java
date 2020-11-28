package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.CompletedChore;
import com.bscott.chore.tracker.dto.CompletedChoreDto;
import com.bscott.chore.tracker.service.CompletedChoreService;
import com.bscott.chore.tracker.translator.CompletedChoreTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(allowCredentials = "true")
@Api(value = "/completed-chores")
@RequestMapping("/completed-chores")
@RestController
@Slf4j
public class CompletedChoreController {

    @Autowired
    private CompletedChoreService completedChoreService;

    @Autowired
    private CompletedChoreTranslator completedChoreTranslator;

    @ApiOperation(value = "Get a completed chore by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed Chore was retrieved successfully")})
    @GetMapping("/{id}")
    public ResponseEntity<CompletedChoreDto> getCompletedChore(@ApiParam(value = "The choreId to find", required = true)
                               @PathVariable("id") Integer id) {

        CompletedChore completedChore = completedChoreService.findChore(id);

        if (completedChore == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(completedChoreTranslator.toDto(completedChore));
    }

    @ApiOperation(value = "Get Completed Chores by Assignee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed Chores were retrieved successfully")})
    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<CompletedChoreDto>> findCompletedChoresByAssignee(
            @ApiParam(name="assigneeId", required = true)
            @PathVariable(value = "assigneeId") Integer assigneeId) {

        List<CompletedChore> completedChores = completedChoreService.getCompletedChoresByAssignee(assigneeId);

        if (completedChores == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(completedChoreTranslator.toDtos(completedChores));
    }

    @ApiOperation(value = "Get Completed Chores for all Family Members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed Chores were retrieved successfully")})
    @GetMapping("/family/{ownerId}")
    public ResponseEntity<List<CompletedChoreDto>> findCompletedChoresforFamily(
            @ApiParam(name="ownerId", required = true)
            @PathVariable(value = "ownerId") Integer ownerId) {

        List<CompletedChore> completedChores = completedChoreService.getCompletedChoresForFamily(ownerId);

        if (completedChores == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(completedChoreTranslator.toDtos(completedChores));
    }

    @ApiOperation(value = "Add a new completed chore")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed Chore was added successfully")})
    @PostMapping
    public ResponseEntity<CompletedChoreDto> addCompletedChore(
            @ApiParam(value = "The chore to add", required = true)
            @RequestBody CompletedChoreDto completedChoreDto) {

        CompletedChore newCompletedChore = completedChoreService.addCompletedChore(completedChoreTranslator.toEntity(completedChoreDto));
        return ResponseEntity.ok(completedChoreTranslator.toDto(newCompletedChore));
    }

    @ApiOperation(value = "Update a completed chore")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed Chore was updated successfully")})
    @PutMapping
    public ResponseEntity<CompletedChoreDto> updateCompletedChore(@ApiParam(value = "The completed chore to update", required = true)
                                         @RequestBody CompletedChoreDto completedChoreDto) {

        log.info("Update Completed Chore: {}", completedChoreDto);
        CompletedChore updatedCompletedChore = completedChoreService.updateCompletedChore(completedChoreTranslator.toEntity(completedChoreDto));
        return ResponseEntity.ok(completedChoreTranslator.toDto(updatedCompletedChore));
    }

    @ApiOperation(value = "Delete a completed chore by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Completed Chore was deleted successfully")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompletedChore(@ApiParam(value = "The completedChoreId to delete", required = true)
                               @PathVariable("id") Integer id) {

        completedChoreService.deleteCompletedChore(id);
        return ResponseEntity.noContent().build();
    }
}
