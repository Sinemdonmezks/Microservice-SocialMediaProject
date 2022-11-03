package com.sinem.grapgql.mutation;

import com.sinem.grapgql.model.UserProfileInput;
import com.sinem.mapper.IUserMapper;
import com.sinem.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {
    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput profile){
        userProfileService.save(IUserMapper.INSTANCE.toUserProfile(profile));
        return true;
    }
    }


