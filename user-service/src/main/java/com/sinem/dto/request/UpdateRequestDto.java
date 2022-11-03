package com.sinem.dto.request;

import com.sinem.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateRequestDto {

    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    @NotNull
    String token;


}
