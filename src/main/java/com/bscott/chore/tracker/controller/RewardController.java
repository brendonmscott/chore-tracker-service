package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.dto.RewardDto;
import com.bscott.chore.tracker.service.RewardService;
import com.bscott.chore.tracker.translator.RewardTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "/rewards")
@RequestMapping("/rewards")
@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private RewardTranslator rewardTranslator;

    @ApiOperation(value = "Get a reward by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reward was retrieved successfully")})
    @GetMapping("/{id}")
    public ResponseEntity getReward(@ApiParam(value = "The rewardId to find", required = true)
                                   @PathVariable("id") Integer id) {

        Reward reward = rewardService.findReward(id);

        if (reward == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rewardTranslator.toDto(reward));
    }

    @ApiOperation(value = "Get Rewards by Owner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rewards were retrieved successfully")})
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RewardDto>> findRewardsByOwner(@ApiParam(name = "type") @PathVariable("ownerId") Integer ownerId) {

        List<Reward> rewards = rewardService.getRewardsByOwner(ownerId);

        if (rewards == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(rewardTranslator.toDtos(rewards));
    }

    @ApiOperation(value = "Get Rewards by Assignee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rewards were retrieved successfully")})
    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<RewardDto>> findRewardsByAssignee(@ApiParam(name = "type") @PathVariable("assigneeId") Integer assigneeId) {

        List<Reward> rewards = rewardService.getRewardsByAssignee(assigneeId);

        if (rewards == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(rewardTranslator.toDtos(rewards));
    }

    @ApiOperation(value = "Add a new reward")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reward was added successfully")})
    @PostMapping
    public ResponseEntity<RewardDto> addReward(@ApiParam(value = "The reward to add", required = true)
                                        @RequestBody RewardDto rewardDto) {

        Reward newReward = rewardService.addReward(rewardTranslator.toEntity(rewardDto));

        return ResponseEntity.ok(rewardTranslator.toDto(newReward));
    }

    @ApiOperation(value = "Update a reward")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reward was updated successfully")})
    @PutMapping
    public ResponseEntity<RewardDto> updateReward(@ApiParam(value = "The reward to update", required = true)
                                           @RequestBody RewardDto rewardDto) {

        Reward updatedReward = rewardService.updateReward(rewardTranslator.toEntity(rewardDto));
        return ResponseEntity.ok(rewardTranslator.toDto(updatedReward));
    }

    @ApiOperation(value = "Delete a reward by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Reward was deleted successfully")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReward(@ApiParam(value = "The rewardId to delete", required = true)
                                @PathVariable("id") Integer id) {

        rewardService.deleteReward(id);
        return ResponseEntity.noContent().build();
    }
}
