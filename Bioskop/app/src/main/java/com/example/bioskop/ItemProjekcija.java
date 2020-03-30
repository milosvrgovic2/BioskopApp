package com.example.bioskop;

public class ItemProjekcija {

    private String vreme;
    private String sala;
    private int id;

    public ItemProjekcija(String vreme, String sala, int id){
        this.vreme = vreme;
        this.sala = sala;
        this.id = id;
    }

    public ItemProjekcija(){

    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
