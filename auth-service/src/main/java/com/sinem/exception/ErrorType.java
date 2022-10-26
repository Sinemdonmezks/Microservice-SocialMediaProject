package com.sinem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    GECERSIZ_TOKEN(2002,"Token bilgisi geçersizdir.", INTERNAL_SERVER_ERROR),

    LOGIN_ERROR_WRONG(1000, "Kullanıcı adı yada şifre hatalı", INTERNAL_SERVER_ERROR),
    LOGIN_ERROR_REQUIRED_PASSWORD(1001, "Şifre gereksinimleri karşılamıyor, geçerli bir şifre giriniz", INTERNAL_SERVER_ERROR),
    LOGIN_ERROR_USERNAME_ERROR(1002, "Geçerli bir kullanıcı adı giriniz. ", INTERNAL_SERVER_ERROR),
    LOGIN_ERROR_USERNAME_DUPLICATE(1003, "Bu Kullanıcı adı zaten kullanılıyor.", INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1004, "Kullanıcı bulunamadı", INTERNAL_SERVER_ERROR),
    USER_NOT_CREATED(1005, "Kullanıcı kaydedilemedi", INTERNAL_SERVER_ERROR),
    INVALID_ACTIVATE_CODE(1007,"activated code bulunamadı.",INTERNAL_SERVER_ERROR),
    USERNAME_DUPLICATE(1006, "hata",INTERNAL_SERVER_ERROR ),
    ROLE_NOT_FOUND(1010,"role bulunamadı",INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    HttpStatus httpStatus;

}
