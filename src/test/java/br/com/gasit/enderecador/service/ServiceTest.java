package br.com.gasit.enderecador.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.gasit.enderecador.Enderecador;
import br.com.gasit.enderecador.entity.Address;
import br.com.gasit.enderecador.entity.Ibge;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Enderecador.class)
public class ServiceTest {
	
	@Inject //@Autowired
	private AddressService addressService; 
	
	@Inject //@Autowired
	private IbgeService ibgeService;

	private static final Logger log = LoggerFactory.getLogger(ServiceTest.class);

	@Before
	public void setup() {
		assertNotNull("Servico de endereco nao foi iniciado", this.addressService);
		assertNotNull("Servico do ibge nao foi iniciado", this.addressService);
	}
	
	@Test
	public void testCep() throws Exception {
		log.info("Executando testCep");
		List<Address> addresses = this.addressService.search("81070-190");
		log.info(String.format("Foram encontrados %s enderecos", addresses.size()));
		addresses.forEach(a -> System.out.println(a.toString()));
	}

	@Test
	public void testLogradouro() throws Exception {
		log.info("Executando testLogradouro");
		List<Address> addresses = this.addressService.search("Rua João Bettega");
		log.info(String.format("Foram encontrados %s enderecos", addresses.size()));
		addresses.forEach(a -> System.out.println(a.toString()));
	}
	
	@Test
	public void testIbge() throws Exception {
		List<Ibge> ibges = this.ibgeService.search("Toledo");
		log.info(String.format("Foram encontrados %s cidades", ibges.size()));
		ibges.forEach(a -> System.out.println(a.toString()));
	}

}
