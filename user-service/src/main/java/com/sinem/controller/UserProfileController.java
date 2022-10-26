package com.sinem.controller;

import com.sinem.dto.request.ActivateRequestDto;
import com.sinem.dto.request.NewCreateUserDto;
import com.sinem.dto.request.UpdateRequestDto;
import com.sinem.dto.response.RoleResponseDto;
import com.sinem.dto.response.UserProfileRedisResponseDto;
import com.sinem.exception.UserServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.sinem.constants.ApiUrls.*;

/**
 * 1-status değiştiğinde bizim active status cache temizlensin
 *2-userprofile controllerda bir endpoint yazalım buda bize
 * dışarıdan girdiğimiz role göre user profile dönsün
 * bu metodu cache leyelim
 * 3-bu metod ne zaman değişecek yani bu cache bir metodun içinde yeri geldiği zmaan silelim
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {
    private final UserProfileService userProfileService;
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto){
try{
    userProfileService.createUser(dto);
    return ResponseEntity.ok(true);
}catch (Exception e){
    throw  new UserServiceException(ErrorType.INVALID_ACTIVATE_CODE);

}
    }
    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activetedUser(@RequestBody ActivateRequestDto dto){
        try {
            userProfileService.activatedUser(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw  new UserServiceException(ErrorType.INVALID_ACTIVATE_CODE);

        }
    }

    @PostMapping(ACTIVATEDSTATUSBYID)
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

    @GetMapping(GETALL)
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

@GetMapping("/findbyusername/{username}")
public ResponseEntity<UserProfileRedisResponseDto> findByUsername(@PathVariable String username){

        return ResponseEntity.ok(userProfileService.findByUsername(username));
}

    @PutMapping("/updateredis")

    public ResponseEntity<Boolean> updateUserForRedis(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }

    @GetMapping("/findallActiveprofile")
    public ResponseEntity<List<UserProfile>> findAllActiveProfile(){
        return ResponseEntity.ok(userProfileService.findAllActiveProfile());
    }

    @GetMapping("/findbyrole")
    public ResponseEntity<List<RoleResponseDto>> findAllByRole(String role){
       return ResponseEntity.ok(userProfileService.findByRole(role));
    }
}
