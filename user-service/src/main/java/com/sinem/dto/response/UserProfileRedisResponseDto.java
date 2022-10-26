package com.sinem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserProfileRedisResponseDto implements Serializable {

    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;

}
