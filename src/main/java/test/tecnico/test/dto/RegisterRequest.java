package test.tecnico.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterRequest {
    @NotBlank(message = "El tamano del usuario no es adecuado") @Length(max = 20)
    private String username;
    @NotBlank @Length(max = 30, message = "Ingrese un correo valido") @Email
    private String email;
    @NotBlank @Length(min = 6, message = "La contrasena debe tener mas de 6 caracteres")
    private String password;

}
