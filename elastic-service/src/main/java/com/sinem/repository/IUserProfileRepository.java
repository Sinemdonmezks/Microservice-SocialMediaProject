package com.sinem.repository;

import com.sinem.repository.entity.UserProfile;
import com.sinem.repository.enums.Status;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,String > {

    List<UserProfile> findByUsernameContainingIgnoreCase(String username);
    List<UserProfile>  findByEmailContainingIgnoreCase(String email);

    List<UserProfile>  findByStatusContainingIgnoreCase(Status status);


}
