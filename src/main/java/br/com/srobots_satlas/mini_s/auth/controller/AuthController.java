package br.com.srobots_satlas.mini_s.auth.controller;

import br.com.srobots_satlas.mini_s.auth.dto.LoginUserDto;
import br.com.srobots_satlas.mini_s.auth.dto.RecoveryJwtTokenDto;
import br.com.srobots_satlas.mini_s.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController
{
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(
            @RequestBody @Valid LoginUserDto loginUserDto
            )
    {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
