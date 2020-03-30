package android.bioskopSpring.dto;

import javax.validation.constraints.NotBlank;

public class SaveRezervacijaRequestDto {
	
	private int brojKarata;
	private int idProjekcija;
	private int idKorisnik;
	
	public int getBrojKarata() {
		return brojKarata;
	}
	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}
	public int getIdProjekcija() {
		return idProjekcija;
	}
	public void setIdProjekcija(int idProjekcija) {
		this.idProjekcija = idProjekcija;
	}
	public int getIdKorisnik() {
		return idKorisnik;
	}
	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}
	
	
}
