package android.bioskopSpring.dto;

import model.Projekcija;

public class GetProjekcijeResponseDto {
	
	private int id;
	private String vreme;
	private String sala;
	
	public GetProjekcijeResponseDto(Projekcija p) {
		vreme = p.getVreme();
		sala = p.getSala().getNaziv();
		id = p.getId();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
