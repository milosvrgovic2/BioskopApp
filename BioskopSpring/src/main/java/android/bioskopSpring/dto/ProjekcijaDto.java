package android.bioskopSpring.dto;


import model.Projekcija;

public class ProjekcijaDto {
	
	private String datum;
	private String vreme;
	
	public ProjekcijaDto(Projekcija p) {
		datum = p.getDatum();
		vreme = p.getVreme();
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	
	
}
