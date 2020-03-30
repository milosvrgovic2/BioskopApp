package android.bioskopSpring.dto;

import model.Projekcija;

public class GetProjekcijaResponseDto {
	
	private String vreme;
	private String sala;
	private double cena;
	private String datum;
	
	public GetProjekcijaResponseDto(Projekcija p) {
		vreme = p.getVreme();
		sala = p.getSala().getNaziv();
		cena = p.getCena();
		datum = p.getDatum();
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
	
}
