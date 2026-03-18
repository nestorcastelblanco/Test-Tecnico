package test.tecnico.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarResponse {
    private Long id;
    private String marca;
    private String modelo;
    private int anio;
    private String placa;
    private String color;
    private String foto;
}