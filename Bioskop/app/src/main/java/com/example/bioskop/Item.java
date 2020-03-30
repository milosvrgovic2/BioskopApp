package com.example.bioskop;

public class Item {

    private String film;
    private String zanr;
    private int duzina;
    private byte[] slika;

    public Item(String film, String zanr, int duzina, byte[] slika){
        this.film = film;
        this.zanr = zanr;
        this.duzina = duzina;
        this.slika = slika;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }

    public byte[] getSlika() {
        return slika;
    }

    public void setSlika(byte[] slika) {
        this.slika = slika;
    }
}
