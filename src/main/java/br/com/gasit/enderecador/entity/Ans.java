package br.com.gasit.enderecador.entity;

import com.google.common.base.Objects;
import me.yanaga.opes.Cnpj;

import java.io.Serializable;

/**
 * Created by andre on 4/10/16.
 */
public class Ans implements Serializable {

    private String fantasyName;
    private String ansRegister;
    private Cnpj cnpj;
    private String companyName;
    private String status;
    private String segment;

    private String city;
    private String state;

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getAnsRegister() {
        return ansRegister;
    }

    public void setAnsRegister(String ansRegister) {
        this.ansRegister = ansRegister;
    }

    public String getCnpj() {
        return cnpj.toString();
    }

    public void setCnpj(String cnpj) {
        this.cnpj = Cnpj.of(cnpj);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ans ans = (Ans) o;
        return Objects.equal(ansRegister, ans.ansRegister);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ansRegister);
    }
}
