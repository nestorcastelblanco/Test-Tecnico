package test.tecnico.test.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    private int anio;

    private String placa;

    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}