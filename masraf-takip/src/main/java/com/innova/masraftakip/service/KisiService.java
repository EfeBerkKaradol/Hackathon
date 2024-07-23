package com.innova.masraftakip.service;

import com.innova.masraftakip.model.Kisi;
import com.innova.masraftakip.repository.KisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KisiService {

    @Autowired
    private KisiRepository kisiRepository;

    public List<Kisi> findAll() {
        return kisiRepository.findAll();
    }

    public Optional<Kisi> findById(Long id) {
        return kisiRepository.findById(id);
    }

    public Kisi save(Kisi kisi) {
        return kisiRepository.save(kisi);
    }

    public void deleteById(Long id) {
        kisiRepository.deleteById(id);
    }

    public Kisi updateKisi(Long id, Kisi kisiDetails) {
        Optional<Kisi> kisiOptional = kisiRepository.findById(id);
        if (kisiOptional.isPresent()) {
            Kisi kisi = kisiOptional.get();
            kisi.setIsim(kisiDetails.getIsim());
            kisi.setSoyisim(kisiDetails.getSoyisim());
            kisi.setEmail(kisiDetails.getEmail());
            kisi.setSifre(kisiDetails.getSifre());
            return kisiRepository.save(kisi);
        } else {
            return null;
        }
    }
}
