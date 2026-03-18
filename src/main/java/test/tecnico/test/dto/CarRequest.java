package test.tecnico.test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarRequest {

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Min(value = 1900, message = "El año no es válido")
    private int anio;

    @NotBlank(message = "La placa es obligatoria")
    private String placa;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    private String foto;
}