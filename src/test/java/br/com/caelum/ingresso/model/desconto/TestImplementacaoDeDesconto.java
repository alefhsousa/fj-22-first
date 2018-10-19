package br.com.caelum.ingresso.model.desconto;

import br.com.caelum.ingresso.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class TestImplementacaoDeDesconto {

    @Test
    public void naoDeveAplicarDesconto() {
        Filme filme = new Filme("VENOM", Duration.ofMinutes(120), "Ação", new BigDecimal("15.0"));
        Sala imax = new Sala("Imax", new BigDecimal("20.0"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00"), filme, imax);
        Ingresso ingresso = new Ingresso(sessao, TipoIngresso.INTEIRO, new Lugar("A", 1));


        Assert.assertEquals(sessao.getPreco(), ingresso.getPreco());
    }
}

