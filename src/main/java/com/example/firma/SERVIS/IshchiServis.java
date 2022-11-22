package com.example.firma.SERVIS;

import com.example.firma.DTO.FirmaAPIresponse;
import com.example.firma.DTO.IshchiDTO;
import com.example.firma.Entity.Bolim;
import com.example.firma.Entity.Firma;
import com.example.firma.Entity.Ishchi;
import com.example.firma.Entity.Manzil;
import com.example.firma.Repository.BolimRepository;
import com.example.firma.Repository.IshchiRepository;
import com.example.firma.Repository.ManzilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IshchiServis {
    @Autowired
    IshchiRepository ishchiRepository;

    @Autowired
    ManzilRepository manzilRepository;

    @Autowired
    BolimRepository bolimRepository;

    public FirmaAPIresponse ishchiPost(IshchiDTO ishchiDTO) {
        boolean b = ishchiRepository.existsByTelNomer(ishchiDTO.getTelNomer());
        if(b) return new FirmaAPIresponse("Bunday telNomerli ishchi mavjud ekan!!!",false);
        boolean b1 = bolimRepository.existsById(ishchiDTO.getBolimId());
        if (!b1) return new FirmaAPIresponse("Bunday id li bolim mavjud emas!!!",false);
        Ishchi ishchi = new Ishchi();
        ishchi.setFish(ishchiDTO.getFish());
        if(ishchiDTO.getTelNomer().length()==13 || ishchiDTO.getTelNomer().length()==9 ){
            ishchi.setTelNomer(ishchiDTO.getTelNomer());
        }
        else {
            return new FirmaAPIresponse("Telnomer xato ketdi!!!",false);
        }
        ishchi.setBolim(bolimRepository.findById(ishchiDTO.getBolimId()).get());
        Manzil manzil = new Manzil();
        manzil.setVil(ishchiDTO.getVil());
        manzil.setTum(ishchiDTO.getTum());
        manzil.setKucha(ishchiDTO.getKucha());
        manzil.setUyRaqam(ishchiDTO.getUyRaqam());
        manzilRepository.save(manzil);
        ishchi.setManzil(manzil);
        ishchiRepository.save(ishchi);
        return new FirmaAPIresponse("Ma'lumot bazaga saqlandi!!!",true);
    }

    public FirmaAPIresponse ishchiGet() {
        return new FirmaAPIresponse(ishchiRepository.findAll().toString(),true);
    }

    public FirmaAPIresponse ishchigetId(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if (!byId.isPresent()) return new FirmaAPIresponse(id+"-idli malumot topilmadi!!!",false);
            return new FirmaAPIresponse(ishchiRepository.findById(id).toString(),true);
    }

    public FirmaAPIresponse ishchidelet(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if(!byId.isPresent()) return new FirmaAPIresponse(id+"-idli malumot topilmadi!!!",false);
        Ishchi ishchi = byId.get();
        ishchiRepository.delete(ishchi);
        Manzil manzil = byId.get().getManzil();
        manzilRepository.delete(manzil);
        Bolim bolim = byId.get().getBolim();
        bolimRepository.delete(bolim);
        return new FirmaAPIresponse("Malumot o'chirildi!!!",true);
    }
}
