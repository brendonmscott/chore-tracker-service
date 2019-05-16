package com.bscott.chore.tracker.controller;

import com.bscott.chore.tracker.domain.BannerMessage;
import com.bscott.chore.tracker.dto.BannerMessageDto;
import com.bscott.chore.tracker.service.ContentService;
import com.bscott.chore.tracker.translator.BannerMessageTranslator;
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
@Api(value = "/content")
@RequestMapping("/content")
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private BannerMessageTranslator bannerMessageTranslator;

    @ApiOperation(value = "Get Banner Messages")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Banner messages were retrieved successfully")})
    @GetMapping("/banner/messages")
    public ResponseEntity<List<BannerMessageDto>> getBannerMessages() {

        List<BannerMessage> bannerMessages = contentService.getBannerMessages();

        if (bannerMessages == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        return ResponseEntity.ok(bannerMessageTranslator.toDtos(bannerMessages));
    }

    @ApiOperation(value = "Add a new Banner Message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Chore was added successfully")})
    @PostMapping("/banner/messages")
    public ResponseEntity<BannerMessageDto> addBannerMessage(@ApiParam(value = "The banner messages to add", required = true)
                                                      @RequestBody BannerMessageDto bannerMessageDto) {

        BannerMessage newBannerMessage = contentService.addBannerMessage(bannerMessageTranslator.toEntity(bannerMessageDto));
        return ResponseEntity.ok(bannerMessageTranslator.toDto(newBannerMessage));
    }

    @ApiOperation(value = "Update a Banner Message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Banner Message was updated successfully")})
    @PutMapping("/banner/messages")
    public ResponseEntity updateBannerMessage(@ApiParam(value = "The banner message to update", required = true)
                                       @RequestBody BannerMessageDto bannerMessageDto) {

        BannerMessage updatedBannerMessage = contentService.updateBannerMessage(bannerMessageTranslator.toEntity(bannerMessageDto));
        return ResponseEntity.ok(bannerMessageTranslator.toDto(updatedBannerMessage));
    }

    @ApiOperation(value = "Delete a Banner Message by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Banner Message was deleted successfully")})
    @DeleteMapping("/banner/messages/{id}")
    public ResponseEntity deleteBannerMessage(@ApiParam(value = "The bannerMessageId to delete", required = true)
                                       @PathVariable("id") String id) {

        contentService.deleteBannerMessage(id);
        return ResponseEntity.noContent().build();
    }
}
