package com.sinem.manager;

import com.sinem.dto.NewCreateUserDto;

import com.sinem.dto.request.ActivateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sinem.constants.ApiUrls.*;


@FeignClient(name = "user-profile-service",
        url= "${myapplication.feign.user}/user",
        decode404 = true)
public interface IUserManager {
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto);

    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activetedUser(@RequestBody ActivateRequestDto dto);


    @PostMapping(ACTIVATEDSTATUSBYID)
    public ResponseEntity<Boolean> activetedStatus(@PathVariable Long authid);

}
