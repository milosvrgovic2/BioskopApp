package android.bioskopSpring.dto;

import model.Korisnik;

public class KorisnikDto {
	
	private String ime;
	private String prezime;
	private int id;
	private String email;
	private String korisnickoIme;
	private String lozinka;
	
	public KorisnikDto(Korisnik k) {
		ime = k.getIme();
		prezime = k.getPrezime();
		id = k.getId();
		email = k.getEmail();
		korisnickoIme = k.getKorisnickoIme();
		lozinka = k.getLozinka();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
}
