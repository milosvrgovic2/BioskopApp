package android.bioskopSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	@Query(value = "SELECT * FROM korisnik k WHERE korisnickoIme=:korisnickoIme AND lozinka=:lozinka", nativeQuery = true)
	Optional<Korisnik> findByKorisnickoIme(@Param("korisnickoIme") String korisnickoIme, @Param("lozinka") String lozinka);
	
	@Query(value = "SELECT * FROM korisnik k WHERE k.id=:id", nativeQuery = true)
	Korisnik findKorisnikById(@Param("id") int id);
	
}
