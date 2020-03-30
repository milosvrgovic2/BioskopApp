package android.bioskopSpring.dto;

import model.Rezervacija;

public class SaveRezervacijaResponseDto {
	
	private int id;
	private int brojKarata;
	
	public SaveRezervacijaResponseDto(Rezervacija r) {
		id = r.getId();
		brojKarata = r.getBrojKarata();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrojKarata() {
		return brojKarata;
	}

	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}
	
	
}
