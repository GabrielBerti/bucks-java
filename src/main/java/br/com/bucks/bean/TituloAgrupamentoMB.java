/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.DAO.LovDAO;
import br.com.bucks.DAO.TituloAgrupamentoDAO;
import br.com.bucks.model.Titulo;
import br.com.bucks.model.TituloAgrupamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SC792765
 */
@ManagedBean
@ViewScoped
//@RequestScoped
public class TituloAgrupamentoMB implements Serializable {

    private List<TituloAgrupamento> listaTituloAgrupamento = new ArrayList();
    private List<Titulo> listaTitulo = new ArrayList();
    private List<LovDAO> lovCdSitTitAgrup = new ArrayList();
    
    TituloMB tituloMB = new TituloMB();

//    private List<ContratoTipoEnum> listaContratoTipos = new ArrayList();
    private TituloAgrupamentoDAO tituloAgrupamentoDAO = new TituloAgrupamentoDAO();

    private TituloAgrupamento selectedTituloAgrupamento;

    public List<Integer> listaIdTituloAgrupamento = new ArrayList<Integer>();

    private Integer id;
//    private Integer fkTituloId;
    private Titulo titulo;
    private String usCad, usAlt;
    private Date dtCad, dtAlt;
    private Integer cdTipo;

    @PostConstruct
    public void init() {
        this.todosTitulosAgrupamentos();
        //this.tituloMB.todosTitulos();
    }

    public void todosTitulosAgrupamentos() {
        this.setListaTituloAgrupamento(tituloAgrupamentoDAO.todos());
        //this.setListaTitulo(tituloAgrupamentoDAO.todosTitulos());
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setId(this.getSelectedTituloAgrupamento().getId());
        this.setTitulo(this.getSelectedTituloAgrupamento().getTitulo());
        this.setCdTipo(this.getSelectedTituloAgrupamento().getCdTipo());
        this.setUsCad(this.getSelectedTituloAgrupamento().getUsCad());
        this.setUsAlt(this.getSelectedTituloAgrupamento().getUsAlt());
        this.setDtCad(this.getSelectedTituloAgrupamento().getDtCad());
        this.setDtAlt(this.getSelectedTituloAgrupamento().getDtAlt());
    }

    public boolean isTituloAgrupamentoSeleciona() {
        if (selectedTituloAgrupamento != null && selectedTituloAgrupamento.getId() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void carregarListUltIdTituloAgrupamento() {
        this.setListaIdTituloAgrupamento(tituloAgrupamentoDAO.ultIdTituloAgrupamento());
    }

    /*public void inserir() {
    TituloAgrupamento tituloAgrupamento = new TituloAgrupamento();

    System.out.println(" ");
    if (this.getNum() != null) {
      System.out.println("<<<ContratoMB novo num: " + this.getNum() + ">>>");
      tituloAgrupamento.setNum(this.getNum());

      tituloAgrupamento.setDtAlt(new Date());
      tituloAgrupamento.setUsAlt("CADASTRO TITULO");
    }

    System.out.println("<<<tituloAgrupamento num: " + this.getNum() + ">>>");

    tituloAgrupamento.setCdTipoEnum(this.getCdTipoEnum());
    //tituloAgrupamento.setFkTituloNum(this.getFkTituloNum());
    tituloAgrupamento.setTitulo(this.getTitulo());
    tituloAgrupamento.setDtCad(this.getDtCad());
    tituloAgrupamento.setDtCad(this.getDtCad());
    tituloAgrupamento.setDtCad(this.getDtCad());
    tituloAgrupamento.setUsCad("TITULO AGRUPAMENTO");

    tituloAgrupamentoDAO.inserir(tituloAgrupamento);

    this.todosTitulosAgrupamentos();
    this.limpaCampos();
  }

  public void excluir() {
    tituloAgrupamentoDAO.excluir(this.getNum());

    this.todosTitulosAgrupamentos();
  }
     */
    public void limpaCampos() {
        this.setId(null);
        this.setTitulo(null);
        this.setCdTipo(null);
        this.setUsCad(null);
        this.setUsAlt(null);
        this.setDtCad(null);
        this.setDtAlt(null);
    }

    public String getCdTipoTitAgrupDescr(Integer pCd) {
        return tituloAgrupamentoDAO.getCdTipoTitAgrupDescr(pCd);
    }

    public List<TituloAgrupamento> getListaTituloAgrupamento() {
        return listaTituloAgrupamento;
    }

    public List<Titulo> getListaTitulo() {
        return listaTitulo;
    }

    public void setListaTitulo(List<Titulo> listaTitulo) {
        this.listaTitulo = listaTitulo;
    }

    public void setListaTituloAgrupamento(List<TituloAgrupamento> listaTituloAgrupamento) {
        this.listaTituloAgrupamento = listaTituloAgrupamento;
    }

    public TituloAgrupamentoDAO getTituloAgrupamentoDAO() {
        return tituloAgrupamentoDAO;
    }

    public void setTituloAgrupamentoDAO(TituloAgrupamentoDAO tituloAgrupamentoDAO) {
        this.tituloAgrupamentoDAO = tituloAgrupamentoDAO;
    }

    public TituloAgrupamento getSelectedTituloAgrupamento() {
        return selectedTituloAgrupamento;
    }

    public void setSelectedTituloAgrupamento(TituloAgrupamento selectedTituloAgrupamento) {
        this.selectedTituloAgrupamento = selectedTituloAgrupamento;
    }

    public List<LovDAO> getLovCdSitTitAgrup() {
        return lovCdSitTitAgrup;
    }

    public void setLovCdSitTitAgrup(List<LovDAO> lovCdSitTitAgrup) {
        this.lovCdSitTitAgrup = lovCdSitTitAgrup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer num) {
        this.id = id;
    }

//    public Integer getFkTituloId() {
//        return fkTituloId;
//    }
//
//    public void setFkTituloId(Integer fkTituloId) {
//        this.fkTituloId = fkTituloId;
//    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }
    
    

    public Integer getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(Integer cdTipo) {
        this.cdTipo = cdTipo;
    }

    public String getUsCad() {
        return usCad;
    }

    public void setUsCad(String usCad) {
        this.usCad = usCad;
    }

    public String getUsAlt() {
        return usAlt;
    }

    public void setUsAlt(String usAlt) {
        this.usAlt = usAlt;
    }

    public Date getDtCad() {
        return dtCad;
    }

    public void setDtCad(Date dtCad) {
        this.dtCad = dtCad;
    }

    public Date getDtAlt() {
        return dtAlt;
    }

    public void setDtAlt(Date dtAlt) {
        this.dtAlt = dtAlt;
    }

    public List<Integer> getListaIdTituloAgrupamento() {
        return listaIdTituloAgrupamento;
    }

    public void setListaIdTituloAgrupamento(List<Integer> listaIdTituloAgrupamento) {
        this.listaIdTituloAgrupamento = listaIdTituloAgrupamento;
    }

}
