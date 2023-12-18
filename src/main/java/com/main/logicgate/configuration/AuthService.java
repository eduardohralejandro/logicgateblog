package com.main.logicgate.configuration;

import com.main.logicgate.dto.AuthenticationrRequestDTO;
import com.main.logicgate.dto.RegisterRequestDTO;
import com.main.logicgate.dto.AuthenticationResponseDTO;
import com.main.logicgate.model.UserModel;
import com.main.logicgate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {

        UserModel newUser = new UserModel();
        newUser.setName(request.getName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPhoto(request.getPhoto());
        newUser.setUserRole(request.getUserRole());
        newUser.setDateCreated(request.getDateCreated());
        newUser.setArticles(request.getArticles());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();

    }

    public AuthenticationResponseDTO authenticate(AuthenticationrRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();

    }
}
