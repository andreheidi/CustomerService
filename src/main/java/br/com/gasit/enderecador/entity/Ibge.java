package br.com.gasit.enderecador.entity;

import java.io.Serializable;

/**
 * Classe auxiliar Ibge
 * 
 * @author andreheidi
 * @since 1.0.000
 *
 */
public class Ibge implements Serializable {

	private static final long serialVersionUID = 2553693011859353439L;

	private String stateCode;
	private String state;
	private String cityCode;
	private String city;
	private String area;
	
	public Ibge() {
	}
	
	public Ibge(String stateCode, String state, String cityCode, String city, String area) {
		this.stateCode = stateCode;
		this.state = state;
		this.cityCode = cityCode;
		this.city = city;
		this.area = area;
	}

	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityCode == null) ? 0 : cityCode.hashCode());
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ibge other = (Ibge) obj;
		if (cityCode == null) {
			if (other.cityCode != null)
				return false;
		} else if (!cityCode.equals(other.cityCode))
			return false;
		if (stateCode == null) {
			if (other.stateCode != null)
				return false;
		} else if (!stateCode.equals(other.stateCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Ibge [Codigo UF = %s, Estado = %s, Codigo Municipio = %s, Municipio = %s]", stateCode, state, cityCode, city);
	}

	
}
