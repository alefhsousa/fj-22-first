package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Sessao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class GerenciadorDeSessoes {

    private List<Sessao> sessoesDaSala;

    public GerenciadorDeSessoes(List<Sessao> sessoesDaSala) {
        this.sessoesDaSala = sessoesDaSala;
    }


    public boolean validaSessao(Sessao sessaoNova) {
        return sessoesDaSala.stream()
                .noneMatch(sessao -> horarioEhConflitanteComOsHorariosExistentes(sessao, sessaoNova));
    }

    private boolean horarioEhConflitanteComOsHorariosExistentes(Sessao sessaoExistente, Sessao sessaoNova) {
        LocalDate hoje = LocalDate.now();

        LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje);
        LocalDateTime horarioSessaoNova = sessaoNova.getHorario().atDate(hoje);

        boolean terminaAntes = sessaoNova.getHorarioTermino().isBefore(horarioSessaoExistente.toLocalTime());
        boolean comecaDepois = sessaoExistente.getHorarioTermino().isBefore(horarioSessaoNova.toLocalTime());

        if (terminaAntes || comecaDepois) {
            return false;
        }
        return true;
    }
}



