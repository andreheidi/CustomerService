package br.com.gasit.enderecador.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.common.collect.Lists;

import br.com.gasit.enderecador.entity.Ibge;
import br.com.gasit.enderecador.repository.IbgeRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
class IbgeRepositoryImpl extends AbstractEnderecadorReporitory<Ibge> implements IbgeRepository {

	private static final Logger log = Logger.getLogger(IbgeRepositoryImpl.class);

	private static final String BASE_IBGE_URL= "http://www.ibge.gov.br/home/geociencias/areaterritorial/area.php?%s&submit.x=0&submit.y=0";
	
	private String query;

	@Override
	public void createQuery(String query) {
		log.info(String.format("Passando valor para consulta [ %s ]", query));
		checkNotNull(query, "O valor para consulta nao pode ser nulo.");
		this.query = query;
	}

	@Override
	public List<Ibge> search() {
		List<Ibge> ibges = Lists.newLinkedList();
		List<HtmlTableRow> rows = this.searchIBGE().getRows();
		for (int index = 1; index < rows.size(); index++) {
			ibges.add(build(rows.get(index)));
		}
		return ibges;
	}
	
	protected Ibge build(HtmlTableRow row) {
		Ibge ibge = new Ibge();
		ibge.setStateCode(row.getCell(0).asText());
		ibge.setState(row.getCell(1).asText());
		ibge.setCityCode(row.getCell(2).asText());
		ibge.setCity(row.getCell(3).asText());
		ibge.setArea(row.getCell(4).asText());
		return ibge;
	}
	
	private HtmlTable searchIBGE() {
		try {			
			String metodo = query.matches("^[0-9]*$") ? String.format("nome=&codigo=%s", query) : String.format("nome=%s&codigo=", query);
			HtmlPage page = this.getPage(String.format(BASE_IBGE_URL, metodo));
			return (HtmlTable) page.getByXPath("//*[@id='miolo_interno']/table[1]").get(0);
		} catch (Exception e) {
			throw new RuntimeException("RunTimeException", e);
		}
	}

}
