package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.clients.imdb.DetalheFilme;
import br.com.caelum.ingresso.clients.imdb.ImdClient;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {


    @Autowired
    private FilmeDao filmeDao;

    @Autowired
    private ImdClient client;

    @Autowired
    private SessaoDao sessaoDao;


    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Filme filme){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            filme = filmeDao.findOne(id.get());
        }

        modelAndView.addObject("filme", filme);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Filme filme, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filme.getId()), filme);
        }

        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista() throws URISyntaxException {

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/filme/em-cartaz")
    public ModelAndView filmesEmCartaz() {
        ModelAndView view = new ModelAndView("filme/em-cartaz");
        view.addObject("filmes", filmeDao.findAll());
        return view;
    }

    @GetMapping(value = "/filme/{id}/detalhe")
    public ModelAndView detalheFilme(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("filme/detalhe");
        Filme filme = filmeDao.findOne(id);
        Optional<DetalheFilme> detalheFilme = this.client.buscaDetalheFilme(filme, DetalheFilme.class);
        view.addObject("sessoes", sessaoDao.buscaSessoesDoFilme(filme));
        view.addObject("detalhes", detalheFilme.orElse(new DetalheFilme()));
        return view;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }

}
