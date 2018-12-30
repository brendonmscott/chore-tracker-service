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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "/rewards")
@RequestMapping("/rewards")
@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private RewardTranslator rewardTranslator;

    @ApiOperation(value = "Get Rewards")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rewards were retrieved successfully")})
    @GetMapping
    ResponseEntity<List<RewardDto>> findRewards(@ApiParam(name = "type") @RequestParam("type") String type) {

        List<Reward> rewards = rewardService.getRewards(type);

        if (rewards == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(rewardTranslator.toDtos(rewards));
    }

    @ApiOperation(value = "Add a new reward")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reward was added successfully")})
    @PostMapping
    ResponseEntity<RewardDto> addReward(@ApiParam(value = "The reward to add", required = true)
                                        @RequestBody RewardDto rewardDto) {

        Reward newReward = rewardService.addReward(rewardTranslator.toEntity(rewardDto));

        return ResponseEntity.ok(rewardTranslator.toDto(newReward));
    }

    @ApiOperation(value = "Update a reward")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reward was updated successfully")})
    @PutMapping
    ResponseEntity<RewardDto> updateReward(@ApiParam(value = "The reward to update", required = true)
                                           @RequestBody RewardDto rewardDto) {

        Reward updatedReward = rewardService.updateReward(rewardTranslator.toEntity(rewardDto));
        return ResponseEntity.ok(rewardTranslator.toDto(updatedReward));
    }

    @ApiOperation(value = "Delete a reward by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Reward was deleted successfully")})
    @DeleteMapping("/{id}")
    ResponseEntity deleteReward(@ApiParam(value = "The rewardId to delete", required = true)
                                @PathVariable("id") String id) {

        rewardService.deleteReward(id);
        return ResponseEntity.noContent().build();
    }
}
