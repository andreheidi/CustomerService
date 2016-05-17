package br.com.gasit.enderecador.service;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.sun.deploy.uitoolkit.impl.fx.Utils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by andre on 4/10/16.
 */
public class AnsTest {

    private final Logger log = Logger.getLogger(AnsTest.class);

    private static final String BASE_ANS_URL = "http://www.ans.gov.br/externo/site_novo/informacoes_avaliacoes_oper/pesquisa_operadora.asp";

    //http://www.ans.gov.br/externo/site_novo/informacoes_avaliacoes_oper/consultaOperadoras.asp?cans=32630-5
    //http://www.ans.gov.br/externo/site_novo/informacoes_avaliacoes_oper/consultaOperadoras.asp?cans=&razs=amil&
    //http://www.ans.gov.br/externo/site_novo/informacoes_avaliacoes_oper/consultaOperadoras.asp?cans=&razs=&cnpj=29.309.127/0001-79&

    public void test() {
        HtmlPage page = null;
        String type = "OPERADORA";
        String query = "AMIL";
        HtmlTextInput input = null;
        HtmlDivision div = null;
        try {

            WebClient w = getWebClient();
            page = w.getPage("http://www.ans.gov.br/externo/site_novo/informacoes_avaliacoes_oper/consultaOperadoras.asp?cans=326305");


            System.out.println(page.asXml());
            //w.waitForBackgroundJavaScript(9000);
            /*ScriptResult rs = page.executeJavaScript("redirecionar()");
            w.waitForBackgroundJavaScript(9000);

            HtmlPage p2 = (HtmlPage) rs.getNewPage();
            System.out.println(p2.asXml());*/

            //ScriptResult rs = page.executeJavaScript("redirecionar()");

            //HtmlPage p1 = this.getPage("http://www.ans.gov.br/externo/site_novo/informacoes_avaliacoes_oper/detalhesOperadora.asp");

            HtmlForm form = page.getFormByName("f");
            HtmlPage f = form.getHtmlPageOrNull();
            ScriptResult rs = f.executeJavaScript("redirecionar()");

            HtmlPage prs = (HtmlPage) rs.getNewPage();
            System.out.println(prs.asXml());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected WebClient getWebClient() {
        log.info("Iniciando configuracao do webclient");
        WebClient web = new WebClient();

        //web.setAjaxController(new NicelyResynchronizingAjaxController());
        //web.waitForBackgroundJavaScript(90000);
        //web.waitForBackgroundJavaScriptStartingBefore(90000);
        log.debug("Recuperando Options do webclient");
        WebClientOptions options = web.getOptions();
        log.debug("Setando para false o parametro throwExceptionOnScriptError");
        options.setThrowExceptionOnScriptError(false);

        log.debug("Setando para false o parametro javaScriptEnable");
        options.setJavaScriptEnabled(false);

        log.debug("Setando para false  parametro cssEnable");
        options.setCssEnabled(false);

        log.info("Configuracoes do webclient iniciada com sucesso.");
        return web;
    }

    protected HtmlPage getPage(String url) throws IOException {
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
}
