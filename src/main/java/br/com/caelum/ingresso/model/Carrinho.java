package br.com.caelum.ingresso.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Carrinho {

    private List<Ingresso> ingressos = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;

    public void adiciona(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public boolean isSelecionado(Lugar lugarSelecionado) {
        return this.ingressos.stream().map(Ingresso::getLugar).anyMatch(lugar -> lugar.equals(lugarSelecionado));
    }

    public BigDecimal getTotal() {
        /* java 7
        for (Ingresso ing: this.ingressos) {
            BigDecimal preco = ing.getPreco();
            this.total.add(preco);
        } */
        /* java 8 triste
        this.ingressos.stream().map(Ingresso::getPreco).forEach(preco -> {
            this.total.add(preco);
        });
         return this.total;
         */


        //java 8 feliz
        return this.ingressos.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
