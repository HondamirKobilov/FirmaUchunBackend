package com.example.firma.Repository;

import com.example.firma.Entity.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IshchiRepository extends JpaRepository<Ishchi, Integer> {
    boolean existsByTelNomer(String telNomer);
}
