package android.bioskopSpring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import android.bioskopSpring.dto.DeleteRezervacijaRequestDto;
import android.bioskopSpring.dto.GetRezervacijeKorisnikaRequestDto;
import android.bioskopSpring.dto.GetRezervacijeKorisnikaResponseDto;
import android.bioskopSpring.dto.SaveRezervacijaRequestDto;
import android.bioskopSpring.dto.SaveRezervacijaResponseDto;
import android.bioskopSpring.repository.KorisnikRepository;
import android.bioskopSpring.repository.ProjekcijaRepository;
import android.bioskopSpring.repository.RezervacijaRepository;
import model.Korisnik;
import model.Projekcija;
import model.Rezervacija;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
	
	@Autowired
	RezervacijaRepository rr;
	
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	ProjekcijaRepository pr;
	
	@PostMapping("/saveRezervacija")
	ResponseEntity<?> saveRezervacija(@RequestBody SaveRezervacijaRequestDto request){
		try {
			Rezervacija r = new Rezervacija();
			r.setBrojKarata(request.getBrojKarata());
			Korisnik k = kr.findKorisnikById(request.getIdKorisnik());
			r.setKorisnik(k);
			Projekcija p = pr.findProById(request.getIdProjekcija());
			r.setProjekcija(p);
			rr.save(r);
			SaveRezervacijaResponseDto srrdto = new SaveRezervacijaResponseDto(r);
			return new ResponseEntity<SaveRezervacijaResponseDto>(srrdto, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Neuspesna rezervacija! "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getRezervacijeKorisnika")
	ResponseEntity<List<GetRezervacijeKorisnikaResponseDto>> getRezervacijeKorisnika(@RequestBody GetRezervacijeKorisnikaRequestDto request){
		List<GetRezervacijeKorisnikaResponseDto> rkdto = new ArrayList<GetRezervacijeKorisnikaResponseDto>();
		List<Rezervacija> rezervacije = rr.findRezervacijeByKorisnik(request.getId());
		for(Rezervacija r : rezervacije) {
			GetRezervacijeKorisnikaResponseDto grkr = new GetRezervacijeKorisnikaResponseDto(r);
			rkdto.add(grkr);
		}
		return new ResponseEntity<List<GetRezervacijeKorisnikaResponseDto>>(rkdto, HttpStatus.OK);
	}
	
	@PostMapping("/deleteRezervacija")
	ResponseEntity<String> deleteRezervacija(@RequestBody DeleteRezervacijaRequestDto request){
		rr.deleteById(request.getId());
		return new ResponseEntity<String>("Uspesno obrisana rezervacija", HttpStatus.OK);
	}
}
