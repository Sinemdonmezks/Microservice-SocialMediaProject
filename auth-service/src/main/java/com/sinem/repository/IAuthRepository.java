package com.sinem.repository;

import com.sinem.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
@Query("select count(a.username)>0 from Auth as a where a.username=?1")
    Boolean existUsername(String username);

    Optional<Auth> findOptionalByEmailAndPassword(String email,String password);

}
