package br.com.srobots_satlas.mini_s.auth.filter;

import br.com.srobots_satlas.mini_s.auth.config.SecurityConfig;
import br.com.srobots_satlas.mini_s.auth.decorator.SecurityUser;
import br.com.srobots_satlas.mini_s.auth.service.JwtTokenService;
import br.com.srobots_satlas.mini_s.user.entity.User;
import br.com.srobots_satlas.mini_s.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        if(checkIfEndpointIsNotPublic(request))
        {
            String token = recoveryToken(request);

            if(token != null)
            {
                String subject = jwtTokenService.getSubjectFromToken(token);
                User user = userRepository.findByEmail(subject).get();
                SecurityUser userDetails = new SecurityUser(user);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request)
    {
        String authoriztionHeader = request.getHeader("Authorization");
        if(authoriztionHeader != null)
        {
            return authoriztionHeader.replace("Bearer", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}
