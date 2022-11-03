package com.sinem.controller;

import com.sinem.dto.request.ActivateRequestDto;
import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.dto.response.LoginResponseDto;
import com.sinem.dto.response.RegisterResponseDto;
import com.sinem.dto.response.RoleResponseDto;
import com.sinem.repository.entity.Auth;
import com.sinem.repository.enums.Roles;
import com.sinem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import static com.sinem.constants.ApiUrls.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping (REGISTER)
    @Operation(summary = "Kullanıcı kaydeden method")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping (LOGIN)
    @Operation(summary = "Kullanıcı giriş method")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto).get());
    }
@PostMapping(ACTIVATE)
public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
       return ResponseEntity.ok(authService.activateStatus(dto));

}
@GetMapping(GETALL)
public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
}

@GetMapping("/redis")
public ResponseEntity<String> getValue(String value){
        return ResponseEntity.ok(authService.getValue(value));
}

@GetMapping("/finbyrole/{roles}")
public List<RoleResponseDto> findallbyrole(@PathVariable String roles){
      return authService.findbyrole(roles);
}
}
