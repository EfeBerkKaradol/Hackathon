package com.innova.masraftakip.controller;

import com.innova.masraftakip.error_handling.ResourceNotFoundException;
import com.innova.masraftakip.model.Kisi;
import com.innova.masraftakip.model.Masraf;
import com.innova.masraftakip.service.KisiService;
import com.innova.masraftakip.repository.KisiRepository;
import com.innova.masraftakip.repository.MasrafRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kisi")
public class KisiController {

    @Autowired
    private KisiService kisiService;

    @Autowired
    private KisiRepository kisiRepository;

    @Autowired
    private MasrafRepository masrafRepository;

    @GetMapping
    public List<Kisi> getAllKisiler() {
        return kisiService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kisi> getKisiById(@PathVariable Long id) {
        Optional<Kisi> kisi = kisiService.findById(id);
        return kisi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Kisi createKisi(@RequestBody Kisi kisi) {
        return kisiService.save(kisi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kisi> updateKisi(@PathVariable Long id, @RequestBody Kisi kisiDetails) {
        Kisi updatedKisi = kisiService.updateKisi(id, kisiDetails);
        if (updatedKisi != null) {
            return ResponseEntity.ok(updatedKisi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKisi(@PathVariable Long id) {
        kisiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/masraf")
    public ResponseEntity<Kisi> addMasrafToKisi(@PathVariable(value = "id") Long kisiId, @RequestBody Masraf masraf) {
        Kisi kisi = kisiRepository.findById(kisiId)
                .orElseThrow(() -> new ResourceNotFoundException("Kisi not found for this id :: " + kisiId));

        kisi.addMasraf(masraf);
        masrafRepository.save(masraf);
        kisiRepository.save(kisi);
        return ResponseEntity.ok(kisi);
    }
}
