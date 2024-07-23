package com.innova.masraftakip.repository;

import com.innova.masraftakip.model.Masraf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MasrafRepository extends JpaRepository<Masraf, Long> {
    // Belirli bir kişiye ait tüm masrafları bulmak için
    List<Masraf> findByKisiId(Long kisiId);

    List<Masraf> findAllByTarihBetween(LocalDateTime start, LocalDateTime end);

}
