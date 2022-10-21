package com.sinem.controller;

import com.sinem.dto.ActivateRequestDto;
import com.sinem.dto.NewCreateUserDto;
import com.sinem.dto.UpdateRequestDto;
import com.sinem.exception.UserServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    throw  new UserServiceException(ErrorType.INVALID_ACTIVATE_CODE);

}
    }
    @PostMapping("/activate")
    public ResponseEntity<Boolean> activetedUser(@RequestBody ActivateRequestDto dto){
        try {
            userProfileService.activatedUser(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw  new UserServiceException(ErrorType.INVALID_ACTIVATE_CODE);

        }
    }

    @PostMapping("/activate/{authid}")
    public ResponseEntity<Boolean> activetedStatus(@PathVariable Long authid){
        try {
            userProfileService.activatedStatus(authid);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw  new UserServiceException(ErrorType.INVALID_ACTIVATE_CODE);

        }
    }

    @PutMapping("/update")

    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }
}
