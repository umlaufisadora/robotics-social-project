package br.com.srobots_satlas.mini_s.user.service;

import br.com.srobots_satlas.mini_s.auth.config.SecurityConfig;
import br.com.srobots_satlas.mini_s.auth.decorator.SecurityUser;
import br.com.srobots_satlas.mini_s.auth.dto.LoginUserDto;
import br.com.srobots_satlas.mini_s.auth.dto.RecoveryJwtTokenDto;
import br.com.srobots_satlas.mini_s.auth.service.JwtTokenService;
import br.com.srobots_satlas.mini_s.user.dto.CreateUserDto;
import br.com.srobots_satlas.mini_s.user.entity.User;
import br.com.srobots_satlas.mini_s.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDto createUserDto)
    {
        User newUser = User.builder()
                .username(createUserDto.username())
                .email(createUserDto.email())
                .password(createUserDto.password())
                .authority(createUserDto.role())
                .build();
    }
}
