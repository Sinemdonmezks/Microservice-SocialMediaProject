package com.sinem.repository;

import com.sinem.repository.entity.Auth;
import com.sinem.repository.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
@Query("select count(a.username)>0 from Auth as a where a.username=?1")
    Boolean existUsername(String username);

    Optional<Auth> findOptionalByEmailAndPassword(String email,String password);

    List<Auth> findAllByRole(Roles roles);
}
