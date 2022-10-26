package com.sinem.repository;

import com.sinem.repository.entity.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,Long> {



}
