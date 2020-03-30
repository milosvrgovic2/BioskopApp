package android.bioskopSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer>{
	
	@Query(value = "SELECT * FROM rezervacija r INNER JOIN projekcija p ON r.projekcija_id=p.id WHERE r.korisnik_id=:id", nativeQuery = true)
	List<Rezervacija> findRezervacijeByKorisnik(@Param("id") int id);
	
	@Query(value = "DELETE FROM rezervacija r WHERE r.id=:id", nativeQuery = true)
	long deleteRezervacijaById(@Param("id") int id);
}
