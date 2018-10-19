package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoParaEstudante implements Desconto {

    @Override
    public BigDecimal calculaDesconto(BigDecimal precoFinal) {
        return precoFinal.subtract(aplicaCinquentaPorCentoDeDesconto(precoFinal));
    }

    @Override
    public String getDescricao() {
        return "MEIA";
    }

    private BigDecimal aplicaCinquentaPorCentoDeDesconto(BigDecimal precoFinal) {
        BigDecimal metadeDoValor = new BigDecimal("0.5");
        return precoFinal.multiply(metadeDoValor);
    }
}
