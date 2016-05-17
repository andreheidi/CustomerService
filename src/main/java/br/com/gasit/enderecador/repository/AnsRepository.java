package br.com.gasit.enderecador.repository;

import br.com.gasit.enderecador.entity.Ans;

import java.util.List;

/**
 * Created by andre on 4/10/16.
 */
public interface AnsRepository {

    void createQuery(String query, String type);

    Ans findByAnsRegister(String ansRegister);

    Ans findByCnpj(String cnpj);

    List<Ans> findByName(String name);

}
