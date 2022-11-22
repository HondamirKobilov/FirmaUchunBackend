package com.example.firma.Repository;

import com.example.firma.Entity.Bolim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BolimRepository extends JpaRepository<Bolim, Integer> {
    Optional<Bolim> findByBolimNomiAndFirma_Id(String bolimNomi, Integer firma_id);
}
