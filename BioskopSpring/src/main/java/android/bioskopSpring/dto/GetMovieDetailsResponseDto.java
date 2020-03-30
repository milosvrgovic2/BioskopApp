package android.bioskopSpring.dto;

import model.Film;

public class GetMovieDetailsResponseDto {
	
	String nazivFilma;
	int duzinaFilma;
	String zanrFilma;
	String verzijaFilma;
	String opisFilma;
	byte[] slika;
	
	public GetMovieDetailsResponseDto(Film f) {
		nazivFilma = f.getNaziv();
		duzinaFilma = f.getDuzina();
		zanrFilma = f.getZanr().getNaziv();
		verzijaFilma = f.getVerzija();
		opisFilma = f.getOpis();
		slika = f.getSlika();
	}

	public String getNazivFilma() {
		return nazivFilma;
	}

	public void setNazivFilma(String nazivFilma) {
		this.nazivFilma = nazivFilma;
	}

	public int getDuzinaFilma() {
		return duzinaFilma;
	}

	public void setDuzinaFilma(int duzinaFilma) {
		this.duzinaFilma = duzinaFilma;
	}

	public String getZanrFilma() {
		return zanrFilma;
	}

	public void setZanrFilma(String zanrFilma) {
		this.zanrFilma = zanrFilma;
	}

	public String getVerzijaFilma() {
		return verzijaFilma;
	}

	public void setVerzijaFilma(String verzijaFilma) {
		this.verzijaFilma = verzijaFilma;
	}

	public String getOpisFilma() {
		return opisFilma;
	}

	public void setOpisFilma(String opisFilma) {
		this.opisFilma = opisFilma;
	}

	public byte[] getSlika() {
		return slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}
	
}
