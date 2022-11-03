package com.sinem.service;

import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.repository.enums.Status;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String > {
    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository){
        super(repository);
        this.repository=repository;
    }


    public List<UserProfile> findAllContainingUsername(String username) {
   return repository.findByUsernameContainingIgnoreCase(username);
    }

   public List<UserProfile> findAllByStatus(String status) {
        return repository.findByStatusContainingIgnoreCase(Status.valueOf(status));
    }
}
