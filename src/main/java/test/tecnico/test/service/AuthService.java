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

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (userRepo.existsByUsuario(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if(request.getPassword().length() < 6){
            throw new RuntimeException("La contraseña no es valida");
        }

        User user = User.builder()
                .usuario(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);

        return new AuthResponse(null, "Usuario registrado correctamente");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("El correo no está registrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("La contraseña es incorrecta");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, "Inicio de sesión exitoso");
    }
}