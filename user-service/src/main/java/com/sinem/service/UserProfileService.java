package com.sinem.service;

import com.sinem.dto.request.ActivateRequestDto;
import com.sinem.dto.request.NewCreateUserDto;
import com.sinem.dto.request.UpdateRequestDto;
import com.sinem.dto.response.RoleResponseDto;
import com.sinem.dto.response.UserProfileRedisResponseDto;
import com.sinem.exception.UserServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.manager.IAuthManager;
import com.sinem.manager.IElasticManager;
import com.sinem.mapper.IUserMapper;
import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.repository.enums.Status;
import com.sinem.utility.JwtTokenManager;
import com.sinem.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String > {
    private final IUserProfileRepository userProfileRepository;
    private final JwtTokenManager tokenManager;
    private final IAuthManager authManager;
    @Autowired
    private final CacheManager cacheManager;
    private final IElasticManager elasticManager;
    public UserProfileService(IUserProfileRepository userProfileRepository,
                              JwtTokenManager tokenManager,CacheManager cacheManager,
                              IAuthManager authManager,IElasticManager elasticManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.tokenManager = tokenManager;
        this.cacheManager=cacheManager;
        this.authManager=authManager;
        this.elasticManager=elasticManager;
    }

    public UserProfile createUser(NewCreateUserDto dto) {
        UserProfile userProfile=save(IUserMapper.INSTANCE.toUserProfile(dto));
      elasticManager.createUser(IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile));
        return userProfile;

    }

    public boolean activatedUser(ActivateRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(Long.valueOf(dto.getId()));
        if (userProfile.isPresent()) {
            userProfile.get().setStatus(Status.ACTIVE);
            save(userProfile.get());
            return true;
        } else {
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
    }

    public boolean activatedStatus(Long authid) {
        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(authid);
        if (userProfile.isPresent()) {
            userProfile.get().setStatus(Status.ACTIVE);
            save(userProfile.get());
            return true;
        } else {
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
    }

    public boolean updateUser(UpdateRequestDto dto) {
        Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
        UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
        if (authid.isPresent()) {
            Optional<UserProfile> userProfileDb = userProfileRepository.findOptionalByAuthid(authid.get());
            if (userProfileDb.isPresent()) {
                userProfileDb.get().setEmail(dto.getEmail());
                userProfileDb.get().setAddress(dto.getAddress());
                userProfileDb.get().setAbout(dto.getAbout());
                userProfileDb.get().setName(dto.getName());
                userProfileDb.get().setUsername(dto.getUsername());
                userProfileDb.get().setPhone(dto.getPhone());
                userProfileDb.get().setPhoto(dto.getPhoto());
                userProfileDb.get().setUpdated(System.currentTimeMillis());
                elasticManager.update(IUserMapper.INSTANCE.toUserProfileResponseDto(userProfileDb.get()));
                save(userProfileDb.get());
                return true;
            } else {
                throw new UserServiceException(ErrorType.INTERNAL_ERROR);
            }
        } else {
            throw new UserServiceException(ErrorType.INTERNAL_ERROR);
        }
    }


@Cacheable(value="findbyusername",key = "#username.toUpperCase()")
    public UserProfileRedisResponseDto findByUsername(String username) {
        Optional<UserProfile> profile = userProfileRepository
                .findOptionalByUsernameEqualsIgnoreCase(username);
        if (profile.isPresent()) {
            return IUserMapper.INSTANCE
                    .toUserProfileRedisResponseDto(profile.get());
        } else {
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
    }


    public boolean updateUserForRedis(UpdateRequestDto dto) {
        Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
        UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
        if (authid.isPresent()) {
            Optional<UserProfile> userProfileDb = userProfileRepository.findOptionalByAuthid(authid.get());
            if (userProfileDb.isPresent()) {
                cacheManager.getCache("findbyusername").evict(userProfileDb.get().getUsername());
                userProfileDb.get().setEmail(dto.getEmail());
                userProfileDb.get().setAddress(dto.getAddress());
                userProfileDb.get().setAbout(dto.getAbout());
                userProfileDb.get().setName(dto.getName());
                userProfileDb.get().setUsername(dto.getUsername());
                userProfileDb.get().setPhone(dto.getPhone());
                userProfileDb.get().setPhoto(dto.getPhoto());
                save(userProfileDb.get());
                return true;
            } else {
                throw new UserServiceException(ErrorType.INTERNAL_ERROR);
            }
        } else {
            throw new UserServiceException(ErrorType.INTERNAL_ERROR);
        }
    }
@Cacheable(value = "findallactiveprofile")
    public List<UserProfile> findAllActiveProfile() {
       return userProfileRepository.getActiveProfile();
    }

    @Cacheable(value = "findbyrole",key = "#roles.toUpperCase()")
    public List<RoleResponseDto> findByRole(String roles) {

        return authManager.findAllByRole(roles).getBody();
    }

    public Page<UserProfile> getAllPage(int pageSize, int currentPageNumber, String sortParameter, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortParameter);
        Pageable pageable = PageRequest.of(currentPageNumber,pageSize,sort);
        return userProfileRepository.findAll(pageable);
    }
}
