package br.com.srobots_satlas.mini_s.user.dto;

import br.com.srobots_satlas.mini_s.user.entity.enums.RoleEnum;

import javax.management.relation.Role;

public record CreateUserDto(
        String username,
        String email,
        String password,
        RoleEnum role
) {
}
