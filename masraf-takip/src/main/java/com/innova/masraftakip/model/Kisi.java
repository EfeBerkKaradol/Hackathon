package com.innova.masraftakip.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Kisi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "İsim boş olamaz")
    private String isim;

    @NotBlank(message = "Soyisim boş olamaz")
    private String soyisim;

    @Email(message = "Geçerli bir email adresi girin")
    @NotBlank(message = "Email boş olamaz")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    private String sifre;

    @OneToMany(mappedBy = "kisi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Masraf> masraflar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        if(isim.equals("")){
            System.out.println("Isim bos girilemez!");
        } else {
            this.isim = isim;
        }
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        if(isim.equals("")){
            System.out.println("Soyisim bos girilemez!");
        } else {
            this.soyisim = soyisim;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public List<Masraf> getMasraflar() {
        return masraflar;
    }

    public void setMasraflar(List<Masraf> masraflar) {
        this.masraflar = masraflar;
    }

    public void addMasraf(Masraf masraf) {
        masraflar.add(masraf);
        masraf.setKisi(this);
    }

    public void removeMasraf(Masraf masraf) {
        masraflar.remove(masraf);
        masraf.setKisi(null);
    }
}
