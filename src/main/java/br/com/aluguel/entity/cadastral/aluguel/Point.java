package br.com.aluguel.entity.cadastral.aluguel;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Point {
	
    private BigDecimal latitude;
    private BigDecimal longitude;
    
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
   
}
