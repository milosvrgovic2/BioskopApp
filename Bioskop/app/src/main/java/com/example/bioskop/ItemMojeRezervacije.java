package com.example.bioskop;

public class ItemMojeRezervacije {

    private int id;
    private String film;
    private String datum;
    private String vreme;
    private double cena;
    private int brojKarata;
    private String bioskop;

    public ItemMojeRezervacije(int id, String film, String datum, String vreme, double cena, int brojKarata, String bioskop) {
        this.id = id;
        this.film = film;
        this.datum = datum;
        this.vreme = vreme;
        this.cena = cena;
        this.brojKarata = brojKarata;
        this.bioskop = bioskop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getBrojKarata() {
        return brojKarata;
    }

    public void setBrojKarata(int brojKarata) {
        this.brojKarata = brojKarata;
    }

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }
}
