package com.shrivarshapoojary.in.springly.service;


import com.shrivarshapoojary.in.springly.dto.LoginRequest;
import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.repository.UserRepository;
import com.shrivarshapoojary.in.springly.security.jwt.JwtAuthenticationResponse;
import com.shrivarshapoojary.in.springly.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public User registerUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }


    public JwtAuthenticationResponse login(LoginRequest req)
    {

        Authentication authentication=authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails=(UserDetailsImpl)  authentication.getPrincipal();

        String jwt=jwtUtils.generateToken(userDetails);

        return new JwtAuthenticationResponse(jwt);

    }

    public  User findByUsername(String name) throws Exception {

         return  userRepository.findByUsername(name).orElseThrow(()->new Exception( "User not found"));

    }
}
