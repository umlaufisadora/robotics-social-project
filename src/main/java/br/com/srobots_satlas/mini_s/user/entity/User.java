package br.com.srobots_satlas.mini_s.user.entity;

import br.com.srobots_satlas.mini_s.user.entity.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Representa um usuário persistido pela aplicação
 * <p>Esta entidade contém os dados internos utilizados para camada de persistência</p>
 */

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum authority;
}
