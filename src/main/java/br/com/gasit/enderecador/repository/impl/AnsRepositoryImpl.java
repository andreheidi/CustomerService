package br.com.gasit.enderecador.repository.impl;

import br.com.gasit.enderecador.entity.Ans;
import br.com.gasit.enderecador.repository.AnsRepository;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import static com.google.common.base.Preconditions.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andre on 4/10/16.
 */
public class AnsRepositoryImpl extends AbstractEnderecadorReporitory<Ans> implements AnsRepository, Serializable {

    private static final String BASE_ANS_URL = "http://www.ans.gov.br/planos-de-saude-e-operadoras/informacoes-e-avaliacoes-de-operadoras/consultar-dados";

    private String query;
    private String type;

    @Override
    public void createQuery(String query, String type) {
        checkNotNull(query, "O valor para consulta esta nulo");
        checkNotNull(type, "O tipo para consulta esta nulo");
        checkArgument(query.isEmpty(), "O valor para consulta esta vazia");
        checkArgument(type.isEmpty(), "O tipo para consulta esta vazia");

        this.query = query;
        this.type = type;
    }

    @Override
    public Ans findByAnsRegister(String ansRegister) {
        return null;
    }

    @Override
    public Ans findByCnpj(String cnpj) {
        return null;
    }

    @Override
    public List<Ans> findByName(String name) {
        return null;
    }

    @Override
    protected Ans build(HtmlTableRow row) {
        return null;
    }

    private HtmlTable searchCep() throws Exception {
        HtmlPage page = this.getPage(BASE_ANS_URL);
        HtmlTextInput input = null;

        if(type.equalsIgnoreCase("ANS")) {
            input = page.getElementByName("cans");
        } else if (type.equalsIgnoreCase("OPERADORA")) {
            input = page.getElementByName("razs");
        } else if(type.equalsIgnoreCase("CNPJ")) {
            input = page.getElementByName("cnpj");
        }

        input.setText(query);

        ScriptResult result = page.executeJavaScript("pesquisar()");

        Page resultPage = result.getNewPage();

        System.out.println(resultPage.isHtmlPage());

        return null;
    }
}
