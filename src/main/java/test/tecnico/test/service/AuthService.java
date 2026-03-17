package test.tecnico.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.tecnico.test.config.JwtUtil;
import test.tecnico.test.dto.AuthResponse;
import test.tecnico.test.dto.LoginRequest;
import test.tecnico.test.dto.RegisterRequest;
import test.tecnico.test.model.User;
import test.tecnico.test.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        User user = User.builder()
                .usuario(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsuario());

        return new AuthResponse(token);
    }
}
