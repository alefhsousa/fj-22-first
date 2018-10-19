package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarrinhoForm {
    List<Ingresso> ingressos = new ArrayList<>();

    public List<Ingresso> toIngresso(SessaoDao sessaoDao, LugarDao lugarDao) {
        return ingressos.stream().map( ingresso -> {
            Sessao sessao = sessaoDao.getSessaoById(ingresso.getSessao().getId());
            Lugar lugar = lugarDao.getLugarById(ingresso.getLugar().getId());
            return new Ingresso(sessao, ingresso.getTipoIngresso(), lugar);
        }).collect(Collectors.toList());
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
}
