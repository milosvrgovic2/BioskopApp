package android.bioskopSpring.dto;

import model.Film;

public class TehnologijeFilmovaDto {
	
	private String tehnologija;
	
	public TehnologijeFilmovaDto(Film f) {
		tehnologija = f.getTehnologija();
	}

	public String getTehnologija() {
		return tehnologija;
	}

	public void setTehnologija(String tehnologija) {
		this.tehnologija = tehnologija;
	}
	
	
}
