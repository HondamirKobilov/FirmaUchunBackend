package com.example.firma.Controller;

import com.example.firma.DTO.BolimDTO;
import com.example.firma.DTO.FirmaAPIresponse;
import com.example.firma.SERVIS.BolimServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bolimAPI")
public class BolimController {
    @Autowired
    BolimServis bolimServis;

    @PostMapping("/boliJoylash")
    public HttpEntity<?> post(@RequestBody BolimDTO bolimDTO){
        FirmaAPIresponse firmaAPIresponse = bolimServis.bolimPost(bolimDTO);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("olishBolim")
    public HttpEntity<?> getBolim(){
        FirmaAPIresponse firmaAPIresponse = bolimServis.bolimGet();
        return ResponseEntity.status(HttpStatus.OK).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("/olish/{id}")
    public HttpEntity<?> getbolimId(@PathVariable Integer id){
        FirmaAPIresponse firmaAPIresponse = bolimServis.bolimGetId(id);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        FirmaAPIresponse firmaAPIresponse = bolimServis.bolimdelet(id);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
}
