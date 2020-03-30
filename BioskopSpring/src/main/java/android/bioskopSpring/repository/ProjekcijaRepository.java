package android.bioskopSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import android.bioskopSpring.dto.AdresaBioskopaDto;
import model.Projekcija;

public interface ProjekcijaRepository extends JpaRepository<Projekcija, Integer>{
	
	@Query(value = "SELECT * FROM projekcija p GROUP BY p.adresaBioskopa", nativeQuery = true)
	List<Projekcija> findAllAddress();
	
	@Query(value = "SELECT * FROM ((projekcija p INNER JOIN sala s ON p.sala_id=s.id) INNER JOIN film f ON p.film_id=f.id) WHERE f.naziv=:naziv", nativeQuery = true)
	List<Projekcija> findAllProjekcijeForFilm(@Param("naziv") String film);
	
	@Query(value = "SELECT * FROM (projekcija p INNER JOIN sala s ON p.sala_id=s.id) WHERE p.id=:id", nativeQuery = true)
	Projekcija findProjekcijaById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM projekcija p WHERE p.id=:id", nativeQuery = true)
	Projekcija findProById(@Param("id") int id);
}
