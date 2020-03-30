package android.bioskopSpring.dto;



public class GetMoviesRequestDto {
	
	private String adresaBioskopa;
	private String datum;
	private String tehnologija;
	private String verzija;
	
	public String getAdresaBioskopa() {
		return adresaBioskopa;
	}
	public void setAdresaBioskopa(String adresaBioskopa) {
		this.adresaBioskopa = adresaBioskopa;
	}
	
	public String getTehnologija() {
		return tehnologija;
	}
	public void setTehnologija(String tehnologija) {
		this.tehnologija = tehnologija;
	}
	public String getVerzija() {
		return verzija;
	}
	public void setVerzija(String verzija) {
		this.verzija = verzija;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	
	
}
