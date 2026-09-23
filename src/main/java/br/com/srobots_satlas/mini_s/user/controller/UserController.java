package br.com.srobots_satlas.mini_s.user.controller;

import br.com.srobots_satlas.mini_s.user.dto.CreateUserDto;
import br.com.srobots_satlas.mini_s.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto)
    {
        userService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest()
    {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/user")
    public ResponseEntity<String> getUserAuthenticationTest()
    {
        return new ResponseEntity<>("Usuário autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/professor")
    public ResponseEntity<String> getProfessorAuthenticationTest()
    {
        return new ResponseEntity<>("Professor autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest()
    {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }
}
