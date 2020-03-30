package android.bioskopSpring.dto;

import model.Film;

public class FilmDto {
	
	private String naziv;
	
	public FilmDto(Film f) {
		naziv = f.getNaziv();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
