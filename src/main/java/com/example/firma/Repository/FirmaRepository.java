package com.example.firma.Repository;

import com.example.firma.Entity.Firma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirmaRepository extends JpaRepository<Firma,Integer> {
    boolean existsByFirmaNomi(String firmaNomi);
    boolean existsByManzil_UyRaqamAndManzil_Kucha(String manzil_uyRaqam, String manzil_kucha);

    boolean existsById(Integer id);
}
