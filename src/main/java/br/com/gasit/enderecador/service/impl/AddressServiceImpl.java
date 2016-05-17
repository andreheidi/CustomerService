package br.com.gasit.enderecador.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gasit.enderecador.entity.Address;
import br.com.gasit.enderecador.repository.AddressRepository;
import br.com.gasit.enderecador.service.AddressService;

@Service
class AddressServiceImpl implements AddressService {
	
	private Logger log = Logger.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> search(String value) throws Exception {
		log.info(String.format("Consultando endereco %s", value));
		List<Address> result = null;
		try {
			log.debug("Criando a pesquisa");
			this.addressRepository.createQuery(value);
			
			log.debug("Pesquisando endereco");
			result = this.addressRepository.search();
			log.debug("Pesquisa realizada com sucesso");
		} catch (Exception e) {
			log.error(String.format("Erro ao tentar pesquisar o endereco %s. Causa Exception: [ %s ]", value, e.getMessage()));
			throw new Exception(e.getMessage(), e);
		}
		return result;
	}
	
}
