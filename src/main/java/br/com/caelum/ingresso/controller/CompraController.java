package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import br.com.caelum.ingresso.model.form.LugarForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompraController {

    @Autowired private Carrinho carrinho;
    @Autowired private SessaoDao sessaoDao;
    @Autowired private LugarDao lugarDao;

    @PostMapping("/compra/ingressos")
    public ModelAndView finalizarPagamento(CarrinhoForm carrinhoForm) {
        ModelAndView view = new ModelAndView("redirect:/compra");
        carrinhoForm.toIngresso(sessaoDao, lugarDao).forEach(carrinho::adiciona);
        return view;
    }

    @GetMapping("/compra")
    public ModelAndView finalizarCompra() {
        ModelAndView view = new ModelAndView("compra/pagamento");
        view.addObject("carrinho", carrinho);
        return view;
    }

}
