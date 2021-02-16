package br.com.bucks.bean;

import br.com.bucks.DAO.GrupoTituloDAO;
import br.com.bucks.DAO.LovDAO;
import br.com.bucks.model.GrupoTitulo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "grupoTituloMB")
@ViewScoped
//@RequestScoped
public class GrupoTituloMB implements Serializable {

    private List<GrupoTitulo> listaGrupoTitulos = new ArrayList();
    private GrupoTituloDAO grupoTituloDAO = new GrupoTituloDAO();
    //pause....retirar o lovDAO daqui...usar o GrupoTituloDAO para ser chamado e chamar o LovDAO
    private LovDAO lovDAO = new LovDAO();

    //Lov
    private List<LovDAO> lovTipo = new ArrayList();
    private List<LovDAO> lovSimNao = new ArrayList();

    private Integer num;
    private String descr;
    private String cdTipo;
    private String cdContabiliza;
    private GrupoTitulo selectedGrupoTitulo;

    @PostConstruct
    public void init() {
        this.todosGruposTitulos();
        this.carregarLovTipo();
        this.carregarLovSimNao();
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setNum(this.getSelectedGrupoTitulo().getNum());
        this.setDescr(this.getSelectedGrupoTitulo().getDescr());
        this.setCdTipo(this.getSelectedGrupoTitulo().getCdTipo());
        this.setCdContabiliza(this.getSelectedGrupoTitulo().getCdContabiliza());
    }

    public void todosGruposTitulos() {
        this.setListaGrupoTitulo(grupoTituloDAO.todas());
    }

    public void carregarLovTipo() {
        this.setLovTipo(lovDAO.carregarLov("PAGAR RECEBER", false));
    }

    public void carregarLovSimNao() {
        this.setLovSimNao(lovDAO.carregarLov("SIM NAO", false));
    }

    public boolean isGrupoTituloSeleciona() {
        if (selectedGrupoTitulo != null && selectedGrupoTitulo.getDescr() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void inserirGrupoTitulo() {
        GrupoTitulo grupoTitulo = new GrupoTitulo();

        if (this.getNum() != null) {
            grupoTitulo.setNum(this.getNum());

            grupoTitulo.setDtAlt(new Date());
            grupoTitulo.setUsAlt("CADASTRO GRUPO TITULO");
        }

        grupoTitulo.setDtCad(new Date());
        grupoTitulo.setUsCad("CADASTRO TIPO PAGTO");
        grupoTitulo.setDescr(this.getDescr());
        grupoTitulo.setCdTipo(this.getCdTipo());
        grupoTitulo.setCdContabiliza(this.getCdContabiliza());

        grupoTituloDAO.inserirGrupoTitulo(grupoTitulo);
        this.todosGruposTitulos();
        this.limpaCamposGrupoTitulo();
    }

    public void excluirGrupoTitulo() {
        grupoTituloDAO.excluirGrupoTitulo(this.getNum());
        
        this.todosGruposTitulos();
        this.limpaCamposGrupoTitulo();
    }

    public void limpaCamposGrupoTitulo() {
        this.setNum(null);
        this.setDescr(null);
        this.setCdTipo(null);
        this.setCdContabiliza(null);
    }
    
    public void actionNova() {
        System.out.println(" <<<GrupoTituloMB actionNova >>>");
        this.limpaCamposGrupoTitulo();
    }

    public List<GrupoTitulo> getListaGrupoTitulos() {
        return listaGrupoTitulos;
    }

    public void setListaGrupoTitulo(List<GrupoTitulo> listaGrupoTitulos) {
        this.listaGrupoTitulos = listaGrupoTitulos;
    }

    public GrupoTituloDAO getGrupoTituloDAO() {
        return grupoTituloDAO;
    }

    public void setGrupoTituloDAO(GrupoTituloDAO grupoTituloDAO) {
        this.grupoTituloDAO = grupoTituloDAO;
    }

    public GrupoTitulo getSelectedGrupoTitulo() {
        return selectedGrupoTitulo;
    }

    public void setSelectedGrupoTitulo(GrupoTitulo selectedGrupoTitulo) {
        this.selectedGrupoTitulo = selectedGrupoTitulo;
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

    public String getCdContabiliza() {
        return cdContabiliza;
    }

    public void setCdContabiliza(String cdContabiliza) {
        this.cdContabiliza = cdContabiliza;
    }

    public String getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(String cdTipo) {
        this.cdTipo = cdTipo;
    }

    public String cdContabilizaDescr(String pCdContabiliza) {
        return grupoTituloDAO.cdContabilizaDescr(pCdContabiliza);
    }

    public String cdTipoDescr(String cdTipo) {
        return grupoTituloDAO.cdTipoDescr(cdTipo);
    }

    public List<LovDAO> getLovTipo() {
        return lovTipo;
    }

    public void setLovTipo(List<LovDAO> lovTipo) {
        this.lovTipo = lovTipo;
    }

    public List<LovDAO> getLovSimNao() {
        return lovSimNao;
    }

    public void setLovSimNao(List<LovDAO> lovSimNao) {
        this.lovSimNao = lovSimNao;
    }

}
