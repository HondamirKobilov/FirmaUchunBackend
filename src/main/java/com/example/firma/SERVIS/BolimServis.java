package com.example.firma.SERVIS;

import com.example.firma.DTO.BolimDTO;
import com.example.firma.DTO.FirmaAPIresponse;
import com.example.firma.Entity.Bolim;
import com.example.firma.Entity.Firma;
import com.example.firma.Entity.Manzil;
import com.example.firma.Repository.BolimRepository;
import com.example.firma.Repository.FirmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BolimServis {
    @Autowired
    BolimRepository bolimRepository;

    @Autowired
    FirmaRepository firmaRepository;


    public FirmaAPIresponse bolimPost(BolimDTO bolimDTO) {
        boolean b = firmaRepository.existsById(bolimDTO.getFirmaId());
        if (!b) return new FirmaAPIresponse("Bunday id li firma mavjud emas!!!",false);
        Optional<Bolim> byBolimNomiAndFirma_id = bolimRepository.findByBolimNomiAndFirma_Id(bolimDTO.getBolimNomi(), bolimDTO.getFirmaId());
        if (byBolimNomiAndFirma_id.isPresent()) return new FirmaAPIresponse("Bunday nomli bo'lim mavjud!!!",false);
        Bolim bolim = new Bolim();
        bolim.setBolimNomi(bolimDTO.getBolimNomi());
        bolim.setFirma(firmaRepository.findById(bolimDTO.getFirmaId()).get());
        bolimRepository.save(bolim);
        return new FirmaAPIresponse("Ma'lumot bazaga saqlandi!!!",true);
    }

    public FirmaAPIresponse bolimGet() {
        return new FirmaAPIresponse(firmaRepository.findAll().toString(),true);
    }

    public FirmaAPIresponse bolimGetId(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if (!byId.isPresent()) return new FirmaAPIresponse(id+"-idli malumot topilmadi!!!",false);
        return new FirmaAPIresponse(firmaRepository.findById(id).toString(),true);
    }

    public FirmaAPIresponse bolimdelet(Integer id) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if(!byId.isPresent()) return new FirmaAPIresponse(id+"-idli malumot topilmadi!!!",false);
        Bolim bolim = byId.get();
        bolimRepository.delete(bolim);
        Firma firma = byId.get().getFirma();
        firmaRepository.delete(firma);
        return new FirmaAPIresponse("Malumot o'chirildi!!!",true);
    }
}
