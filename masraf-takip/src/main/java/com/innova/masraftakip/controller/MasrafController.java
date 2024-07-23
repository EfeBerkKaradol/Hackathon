package com.innova.masraftakip.controller;

import com.innova.masraftakip.model.Masraf;
import com.innova.masraftakip.service.MasrafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/masraflar")
public class MasrafController {

    @Autowired
    private MasrafService masrafService;

    @GetMapping
    public List<Masraf> getAllMasraflar() {
        return masrafService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Masraf> getMasrafById(@PathVariable Long id) {
        Optional<Masraf> masraf = masrafService.findById(id);
        return masraf.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Masraf createMasraf(@RequestBody Masraf masraf) {
        return masrafService.save(masraf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Masraf> updateMasraf(@PathVariable Long id, @RequestBody Masraf masrafDetails) {
        Masraf updatedMasraf = masrafService.updateMasraf(id, masrafDetails);
        if (updatedMasraf != null) {
            return ResponseEntity.ok(updatedMasraf);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasraf(@PathVariable Long id) {
        masrafService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/kisi/{kisiId}")
    public List<Masraf> getMasraflarByKisiId(@PathVariable Long kisiId) {
        return masrafService.findByKisiId(kisiId);
    }

    @GetMapping("/kisi/{kisiId}/toplam")
    public ResponseEntity<Double> getToplamHarcamaByKisiId(@PathVariable Long kisiId) {
        List<Masraf> masraflar = masrafService.findByKisiId(kisiId);
        double toplam = masraflar.stream().mapToDouble(Masraf::getMiktar).sum();
        return ResponseEntity.ok(toplam);
    }

}
