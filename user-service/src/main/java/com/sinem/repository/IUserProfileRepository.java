package com.sinem.repository;

import com.sinem.repository.entity.UserProfile;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findOptionalByAuthid(Long id);
}
