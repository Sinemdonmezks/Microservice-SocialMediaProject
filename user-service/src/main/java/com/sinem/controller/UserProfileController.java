package com.sinem.controller;

import com.sinem.dto.NewCreateUserDto;
import com.sinem.exception.AuthServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserProfileController {
    private final UserProfileService userProfileService;
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto){
try{
    userProfileService.createUser(dto);
    return ResponseEntity.ok(true);
}catch (Exception e){
    throw  new AuthServiceException(ErrorType.INVALID_ACTIVATE_CODE);

}
    }
}
