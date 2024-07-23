package com.innova.masraftakip.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Masraf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aciklama;

    private Double miktar;

    private LocalDateTime tarih;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kisi_id")
    private Kisi kisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Double getMiktar() {
        return miktar;
    }

    public void setMiktar(Double miktar) {
        this.miktar = miktar;
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }
}
