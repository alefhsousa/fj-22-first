package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.*;

@Entity
public class Sessao {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalTime horario;
    @ManyToOne
    private Sala sala;
    @ManyToOne
    private Filme filme;
    private BigDecimal preco;
    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private Set<Ingresso> ingressos = new HashSet<>();

    /**
     * @deprecated hibernate only
     */
    public Sessao() {
    }

    public Sessao(LocalTime horario, Filme filme, Sala sala) {
        this.horario = horario;
        this.filme = filme;
        this.sala = sala;
        this.preco = sala.getPreco().add(filme.getPreco());
    }

    public boolean isDisponivel(Lugar lugarSelecionado) {
        return this.ingressos.stream()
                .map(Ingresso::getLugar)
                .noneMatch(lugar -> lugar.equals(lugarSelecionado));
    }
    public Map<String, List<Lugar>> getMapaDeLugares() {
        return sala.getMapaDeLugares();
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalTime getHorarioTermino() {
        return this.horario.plusMinutes(this.filme.getDuracao().toMinutes());
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Ingresso> getIngressos() {
        return ingressos;
    }
}
