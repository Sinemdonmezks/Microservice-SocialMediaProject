package com.sinem.manager;

import com.sinem.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.sinem.constants.ApiUrls.GETALL;

@FeignClient(name="user-service",url="${myapplication.feign.user}/user",decode404 = true)
public interface IUserProfileManager {
    @GetMapping(GETALL)
     ResponseEntity<List<UserProfile>> findAll();
}
