package android.bioskopSpring.dto;

import model.Film;

public class VerzijeFilmovaDto {

	private String verzija;
	
	public VerzijeFilmovaDto(Film f) {
		verzija = f.getVerzija();
	}

	public String getVerzija() {
		return verzija;
	}

	public void setVerzija(String verzija) {
		this.verzija = verzija;
	}
	
	
	
}
