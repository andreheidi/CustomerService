package br.com.gasit.enderecador.service;

import java.util.List;

import br.com.gasit.enderecador.entity.Address;
import br.com.gasit.enderecador.entity.Ibge;

public interface EnderecadorService {
	
	List<Address> searchCep(String value);
	
	List<Ibge> searchIbge(String value);

}
