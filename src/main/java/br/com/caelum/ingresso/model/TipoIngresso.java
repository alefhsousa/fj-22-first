package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.desconto.Desconto;
import br.com.caelum.ingresso.model.desconto.DescontoParaEstudante;
import br.com.caelum.ingresso.model.desconto.DescontoParaIdosos;
import br.com.caelum.ingresso.model.desconto.SemDesconto;

import java.math.BigDecimal;

public enum  TipoIngresso {


    INTEIRO(new SemDesconto()),
    MEIA(new DescontoParaEstudante()),
    IDOSO(new DescontoParaIdosos());

    private Desconto desconto;

    TipoIngresso(Desconto desconto) {
        this.desconto = desconto;
    }

    public BigDecimal calculaDesconto(BigDecimal preco) {
        return desconto.calculaDesconto(preco);
    }

    public String getDescricao() {
        return desconto.getDescricao();
    }
}
