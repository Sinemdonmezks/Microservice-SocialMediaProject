package com.sinem.manager;

import com.sinem.dto.NewCreateUserDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "user-profile-service",
        url= "http://localhost:8091/user",
        decode404 = true)
public interface IUserManager {
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto);
}
