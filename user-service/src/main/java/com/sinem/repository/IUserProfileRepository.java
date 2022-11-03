package com.sinem.repository;

import com.sinem.repository.entity.UserProfile;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String > {

    Optional<UserProfile> findOptionalByAuthid(Long id);
    Optional<UserProfile> findOptionalByUsername(String username);
    Optional<UserProfile> findOptionalByUsernameEqualsIgnoreCase(String username);

    @Query("select u from UserProfile as u where u.status='ACTIVE'")
    List<UserProfile> getActiveProfile();


}
