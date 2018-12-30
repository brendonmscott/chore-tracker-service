package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.dto.ChoreDto;
import com.bscott.chore.tracker.service.ChoreService;
import com.bscott.chore.tracker.translator.ChoreTranslator;
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

@Api(value = "/chores")
@RequestMapping("/chores")
@RestController
public class ChoreController {

    @Autowired
    private ChoreService choreService;

    @Autowired
    private ChoreTranslator choreTranslator;

    @ApiOperation(value = "Get Chores")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Chores were retrieved successfully")})
    @GetMapping
    ResponseEntity<List<ChoreDto>> findChores(@ApiParam(name="assigneeId")
                                              @RequestParam(value = "assigneeId", required = false) String assigneeId) {

        List<Chore> chores = choreService.getChores(assigneeId);

        if (chores == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(choreTranslator.toDtos(chores));
    }

    @ApiOperation(value = "Add a new chore")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Chore was added successfully")})
    @PostMapping
    ResponseEntity<ChoreDto> addChore(@ApiParam(value = "The chore to add", required = true)
                                      @RequestBody ChoreDto choreDto) {

        Chore newChore = choreService.addChore(choreTranslator.toEntity(choreDto));
        return ResponseEntity.ok(choreTranslator.toDto(newChore));
    }

    @ApiOperation(value = "Update a chore")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Chore was updated successfully")})
    @PutMapping
    ResponseEntity<ChoreDto> updateChore(@ApiParam(value = "The chore to update", required = true)
                                         @RequestBody ChoreDto choreDto) {

        Chore updatedChore = choreService.updateChore(choreTranslator.toEntity(choreDto));
        return ResponseEntity.ok(choreTranslator.toDto(updatedChore));
    }

    @ApiOperation(value = "Delete a chore by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Chore was deleted successfully")})
    @DeleteMapping("/{id}")
    ResponseEntity deleteChore(@ApiParam(value = "The choreId to delete", required = true)
                               @PathVariable("id") String id) {

        choreService.deleteChore(id);
        return ResponseEntity.noContent().build();
    }
}
