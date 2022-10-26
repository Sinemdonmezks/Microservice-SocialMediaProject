package com.sinem.dto.response;

import com.sinem.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponseDto {
    private Long id;
    private String username;
    private Roles role;
}
