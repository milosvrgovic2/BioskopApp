package android.bioskopSpring.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequestDto {
	
	@NotBlank
	private String korisnickoIme;
	@NotBlank
	private String lozinka;
	
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
