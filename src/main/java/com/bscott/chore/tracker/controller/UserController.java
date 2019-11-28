package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.UserDto;
import com.bscott.chore.tracker.service.UserService;
import com.bscott.chore.tracker.translator.UserTranslator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(allowCredentials = "true")
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
    public ResponseEntity<UserDto> findUserById(
            @ApiParam(value = "The id of the User to find", required = true)
            @PathVariable("id") Integer id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userTranslator.toDto(user));
    }

    @ApiOperation(value = "Find a user by search criteria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was retrieved successfully")})
    @GetMapping
    public ResponseEntity<UserDto> findUser(
            @ApiParam(value = "The username of the User to find")
            @RequestParam(value = "username", required = false) String username) {

        User user = userService.findUser(username);
        log.info("User found by query: {}", user);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userTranslator.toDto(user));
    }

    @ApiOperation(value = "Add a family member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Family member was added successfully")})
    @Valid
    @PostMapping("/{id}/familyMembers")
    public ResponseEntity<UserDto> addFamilyMember(
            @ApiParam(value = "The userId to add a family member to", required = true)
            @PathVariable("id") Integer id,
            @ApiParam(value = "The family member to add", required = true)
            @RequestBody UserDto familyMember) {

        User updatedUser = userService.addFamilyMember(id, userTranslator.toEntity(familyMember));
        return ResponseEntity.ok(userTranslator.toDto(updatedUser));
    }

    @ApiOperation(value = "Remove a Family Member by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Family Member was deleted successfully")})
    @DeleteMapping("/{id}/familyMembers/{familyMemberId}")
    public ResponseEntity deleteFamilyMember(@ApiParam(value = "The user Id to delete a family member for", required = true)
                                      @PathVariable("id") Integer userId,
                                      @ApiParam(value = "The family member Id to delete", required = true)
                                      @PathVariable("familyMemberId") Integer familyMemberId) {

        userService.removeFamilyMember(userId, familyMemberId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update a family member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Family Member was updated successfully")})
    @Valid
    @PutMapping("/{id}/familyMembers")
    public ResponseEntity<UserDto> updateFamilyMember(
            @ApiParam(value = "The user Id to update a family member for", required = true)
            @PathVariable("id") Integer userId,
            @ApiParam(value = "The family member to update", required = true)
            @RequestBody UserDto familyMember) {

        User updatedUser = userService.updateFamilyMember(userId, userTranslator.toEntity(familyMember));
        return ResponseEntity.ok(userTranslator.toDto(updatedUser));
    }

    @ApiOperation(value = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully")})
    @Valid
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@ApiParam(value = "The user to update", required = true)
                                       @RequestBody UserDto userDto) {

        User updatedUser = userService.updateUser(userTranslator.toEntity(userDto));
        return ResponseEntity.ok(userTranslator.toDto(updatedUser));
    }

    @ApiOperation(value = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User was deleted successfully")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@ApiParam(value = "The userId to delete", required = true)
                              @PathVariable("id") Integer id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
