package test.tecnico.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(unique = true, nullable = false)
    private String placa;

    private String color;

    @Column(name = "foto", columnDefinition = "VARCHAR(MAX)")
    private String foto;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"cars", "password", "email"})
    private User user;
}