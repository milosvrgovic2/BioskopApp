package android.bioskopSpring.dto;



import model.Projekcija;

public class AdresaBioskopaDto {
	
	private String lokacija;
	
	public AdresaBioskopaDto(Projekcija p) {
		lokacija = p.getAdresaBioskopa();
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	
	
}
