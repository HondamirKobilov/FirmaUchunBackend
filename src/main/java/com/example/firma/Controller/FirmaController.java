package com.example.firma.Controller;

import com.example.firma.DTO.FirmaAPIresponse;
import com.example.firma.DTO.FirmaDto;
import com.example.firma.Entity.Firma;
import com.example.firma.SERVIS.FirmaServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firmaAPI")
public class FirmaController {
    @Autowired
    FirmaServis firmaServis;

    @PostMapping("/firmaJoylash")
    public HttpEntity<?> post(@RequestBody FirmaDto firmaDto){
      FirmaAPIresponse firmaAPIresponse= firmaServis.firmaPost(firmaDto);
      return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("/olish")
    public HttpEntity<?> getFirma(){
        FirmaAPIresponse firmaAPIresponse = firmaServis.getFirma();
        return ResponseEntity.status(HttpStatus.OK).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("/olish/{id}")
    public HttpEntity<?> getfirmaId(@PathVariable Integer id){
        FirmaAPIresponse firmaAPIresponse = firmaServis.firmagetId(id);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }

    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        FirmaAPIresponse firmaAPIresponse = firmaServis.deletFirma(id);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }

}
