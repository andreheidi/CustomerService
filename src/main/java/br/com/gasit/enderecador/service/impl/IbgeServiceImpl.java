package br.com.gasit.enderecador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gasit.enderecador.entity.Ibge;
import br.com.gasit.enderecador.repository.IbgeRepository;
import br.com.gasit.enderecador.service.IbgeService;

@Service
class IbgeServiceImpl implements IbgeService {
	
	@Autowired
	private IbgeRepository ibgeRepository;

	@Override
	public List<Ibge> search(String value) throws Exception {
		List<Ibge> result = null;
		
		try {
			this.ibgeRepository.createQuery(value);
			result = this.ibgeRepository.search();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}
		return result;
	}

}
