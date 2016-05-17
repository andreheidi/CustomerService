package br.com.gasit.enderecador.service;

import java.util.List;

import br.com.gasit.enderecador.entity.Ibge;

public interface IbgeService {
	public List<Ibge> search(String value) throws Exception;
}
