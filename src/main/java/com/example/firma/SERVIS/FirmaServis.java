package com.example.firma.SERVIS;

import com.example.firma.DTO.FirmaAPIresponse;
import com.example.firma.DTO.FirmaDto;
import com.example.firma.Entity.Firma;
import com.example.firma.Entity.Manzil;
import com.example.firma.Repository.FirmaRepository;
import com.example.firma.Repository.ManzilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirmaServis {
    @Autowired
    FirmaRepository firmaRepository;
    @Autowired
    ManzilRepository manzilRepository;
    public FirmaAPIresponse firmaPost(FirmaDto firmaDto) {
        boolean b = firmaRepository.existsByFirmaNomi(firmaDto.getFirmaNomi());
        if (b) return new FirmaAPIresponse("Bunday firma nomi mavjud!!!", false);
        boolean b1 = firmaRepository.existsByManzil_UyRaqamAndManzil_Kucha(firmaDto.getUyRaqam(), firmaDto.getKucha());
        if(b1) return new FirmaAPIresponse("Bu nomerli uyda firma mavjud!!!", false);
        Firma firma = new Firma();
        firma.setFirmaNomi(firmaDto.getFirmaNomi());
        firma.setDirektorNomi(firmaDto.getDirektorNomi());
        Manzil manzil = new Manzil();
        manzil.setVil(firmaDto.getVil());
        manzil.setTum(firmaDto.getTum());
        manzil.setKucha(firmaDto.getKucha());
        manzil.setUyRaqam(firmaDto.getUyRaqam());
        manzilRepository.save(manzil);
        firma.setManzil(manzil);
        firmaRepository.save(firma);
        return new FirmaAPIresponse("Ma'lumot bazaga saqlandi!!!",true);
    }
    public FirmaAPIresponse getFirma() {
        return new FirmaAPIresponse(firmaRepository.findAll().toString(),true);
    }

    public FirmaAPIresponse firmagetId(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if (!byId.isPresent()) return new FirmaAPIresponse(id+"-idli malumot topilmadi!!!",false);
        return new FirmaAPIresponse(firmaRepository.findById(id).toString(),true);

    }

    public FirmaAPIresponse deletFirma(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if(!byId.isPresent()) return new FirmaAPIresponse(id+"-idli malumot topilmadi!!!",false);
        Firma firma = byId.get();
        firmaRepository.delete(firma);
        Manzil manzil = byId.get().getManzil();
        manzilRepository.delete(manzil);
        return new FirmaAPIresponse("Malumot o'chirildi!!!",true);
    }
}
