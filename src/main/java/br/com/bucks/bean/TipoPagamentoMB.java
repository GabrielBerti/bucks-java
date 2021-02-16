package br.com.bucks.bean;

import br.com.bucks.DAO.TipoPagamentoDAO;
import br.com.bucks.model.Projeto;
import br.com.bucks.model.TipoPagamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "tipoPagamentoMB")
@ViewScoped
//@RequestScoped
public class TipoPagamentoMB implements Serializable {

    private List<TipoPagamento> listaTipoPagamentos = new ArrayList();
    private TipoPagamentoDAO tipo_pagamentoDao = new TipoPagamentoDAO();
    private Integer num;
    private String descr;
    private TipoPagamento selectedTipoPagamento;

    @PostConstruct
    public void init() {
        this.todosTiposPagamentos();
    }

    public void linhaSelecionada(SelectEvent event) {
//        System.out.println("selectedProj.descr: " + selectedProj.getDescr());
        this.setNum(this.getSelectedTipoPagamento().getNum());
        this.setDescr(this.getSelectedTipoPagamento().getDescr());
    }

    public void todosTiposPagamentos() {
        //listaProjetos = projetos.todas();
        this.setListaTipoPagamentos(tipo_pagamentoDao.todas());
    }

    public boolean isTipoPagamentoSeleciona() {
        if (selectedTipoPagamento != null && selectedTipoPagamento.getDescr() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void inserirTipoPagamento() {
        TipoPagamento tipo_pagamento = new TipoPagamento();

        if (this.getNum() != null) {
            tipo_pagamento.setNum(this.getNum());

            tipo_pagamento.setDt_alt(new Date());
            tipo_pagamento.setUs_alt("CADASTRO TIPO PAGTO");
        }

        tipo_pagamento.setDescr(this.getDescr());
        tipo_pagamento.setDt_cad(new Date());
        tipo_pagamento.setUs_cad("CADASTRO TIPO PAGTO");

        tipo_pagamentoDao.inserirTipoPagamento(tipo_pagamento);
        this.todosTiposPagamentos();
        this.limpaCamposTipoPagamento();
    }

    public void excluirTipoPagamento() {

        tipo_pagamentoDao.excluirTipoPagamento(this.getNum());

        this.todosTiposPagamentos();
        this.limpaCamposTipoPagamento();
    }

    public void limpaCamposTipoPagamento() {
        this.setNum(null);
        this.setDescr(null);
    }

    public void actionNova() {
        System.out.println(" <<<TipoPagamentoMB actionNova >>>");
        this.limpaCamposTipoPagamento();
    }

    public List<TipoPagamento> getListaTipoPagamentos() {
        return listaTipoPagamentos;
    }

    public void setListaTipoPagamentos(List<TipoPagamento> listaTipoPagamentos) {
        this.listaTipoPagamentos = listaTipoPagamentos;
    }

    public TipoPagamentoDAO getTipo_pagamentoDao() {
        return tipo_pagamentoDao;
    }

    public void setTipo_pagamentoDao(TipoPagamentoDAO tipo_pagamentoDao) {
        this.tipo_pagamentoDao = tipo_pagamentoDao;
    }

    public TipoPagamento getSelectedTipoPagamento() {
        return selectedTipoPagamento;
    }

    public void setSelectedTipoPagamento(TipoPagamento selectedTipoPagamento) {
        this.selectedTipoPagamento = selectedTipoPagamento;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
