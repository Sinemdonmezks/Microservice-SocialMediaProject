package com.sinem.controller;

import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sinem.constants.ApiUrls.*;
@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> getall(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}