package com.example.firma.DTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class FirmaDto {
    private String firmaNomi;
    private String direktorNomi;
    private String vil;
    private String tum;
    private String kucha;
    private String uyRaqam;
}
