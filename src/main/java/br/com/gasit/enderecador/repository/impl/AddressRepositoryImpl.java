package br.com.gasit.enderecador.repository.impl;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.common.collect.Lists;

import br.com.gasit.enderecador.entity.Address;
import br.com.gasit.enderecador.repository.AddressRepository;

@Repository
class AddressRepositoryImpl extends AbstractEnderecadorReporitory<Address> implements AddressRepository {
	
	private static final Logger log = Logger.getLogger(AddressRepositoryImpl.class);
	
	private static final String BASE_CEP_URL = "http://www.buscacep.correios.com.br/sistemas/buscacep/";
	
	private String query;

	@Override
	public void createQuery(String query) {
		log.info(String.format("Passando valor para consulta [ %s ]", query));
		checkNotNull(query, "O valor para consulta nao pode ser nulo.");
		this.query = query;
	}
	
	@Override
	public List<Address> search() throws Exception {
		log.info("Recuperando dados para consulta");
		List<Address> ceps = Lists.newLinkedList();
		
		log.debug("Executando pesquisa do endereco");
		List<HtmlTableRow> rows = this.searchCep().getRows();
		log.debug("Pesquisa realizada com sucesso");
		 
		log.debug("Convertendo table html com os dados em Objeto");
		for (int index = 1; index < rows.size(); index++) {
			ceps.add(build(rows.get(index)));
		}
		return ceps;
	}
	
	protected Address build(HtmlTableRow row) {
		Address address = new Address();
		address.setStreet(row.getCell(0).asText().trim());
		address.setNeighborhood(row.getCell(1).asText().trim());
		address.setCity(row.getCell(2).asText().substring(0, row.getCell(2).asText().indexOf("/")).trim());
		address.setState(row.getCell(2).asText().substring(row.getCell(2).asText().indexOf("/") + 1).trim());
		address.setZipCode(row.getCell(3).asText().trim());
		return address;
	}
	
	private HtmlTable searchCep() throws Exception {
		log.info("Iniciando busca do endereco");
		try {
			log.debug("Recuperando pagina");
			HtmlPage page = this.getPage(BASE_CEP_URL);
			log.debug("Pagina recuperada com sucesso");
			
			log.debug("Recuperando botao da pagina");
			HtmlSubmitInput button = (HtmlSubmitInput) page.getByXPath("//input[@value='Buscar']").get(0);
			 
			log.debug("Recuperando campo texto da pagina");
			HtmlTextInput input = page.getElementByName("relaxation");
			
			log.debug(String.format("Setando valor para realizar a pesquisa do endereco: %s", query));
			input.setText(query);
			
			log.debug("Executando click do botao e tentando fazer a consulta");
			HtmlPage result = button.click();
			
			log.info("Consulta realizada com sucesso. Retornando table com resultado da pesquisa.");
			return (HtmlTable) result.getByXPath("//table[@class='tmptabela']").get(0);
		} catch (FailingHttpStatusCodeException | IOException e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}

}
