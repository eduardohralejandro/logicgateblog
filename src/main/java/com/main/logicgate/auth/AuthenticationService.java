package com.main.logicgate.auth;

import com.main.logicgate.author.Author;
import com.main.logicgate.author.AuthorRepository;
import com.main.logicgate.common.enums.AuthorRole;
import com.main.logicgate.exception.BadRequestException;
import com.main.logicgate.exception.NotFoundException;
import com.main.logicgate.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(Author request) {
        var user = Author.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorRole(AuthorRole.REGULAR)
                .build();
        try {
            authorRepository.save(user);
        } catch (BadRequestException exception) {
            throw new BadRequestException("It was not possible to register the user " + exception);
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = authorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken)
                .build();
    }

}
