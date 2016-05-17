package br.com.gasit.enderecador.repository;

import java.util.List;

public interface EnderecadorRepository<T> {
	
	public void createQuery(String query);
	
	public List<T> search() throws Exception;
}
