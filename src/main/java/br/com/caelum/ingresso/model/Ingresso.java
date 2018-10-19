package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal preco;
    @ManyToOne
    private Sessao sessao;
    @ManyToOne
    private Lugar lugar;
    @Enumerated(EnumType.STRING)
    private TipoIngresso tipoIngresso;

    /**
     * @deprecated hibernate only
     */
    public Ingresso() {}


    public Ingresso(Sessao sessao, TipoIngresso tipoIngresso, Lugar lugar) {
        this.sessao = sessao;
        this.tipoIngresso = tipoIngresso;
        this.preco = tipoIngresso.calculaDesconto(sessao.getPreco());
        this.lugar = lugar;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public void setTipoIngresso(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public void setTipoDeIngresso(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }
}
