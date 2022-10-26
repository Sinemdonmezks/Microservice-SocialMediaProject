package com.sinem.utility;

import com.sinem.manager.IUserProfileManager;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllData {
    private final IUserProfileManager userProfileManager;
    private final UserProfileService userProfileService;
    @PostConstruct
    public void init(){
        List<UserProfile> userProfiles= userProfileManager.findAll().getBody();
        userProfileService.saveAll(userProfiles);
    }
}
