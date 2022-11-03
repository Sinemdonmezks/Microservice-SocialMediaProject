package com.sinem.controller;

import com.sinem.dto.response.UserProfileResponseDto;
import com.sinem.mapper.IUserMapper;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(CREATE)
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDto userProfiledto){
        return ResponseEntity.ok(userProfileService
                .save(IUserMapper.INSTANCE.toUserProfile(userProfiledto)));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody UserProfileResponseDto userProfiledto){
        return ResponseEntity.ok(userProfileService
                .save(IUserMapper.INSTANCE.toUserProfile(userProfiledto)));
    }


}