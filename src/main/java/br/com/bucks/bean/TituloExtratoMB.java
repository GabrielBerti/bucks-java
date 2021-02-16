package br.com.bucks.bean;

import br.com.bucks.DAO.TituloExtratoDAO;
import br.com.bucks.model.Titulo;
import br.com.bucks.model.TituloExtrato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
//@RequestScoped
public class TituloExtratoMB implements Serializable {

    private List<TituloExtrato> listaTituloExtratos = new ArrayList();
    private TituloExtratoDAO tituloExtratoDAO = new TituloExtratoDAO();

    private Integer num;
    private Integer fkTituloNum;
    private Double vl;
    private Double vlSaldoAnt;
    private Double vlSaldoAtu;
    private Date dtCad;
    private String usCAd;

    private TituloExtrato selectedTit;

    @PostConstruct
    public void init() {
        this.todas();
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setFkTituloNum(this.getSelectedTit().getFkTituloNum());
        this.setVl(this.getSelectedTit().getVl());
        this.setVlSaldoAnt(this.getSelectedTit().getVlSaldoAnt());
        this.setVlSaldoAtu(this.getSelectedTit().getVlSaldoAtu());
        this.setDtCad(this.getSelectedTit().getDtCad());
        this.setUsCAd(this.getSelectedTit().getUsCad());
    }

    public void todas() {
        this.setListaTituloExtratos(tituloExtratoDAO.todas());
    }

    public boolean isTituloExtratoSeleciona() {
        if (selectedTit != null && selectedTit.getFkTituloNum() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void inserir() {
        //listaProjetos = projetos.todas();
        //this.setListaProjetos(projetoDAO.todas());
        TituloExtrato tituloExtrato = new TituloExtrato();

        tituloExtrato.setNum(this.getNum());
        tituloExtrato.setFkTituloNum(this.getNum());
        tituloExtrato.setVl(this.getVl());
        tituloExtrato.setVlSaldoAnt(this.getVlSaldoAnt());
        tituloExtrato.setVlSaldoAtu(this.getVlSaldoAtu());
        tituloExtrato.setDtCad(new Date());
        tituloExtrato.setUsCad("CADASTRO TITULO EXTRATO");

        tituloExtratoDAO.inserir(tituloExtrato);

        this.todas();
    }

//    public void excluir() {
//        tituloExtratoDAO.excluir(this.getFkTituloNum());
//        this.todas();
//    }
    ////////////////////////////////////////////////////////////////////////////
    //////////////////// getters and setters ///////////////////////////////////
    public List<TituloExtrato> getListaTituloExtratos() {
        return listaTituloExtratos;
    }

    public void setListaTituloExtratos(List<TituloExtrato> listaTituloExtratos) {
        this.listaTituloExtratos = listaTituloExtratos;
    }

    public TituloExtratoDAO getTituloExtratoDAO() {
        return tituloExtratoDAO;
    }

    public void setTituloExtratoDAO(TituloExtratoDAO tituloExtratoDAO) {
        this.tituloExtratoDAO = tituloExtratoDAO;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getFkTituloNum() {
        return fkTituloNum;
    }

    public void setFkTituloNum(Integer fkTituloNum) {
        this.fkTituloNum = fkTituloNum;
    }

    public Double getVl() {
        return vl;
    }

    public void setVl(Double vl) {
        this.vl = vl;
    }

    public Double getVlSaldoAnt() {
        return vlSaldoAnt;
    }

    public void setVlSaldoAnt(Double vlSaldoAnt) {
        this.vlSaldoAnt = vlSaldoAnt;
    }

    public Double getVlSaldoAtu() {
        return vlSaldoAtu;
    }

    public void setVlSaldoAtu(Double vlSaldoAtu) {
        this.vlSaldoAtu = vlSaldoAtu;
    }

    public Date getDtCad() {
        return dtCad;
    }

    public void setDtCad(Date dtCad) {
        this.dtCad = dtCad;
    }

    public String getUsCAd() {
        return usCAd;
    }

    public void setUsCAd(String usCAd) {
        this.usCAd = usCAd;
    }

    public TituloExtrato getSelectedTit() {
        return selectedTit;
    }

    public void setSelectedTit(TituloExtrato selectedTit) {
        this.selectedTit = selectedTit;
    }

}
