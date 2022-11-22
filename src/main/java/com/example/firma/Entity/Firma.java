package com.example.firma.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Firma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String firmaNomi;
    @Column(nullable = false)
    private String direktorNomi;

    @OneToOne
    Manzil manzil;
}
