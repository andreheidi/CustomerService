package br.com.gasit.enderecador.repository.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class AbstractEnderecadorReporitory<T> {
	
	private final Logger log = Logger.getLogger(AbstractEnderecadorReporitory.class);
	
	protected WebClient getWebClient() {
		log.info("Iniciando configuracao do webclient");
		WebClient web = new WebClient();
		
		log.debug("Recuperando Options do webclient");
		WebClientOptions options = web.getOptions();
		/*ProxyConfig proxy = new ProxyConfig("proxy.url", port);
		DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
		provider.addCredentials("username", "password");
		CredentialsProvider credentialsProvider = provider;				
		web.setCredentialsProvider(credentialsProvider);
		options.setProxyConfig(proxy);*/		
		log.debug("Setando para false o parametro throwExceptionOnScriptError");
		options.setThrowExceptionOnScriptError(false);
		
		log.debug("Setando para false o parametro javaScriptEnable");
		options.setJavaScriptEnabled(false);
		
		log.debug("Setando para false  parametro cssEnable");
		options.setCssEnabled(false);						
		
		log.info("Configuracoes do webclient iniciada com sucesso.");
		return web;
	}
	
	protected HtmlPage getPage(String url) throws IOException  {
		log.info(String.format("Iniciando carregamento da pagina da URL %s", url));
		checkNotNull(url, "O parametro url nao pode ser nulo.");
		HtmlPage page = null;
		try {			
			log.debug("Carregando pagina");
			page = getWebClient().getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			log.error(String.format("Servidor retornou uma falha. Causa FailingHttpStatusCodeException: Message:[ %s ]. StatusMessage:  [ %s ]", e.getMessage(), e.getStatusMessage()), e);
			throw new FailingHttpStatusCodeException(e.getResponse());
		} catch (MalformedURLException e) {
			log.error(String.format("URL mal formatada. Causa MalformedURLException: [ %s ]", e.getMessage()), e);
			throw new MalformedURLException("URL mal formatada");
		} catch (IOException e) {
			log.error(String.format("Ocorreu um erro de I/O. Causa IOException: [ %s ]", e.getMessage()), e);
			throw new IOException("Erro de Entrada/Saida", e);
		}
		log.info("Pagina carregada com sucesso");
		return page;
	}
	
	protected abstract T build(HtmlTableRow row);

}
