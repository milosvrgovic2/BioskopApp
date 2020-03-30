package android.bioskopSpring.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterRequestDto {
	
	@NotBlank
	private String ime;
	@NotBlank
	private String prezime;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String korisnickoIme;
	@NotBlank
	private String lozinka;
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
