package com.sinem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    @Email(message = "Email formata uygun değil.")
    @Size(min=8, max=64, message = "en az 8 ,en çok 64 karakter olmalı")
    @NotBlank(message = "Kullanıcı adı girilmelidir")
    private String email;
    @Size(min=8, max=64, message = "en az 8 ,en çok 64 karakter olmalı")
    @NotBlank(message = "şifre girilmelidir")
    private String password;

}
