package android.bioskopSpring.dto;



import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Projekcija;


public class GetMoviesResponseDto {

	private String nazivFilma;
	private String nazivZanra;
	private int duzinaFilma;
	private byte[] slika;
	
	public GetMoviesResponseDto(Film f) {
		nazivFilma = f.getNaziv();
		nazivZanra = f.getZanr().getNaziv();
		duzinaFilma = f.getDuzina();
		slika = f.getSlika();
		
	}

	public String getNazivFilma() {
		return nazivFilma;
	}

	public void setNazivFilma(String nazivFilma) {
		this.nazivFilma = nazivFilma;
	}

	public String getNazivZanra() {
		return nazivZanra;
	}

	public void setNazivZanra(String nazivZanra) {
		this.nazivZanra = nazivZanra;
	}

	public int getDuzinaFilma() {
		return duzinaFilma;
	}

	public void setDuzinaFilma(int duzinaFilma) {
		this.duzinaFilma = duzinaFilma;
	}

	public byte[] getSlika() {
		return slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}
	
	
	
}
