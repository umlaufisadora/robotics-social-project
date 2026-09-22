package br.com.srobots_satlas.mini_s.auth.service;

import br.com.srobots_satlas.mini_s.auth.decorator.SecurityUser;
import br.com.srobots_satlas.mini_s.user.entity.User;
import br.com.srobots_satlas.mini_s.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new SecurityUser(user);
    }
}
