package android.bioskopSpring.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import android.bioskopSpring.dto.KorisnikDto;
import android.bioskopSpring.dto.LoginRequestDto;
import android.bioskopSpring.dto.RegisterRequestDto;
import android.bioskopSpring.dto.UpdatePasswordRequestDto;
import android.bioskopSpring.repository.KorisnikRepository;
import model.Korisnik;

@RestController
@RequestMapping(value = "/auth")
public class KorisnikController {
	
	@Autowired
	KorisnikRepository kr;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest){
		try {
			Korisnik k = kr.findByKorisnickoIme(loginRequest.getKorisnickoIme(), loginRequest.getLozinka()).get();
			KorisnikDto kdto = new KorisnikDto(k);
			return new ResponseEntity<KorisnikDto>(kdto, HttpStatus.OK);	
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Not logged! " + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequest){
		try {
			Korisnik k = new Korisnik();
			k.setIme(registerRequest.getIme());
			k.setPrezime(registerRequest.getPrezime());
			k.setEmail(registerRequest.getEmail());
			k.setKorisnickoIme(registerRequest.getKorisnickoIme());
			k.setLozinka(registerRequest.getLozinka());
			kr.save(k);
			KorisnikDto kdto = new KorisnikDto(k);
			return new ResponseEntity<KorisnikDto>(kdto, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Not registred! " + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequestDto request){
		try {
			Korisnik k = kr.findKorisnikById(request.getId());
			k.setLozinka(request.getLozinka());
			kr.save(k);
			KorisnikDto kdto = new KorisnikDto(k);
			return new ResponseEntity<KorisnikDto>(kdto, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Update error "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
