package br.com.srobots_satlas.mini_s.user.dto;

import javax.management.relation.Role;
import java.util.UUID;

public record RecoveryUserDto(
        UUID id,
        String email,
        Role role
) {
}
