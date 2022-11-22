package com.example.firma.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ishchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fish;

    @Column(nullable = false, unique = true)
    private String telNomer;

    @OneToOne
    Manzil manzil;

    @OneToOne
    Bolim bolim;
}
