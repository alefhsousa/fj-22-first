package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoParaIdosos implements Desconto {

    @Override
    public BigDecimal calculaDesconto(BigDecimal precoFinal) {
        return precoFinal.subtract(aplica60PorCentoDesconto(precoFinal));
    }

    @Override
    public String getDescricao() {
        return "PARA IDOSOS";
    }

    private BigDecimal aplica60PorCentoDesconto(BigDecimal precoFinal) {
        BigDecimal metadeDoValor = new BigDecimal("0.6");
        return precoFinal.multiply(metadeDoValor);
    }
}
