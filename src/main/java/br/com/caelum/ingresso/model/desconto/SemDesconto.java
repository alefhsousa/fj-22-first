package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

    @Override
    public BigDecimal calculaDesconto(BigDecimal precoFinal) {
        return precoFinal;
    }

    @Override
    public String getDescricao() {
        return "INTEIRA";
    }

}
