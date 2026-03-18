package test.tecnico.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")@Email
    private String email;

    @NotBlank @Length(min = 6, message = "La contrasena debe tener mas de 6 caracteres")
    private String password;

}
