package br.com.gasit.enderecador.service;

import java.util.List;

import br.com.gasit.enderecador.entity.Address;

public interface AddressService {
	
	public List<Address> search(String value) throws Exception;

}
