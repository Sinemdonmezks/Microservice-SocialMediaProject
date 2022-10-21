package com.sinem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotBlank
    @Size(min=3,max=32, message = "kullanıcı adı min 3 karakter en fazla 32 karakter olabilir")
    private String username;
    @NotBlank
     @Size(min=8, max=64, message = "en az 8 ,en çok 64 karakter olmalı")
    private String password;
    @Email(message = "Email formata uygun değil.")
    @NotBlank
    private String email;
    private String admincode;
}
