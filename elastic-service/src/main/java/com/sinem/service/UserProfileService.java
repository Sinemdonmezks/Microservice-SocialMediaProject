package com.sinem.service;

import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository){
        super(repository);
        this.repository=repository;
    }
}
