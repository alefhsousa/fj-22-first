package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

public class GerenciadorDeSessoesTest {
    private Filme filme;
    private Sala sala;
    private Sessao sessaoDoVenom;

    @Before
    public void montaOSetupParaOsTestes() {
        this.filme = new Filme("Venom", Duration.ofMinutes(120), "ACAO", BigDecimal.TEN);
        this.sala = new Sala("IMAX", BigDecimal.TEN);
        this.sessaoDoVenom = new Sessao(LocalTime.parse("21:20"), filme, sala);
    }
    @Test
    public void naoDeveCriarASessaoPoisJaExisteUmaSessaCadastradaNesseHorario() {
        GerenciadorDeSessoes gerenciadorDeSessoes = new GerenciadorDeSessoes(Arrays.asList(this.sessaoDoVenom));
        boolean retorno = gerenciadorDeSessoes.validaSessao(this.sessaoDoVenom);

        Assert.assertFalse(retorno);
    }

    @Test
    public void deveCriarSessaoPoisNaoExisteNenhumaCadastrada() {
        GerenciadorDeSessoes gerenciadorDeSessoes = new GerenciadorDeSessoes(Collections.EMPTY_LIST);
        boolean retorno = gerenciadorDeSessoes.validaSessao(this.sessaoDoVenom);

        Assert.assertTrue(retorno);
    }
}
