package android.bioskopSpring.dto;

import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Zanr;

public class ZanrDto {
	
	private int id;
	private String naziv;
	private List<FilmDto> filmovi;
	
	public ZanrDto(Zanr z) {
		this.filmovi = new ArrayList<FilmDto>();
		id = z.getId();
		naziv = z.getNaziv();
		for(Film f : z.getFilms()) {
			FilmDto fdto = new FilmDto(f);
			this.filmovi.add(fdto);
		}
	}

	public ZanrDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<FilmDto> getFilmovi() {
		return filmovi;
	}

	public void setFilmovi(List<FilmDto> filmovi) {
		this.filmovi = filmovi;
	}
	
}
