package com.innova.masraftakip.service;

import com.innova.masraftakip.model.Masraf;
import com.innova.masraftakip.repository.MasrafRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasrafService {

    @Autowired
    private MasrafRepository masrafRepository;

    public List<Masraf> findAll() {
        return masrafRepository.findAll();
    }

    public Optional<Masraf> findById(Long id) {
        return masrafRepository.findById(id);
    }

    public Masraf save(Masraf masraf) {
        try {
            if (masraf.getId() == null || !masrafRepository.existsById(masraf.getId())) {
                return masrafRepository.save(masraf);
            } else {
                return masrafRepository.saveAndFlush(masraf);
            }
        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error: " + ex.getMessage(), ex);
        }
    }

    public void deleteById(Long id) {
        masrafRepository.deleteById(id);
    }

    public List<Masraf> findByKisiId(Long kisiId) {
        return masrafRepository.findByKisiId(kisiId);
    }

    public Masraf updateMasraf(Long id, Masraf masrafDetails) {
        Optional<Masraf> optionalMasraf = masrafRepository.findById(id);
        if (optionalMasraf.isPresent()) {
            Masraf masraf = optionalMasraf.get();
            masraf.setAciklama(masrafDetails.getAciklama());
            masraf.setMiktar(masrafDetails.getMiktar());
            masraf.setTarih(masrafDetails.getTarih());
            return masrafRepository.save(masraf);
        }
        return null;
    }
}
