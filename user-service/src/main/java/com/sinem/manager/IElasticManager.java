package com.sinem.manager;

import com.sinem.dto.response.UserProfileResponseDto;
import com.sinem.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sinem.constants.ApiUrls.CREATE;
import static com.sinem.constants.ApiUrls.UPDATE;

@FeignClient(url ="${myapplication.feign.elastic}/elastic" , name = "elastic-service",decode404 = true)

public interface IElasticManager {

    @PostMapping(CREATE)
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDto userProfiledto);

    @PutMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody UserProfileResponseDto userProfiledto);
}
