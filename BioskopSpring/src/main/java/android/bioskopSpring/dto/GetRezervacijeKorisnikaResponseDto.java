package android.bioskopSpring.dto;

import model.Rezervacija;

public class GetRezervacijeKorisnikaResponseDto {
	
	private int id;
	private String film;
	private String datum;
	private String vreme;
	private double cena;
	private int brojKarata;
	private String bioskop;
	
	public GetRezervacijeKorisnikaResponseDto(Rezervacija r) {
		id = r.getId();
		film = r.getProjekcija().getFilm().getNaziv();
		datum = r.getProjekcija().getDatum();
		vreme = r.getProjekcija().getVreme();
		cena = r.getProjekcija().getCena();
		brojKarata = r.getBrojKarata();
		bioskop = r.getProjekcija().getAdresaBioskopa();
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDatum() {
		return datum;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
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
