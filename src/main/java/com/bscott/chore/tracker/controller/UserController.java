package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.UserDto;
import com.bscott.chore.tracker.service.UserService;
import com.bscott.chore.tracker.translator.AccountTranslator;
import com.bscott.chore.tracker.translator.UserTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "/users")
@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTranslator userTranslator;

    @ApiOperation(value = "Get a User by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was retrieved successfully")})
    @GetMapping("/{id}")
    ResponseEntity<UserDto> findUserById(
            @ApiParam(value = "The id of the User to find", required = true)
            @RequestParam("id") String id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userTranslator.toDto(user));
    }

    @ApiOperation(value = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully")})
    @Valid
    @PutMapping
    ResponseEntity<UserDto> updateUser(@ApiParam(value = "The user to update", required = true)
                                       @RequestBody UserDto userDto) {

        User updatedUser = userService.updateUser(userTranslator.toEntity(userDto));
        return ResponseEntity.ok(userTranslator.toDto(updatedUser));
    }

    @ApiOperation(value = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User was deleted successfully")})
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@ApiParam(value = "The userId to delete", required = true)
                              @RequestParam("id") String id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
