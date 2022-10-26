package com.sinem.repository;

import com.sinem.repository.entity.UserProfile;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findOptionalByAuthid(Long id);
    Optional<UserProfile> findOptionalByUsername(String username);
    Optional<UserProfile> findOptionalByUsernameEqualsIgnoreCase(String username);

    @Query("select u from UserProfile as u where u.status='ACTIVE'")
    List<UserProfile> getActiveProfile();


}
