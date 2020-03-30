package android.bioskopSpring.dto;

import model.Film;

public class GetMovieByOcenaResponseDto {
	
	byte[] slika;
	String naziv;
	
	public GetMovieByOcenaResponseDto(Film f) {
		slika = f.getSlika();
		naziv = f.getNaziv();
	}

	public byte[] getSlika() {
		return slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}
