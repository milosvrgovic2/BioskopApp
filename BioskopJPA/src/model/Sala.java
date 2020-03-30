package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sala database table.
 * 
 */
@Entity
@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String brojSedista;

	private String naziv;

	//bi-directional many-to-one association to Projekcija
	@OneToMany(mappedBy="sala")
	private List<Projekcija> projekcijas;

	public Sala() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrojSedista() {
		return this.brojSedista;
	}

	public void setBrojSedista(String brojSedista) {
		this.brojSedista = brojSedista;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Projekcija> getProjekcijas() {
		return this.projekcijas;
	}

	public void setProjekcijas(List<Projekcija> projekcijas) {
		this.projekcijas = projekcijas;
	}

	public Projekcija addProjekcija(Projekcija projekcija) {
		getProjekcijas().add(projekcija);
		projekcija.setSala(this);

		return projekcija;
	}

	public Projekcija removeProjekcija(Projekcija projekcija) {
		getProjekcijas().remove(projekcija);
		projekcija.setSala(null);

		return projekcija;
	}

}