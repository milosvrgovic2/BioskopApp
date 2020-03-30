package com.example.bioskop;

public class ItemTopFilmovi {

    byte[] slika;
    String naziv;

    public ItemTopFilmovi(byte[] slika, String naziv){
        this.slika = slika;
        this.naziv = naziv;
    }

    public byte[] getSlika() {
        return slika;
    }

    public void setSlika(byte[] slika) {
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
