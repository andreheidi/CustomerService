package br.com.gasit.enderecador.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringEscapeUtils;

import me.yanaga.opes.Cep;

/**
 * Classe auxiliar Endereco
 * 
 * @author andreheidi
 * @since 1.0.000
 */
public class Address implements Serializable {
				
	private static final long serialVersionUID = -4556637190933285386L;
	
	private Cep zipCode;
	private String street;
	private String neighborhood;
	private String city;
	private String state;
	private Ibge ibge;
	
	public Address() {
	}
	
	public Address(String zipCode, String street, String neighborhood, String city, String state, Ibge ibge) {
		this.zipCode = Cep.of(zipCode);
		this.setZipCode(zipCode);
		this.street = street;
		this.neighborhood =neighborhood;
		this.city = city;
		this.state = state;
		this.ibge = ibge;
	}

	public String getZipCode() {
		return zipCode.toString();
	}

	public void setZipCode(String zipCode) {
		this.zipCode = Cep.of(zipCode);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Ibge getIbge() {
		return ibge;
	}
	
	public void setIbge(Ibge ibge) {
		this.ibge = ibge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Address other = (Address) obj;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Endereco [CEP = %s, Logradouro = %s, Bairro = %s, Cidade = %s, Estado = %s ]", zipCode, street, neighborhood, city, state);
	}
	
}
