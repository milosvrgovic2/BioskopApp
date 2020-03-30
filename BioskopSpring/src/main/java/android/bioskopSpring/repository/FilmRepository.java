package android.bioskopSpring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import android.bioskopSpring.dto.TehnologijeFilmovaDto;
import model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	@Query(value = "SELECT * FROM (( film f INNER JOIN zanr z ON f.zanr_id=z.id) INNER JOIN projekcija p ON f.id=p.film_id) WHERE p.adresaBioskopa=:adresaBioskopa AND p.datum=:datum AND f.tehnologija=:tehnologija AND f.verzija=:verzija GROUP BY f.naziv", nativeQuery = true)
	List<Film> findMoviesByParameters(@Param("adresaBioskopa") String adresaBioskopa, @Param("datum") String datum, @Param("tehnologija") String tehnologija, @Param("verzija") String verzija);
	
	@Query(value = "SELECT * FROM film f GROUP BY f.tehnologija", nativeQuery = true)
	List<Film> findAllTechnologies();
	
	@Query(value = "SELECT * FROM film f GROUP BY f.verzija", nativeQuery = true)
	List<Film> findAllVersions();
	
	@Query(value = "SELECT * FROM film f WHERE f.naziv=:film", nativeQuery = true)
	Film findMovieByTitle(@Param("film") String naziv);
	
	@Query(value = "SELECT * FROM film f WHERE f.ocena=10", nativeQuery = true)
	List<Film> findMovieByOcena();
	
	@Query(value = "SELECT * FROM film f WHERE f.id=:id", nativeQuery = true)
	Film findFilmById(@Param("id") int id);
}
