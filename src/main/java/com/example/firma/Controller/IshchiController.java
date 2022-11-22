package com.example.firma.Controller;

import com.example.firma.DTO.FirmaAPIresponse;
import com.example.firma.DTO.IshchiDTO;
import com.example.firma.SERVIS.IshchiServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ishchiAPI")
public class IshchiController {
    @Autowired
    IshchiServis ishchiServis;

    @PostMapping("/ishchiJoylash")
    public HttpEntity<?> post(@RequestBody IshchiDTO ishchiDTO){
        FirmaAPIresponse firmaAPIresponse = ishchiServis.ishchiPost(ishchiDTO);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("olishIshchi")
    public HttpEntity<?> getIshchi(){
        FirmaAPIresponse firmaAPIresponse = ishchiServis.ishchiGet();
        return ResponseEntity.status(HttpStatus.OK).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("/olish/{id}")
    public HttpEntity<?> getbolimId(@PathVariable Integer id){
        FirmaAPIresponse firmaAPIresponse = ishchiServis.ishchigetId(id);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        FirmaAPIresponse firmaAPIresponse = ishchiServis.ishchidelet(id);
        return ResponseEntity.status(firmaAPIresponse.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
}
