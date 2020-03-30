package android.bioskopSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import android.bioskopSpring.dto.FilmDto;
import android.bioskopSpring.dto.GetMovieByIdRequestDto;
import android.bioskopSpring.dto.GetMovieByOcenaResponseDto;
import android.bioskopSpring.dto.GetMovieDetailsRequestDto;
import android.bioskopSpring.dto.GetMovieDetailsResponseDto;
import android.bioskopSpring.dto.GetMoviesRequestDto;
import android.bioskopSpring.dto.GetMoviesResponseDto;
import android.bioskopSpring.dto.TehnologijeFilmovaDto;
import android.bioskopSpring.dto.VerzijeFilmovaDto;
import android.bioskopSpring.dto.ZanrDto;
import android.bioskopSpring.repository.FilmRepository;
import android.bioskopSpring.repository.ZanrRepository;
import model.Film;
import model.Zanr;

@RestController
@RequestMapping(value = "/film")
public class FilmController {
	
	@Autowired
	FilmRepository fr;
	
	@Autowired
	ZanrRepository zr;
	
	/*@GetMapping("/getAllMovie")
	ResponseEntity<List<FilmDto>> getAllMovie(){
		List<FilmDto> fd = new ArrayList<FilmDto>();
		List<Film> filmovi = fr.findAll();
		for(Film f : filmovi) {
			FilmDto fdto = new FilmDto(f);
			fd.add(fdto);
		}
		return new ResponseEntity<List<FilmDto>>(fd, HttpStatus.OK);
	}*/
	
	@GetMapping("/getAllGenre")
	ResponseEntity<List<ZanrDto>> getAllGenre(){
		List<ZanrDto> zd = new ArrayList<ZanrDto>();
		List<Zanr> zanrovi = zr.findAll();
		for(Zanr z : zanrovi) {
			ZanrDto zdto = new ZanrDto(z);
			zd.add(zdto);
		}
		return new ResponseEntity<List<ZanrDto>>(zd, HttpStatus.OK);
	}
	
	@PostMapping("/getAllMovies")
	ResponseEntity<?> getAllMovies(@RequestBody GetMoviesRequestDto movieRequest){
		try {
			List<Film> filmovi = fr.findMoviesByParameters(movieRequest.getAdresaBioskopa(), movieRequest.getDatum(), movieRequest.getTehnologija(), movieRequest.getVerzija());
			List<GetMoviesResponseDto> moviesResponse = new ArrayList<GetMoviesResponseDto>();
			for(Film f : filmovi) {
				GetMoviesResponseDto mdto = new GetMoviesResponseDto(f);
				moviesResponse.add(mdto);
			}
			return new ResponseEntity<List<GetMoviesResponseDto>>(moviesResponse, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getAllTechnologies")
	ResponseEntity<List<TehnologijeFilmovaDto>> getAllTechnologies(){
		List<TehnologijeFilmovaDto> tfdto = new ArrayList<TehnologijeFilmovaDto>();
		List<Film> tehnologije = fr.findAllTechnologies();
		for(Film f : tehnologije) {
			TehnologijeFilmovaDto tf = new TehnologijeFilmovaDto(f);
			tfdto.add(tf);
		}
		return new ResponseEntity<List<TehnologijeFilmovaDto>>(tfdto, HttpStatus.OK);
	}
	
	@GetMapping("/getAllVersions")
	ResponseEntity<List<VerzijeFilmovaDto>> getAllVersions(){
		List<VerzijeFilmovaDto> vfdto = new ArrayList<VerzijeFilmovaDto>();
		List<Film> verzije = fr.findAllVersions();
		for(Film f : verzije) {
			VerzijeFilmovaDto vdto = new VerzijeFilmovaDto(f);
			vfdto.add(vdto);
		}
		return new ResponseEntity<List<VerzijeFilmovaDto>>(vfdto, HttpStatus.OK);
	}
	
	@PostMapping("/getMovieDetails")
	ResponseEntity<GetMovieDetailsResponseDto> getMovieDetails(@RequestBody GetMovieDetailsRequestDto request){
		Film film = fr.findMovieByTitle(request.getFilm());
		GetMovieDetailsResponseDto movie = new GetMovieDetailsResponseDto(film);
		return new ResponseEntity<GetMovieDetailsResponseDto>(movie, HttpStatus.OK);
	}
	
	@GetMapping("/getMovieByOcena")
	ResponseEntity<List<GetMovieByOcenaResponseDto>> getMovieByOcena(){
		List<GetMovieByOcenaResponseDto> modto = new ArrayList<GetMovieByOcenaResponseDto>();
		List<Film> filmovi = fr.findMovieByOcena();
		for(Film f : filmovi) {
			GetMovieByOcenaResponseDto gmodto = new GetMovieByOcenaResponseDto(f);
			modto.add(gmodto);
		}
		return new ResponseEntity<List<GetMovieByOcenaResponseDto>>(modto, HttpStatus.OK);
	}
	
	@PostMapping("/getMovieById")
	ResponseEntity<FilmDto> getMovieById(@RequestBody GetMovieByIdRequestDto request){
		Film film = fr.findFilmById(request.getId());
		FilmDto fdto = new FilmDto(film);
		return new ResponseEntity<FilmDto>(fdto, HttpStatus.OK);
	}
}
