package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int duzina;

	private String naziv;

	private double ocena;

	private String opis;

	@Lob
	private byte[] slika;

	private String tehnologija;

	private String verzija;

	//bi-directional many-to-one association to Zanr
	@ManyToOne
	private Zanr zanr;

	//bi-directional many-to-one association to Projekcija
	@OneToMany(mappedBy="film")
	private List<Projekcija> projekcijas;

	public Film() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuzina() {
		return this.duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getOcena() {
		return this.ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte[] getSlika() {
		return this.slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public String getTehnologija() {
		return this.tehnologija;
	}

	public void setTehnologija(String tehnologija) {
		this.tehnologija = tehnologija;
	}

	public String getVerzija() {
		return this.verzija;
	}

	public void setVerzija(String verzija) {
		this.verzija = verzija;
	}

	public Zanr getZanr() {
		return this.zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public List<Projekcija> getProjekcijas() {
		return this.projekcijas;
	}

	public void setProjekcijas(List<Projekcija> projekcijas) {
		this.projekcijas = projekcijas;
	}

	public Projekcija addProjekcija(Projekcija projekcija) {
		getProjekcijas().add(projekcija);
		projekcija.setFilm(this);

		return projekcija;
	}

	public Projekcija removeProjekcija(Projekcija projekcija) {
		getProjekcijas().remove(projekcija);
		projekcija.setFilm(null);

		return projekcija;
	}

}