package test.tecnico.test.dto;

import lombok.Data;

@Data
public class CarRequest {

    private String marca;
    private String modelo;
    private int anio;
    private String placa;
    private String color;

}