package android.bioskopSpring.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import android.bioskopSpring.dto.AdresaBioskopaDto;
import android.bioskopSpring.dto.GetProjekcijaRequestDto;
import android.bioskopSpring.dto.GetProjekcijaResponseDto;
import android.bioskopSpring.dto.GetProjekcijeRequestDto;
import android.bioskopSpring.dto.GetProjekcijeResponseDto;
import android.bioskopSpring.repository.ProjekcijaRepository;
import model.Projekcija;

@RestController
@RequestMapping("/projekcija")
public class ProjekcijaController {
	
	@Autowired
	ProjekcijaRepository pr;
	
	@GetMapping("/getAllAddress")
	ResponseEntity<List<AdresaBioskopaDto>> getAllAddress(){
		List<AdresaBioskopaDto> abdto = new ArrayList<AdresaBioskopaDto>();
		List<Projekcija> adrese = pr.findAllAddress();
		for(Projekcija p : adrese) {
			AdresaBioskopaDto l = new AdresaBioskopaDto(p);
			abdto.add(l);
		}
		return new ResponseEntity<List<AdresaBioskopaDto>>(abdto, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDates")
	ResponseEntity<List<String>> getAllDates(){
		List<String> datumi = new ArrayList<String>();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String dateString = date.format(formatter);
		LocalDate date2 = date.plusDays(1);
		String date2String = date2.format(formatter);
		LocalDate date3 = date2.plusDays(1);
		String date3String = date3.format(formatter);
		LocalDate date4 = date3.plusDays(1);
		String date4String = date4.format(formatter);
		LocalDate date5 = date4.plusDays(1);
		String date5String = date5.format(formatter);
		LocalDate date6 = date5.plusDays(1);
		String date6String = date6.format(formatter);
		datumi.add(dateString);
		datumi.add(date2String);
		datumi.add(date3String);
		datumi.add(date4String);
		datumi.add(date5String);
		datumi.add(date6String);
		return new ResponseEntity<List<String>>(datumi, HttpStatus.OK);
	}
	
	@PostMapping("/getAllProjekcijeForFilm")
	ResponseEntity<List<GetProjekcijeResponseDto>> getAllProjekcijeForFilm(@RequestBody GetProjekcijeRequestDto request){
		List<GetProjekcijeResponseDto> pdto = new ArrayList<GetProjekcijeResponseDto>();
		List<Projekcija> projekcije = pr.findAllProjekcijeForFilm(request.getFilm());
		for(Projekcija p : projekcije) {
			GetProjekcijeResponseDto prdto = new GetProjekcijeResponseDto(p);
			pdto.add(prdto);
		}
		return new ResponseEntity<List<GetProjekcijeResponseDto>>(pdto, HttpStatus.OK);
	}
	
	@PostMapping("/getProjekcijaById")
	ResponseEntity<GetProjekcijaResponseDto> getProjekcijaById(@RequestBody GetProjekcijaRequestDto request){
		Projekcija projekcija = pr.findProjekcijaById(request.getId());
		GetProjekcijaResponseDto gprdto = new GetProjekcijaResponseDto(projekcija);
		return new ResponseEntity<GetProjekcijaResponseDto>(gprdto, HttpStatus.OK);
	}
}
