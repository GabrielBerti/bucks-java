package br.com.bucks.bean;

import br.com.bucks.DAO.BucksUtilDAO;
import br.com.bucks.DAO.GrupoTituloDAO;
import br.com.bucks.DAO.LovDAO;
import br.com.bucks.DAO.OrcamentoDAO;
import br.com.bucks.model.GrupoTitulo;
import br.com.bucks.model.Orcamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
//@RequestScoped
public class OrcamentoMB {

    //Lista principal
    private List<Orcamento> listaOrcamentos = new ArrayList();

    //Listas
    private List<GrupoTitulo> listaGruposTitulos = new ArrayList();

    //DAO
    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private GrupoTituloDAO grupoTituloDAO = new GrupoTituloDAO();
    private LovDAO lovDAO = new LovDAO();

    //selected
    private Orcamento selectedOrcamento = new Orcamento();

    // Filtros
    private List<GrupoTitulo> filtroListaTituloGrupo = new ArrayList();
    private GrupoTitulo filtroTituloGrupo = new GrupoTitulo();
    
    //apoio
    private BucksUtilDAO bucksUtilDAO = new BucksUtilDAO();

    private Integer id;
    private GrupoTitulo grupoTitulo;
    private Integer ano;
    private Integer mes;
    private Double vl;
    private String usCad;
    private Date dtCad;
    private String usAlt;
    private Date dtAlt;
    private String obs;

    // Filtros
    private Integer filtroMes;
    private Integer filtroAno;
    private String filtroPago;
    private List<LovDAO> filtroLovPago = new ArrayList();

    @PostConstruct
    public void init() {
        setarFiltros();
        carregarListas();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getVl() {
        return vl;
    }

    public void setVl(Double vl) {
        this.vl = vl;
    }

    public String getUsCad() {
        return usCad;
    }

    public void setUsCad(String usCad) {
        this.usCad = usCad;
    }

    public Date getDtCad() {
        return dtCad;
    }

    public void setDtCad(Date dtCad) {
        this.dtCad = dtCad;
    }

    public String getUsAlt() {
        return usAlt;
    }

    public void setUsAlt(String usAlt) {
        this.usAlt = usAlt;
    }

    public Date getDtAlt() {
        return dtAlt;
    }

    public void setDtAlt(Date dtAlt) {
        this.dtAlt = dtAlt;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Orcamento getSelectedOrcamento() {
        return selectedOrcamento;
    }

    public void setSelectedOrcamento(Orcamento selectedOrcamento) {
        this.selectedOrcamento = selectedOrcamento;
    }

    public GrupoTitulo getGrupoTitulo() {
        return grupoTitulo;
    }

    public void setGrupoTitulo(GrupoTitulo grupoTitulo) {
        this.grupoTitulo = grupoTitulo;
    }

    public List<Orcamento> getListaOrcamentos() {
        return listaOrcamentos;
    }

    public void setListaOrcamentos(List<Orcamento> listaOrcamentos) {
        this.listaOrcamentos = listaOrcamentos;
    }

    public Integer getFiltroMes() {
        return filtroMes;
    }

    public void setFiltroMes(Integer filtroMes) {
        this.filtroMes = filtroMes;
    }

    public Integer getFiltroAno() {
        return filtroAno;
    }

    public void setFiltroAno(Integer filtroAno) {
        this.filtroAno = filtroAno;
    }

    public GrupoTitulo getFiltroTituloGrupo() {
        return filtroTituloGrupo;
    }

    public void setFiltroTituloGrupo(GrupoTitulo filtroTituloGrupo) {
        this.filtroTituloGrupo = filtroTituloGrupo;
    }

    public String getFiltroPago() {
        return filtroPago;
    }

    public void setFiltroPago(String filtroPago) {
        this.filtroPago = filtroPago;
    }

    public List<LovDAO> getFiltroLovPago() {
        return filtroLovPago;
    }

    public void setFiltroLovPago(List<LovDAO> filtroLovPago) {
        this.filtroLovPago = filtroLovPago;
    }

    public List<GrupoTitulo> getListaGruposTitulos() {
        return listaGruposTitulos;
    }

    public void setListaGruposTitulos(List<GrupoTitulo> listaGruposTitulos) {
        this.listaGruposTitulos = listaGruposTitulos;
    }
    
    public void todosGrupostitulos() {
        this.setListaGruposTitulos(grupoTituloDAO.todosGruposTitulos());
    }
    
    private void carregarListas() {
        this.todosOrcamentos();
        this.todosGrupostitulos();
    }
    
    private void carregarFiltroLovPago() {
        this.setFiltroLovPago(lovDAO.carregarLov("ALL SIM NAO", false));
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setId(this.getSelectedOrcamento().getId());
        this.setGrupoTitulo(this.getSelectedOrcamento().getGrupoTitulo());
        this.setAno(this.getSelectedOrcamento().getAno());
        this.setMes(this.getSelectedOrcamento().getMes());
        this.setVl(this.getSelectedOrcamento().getVl());
        this.setUsCad(this.getSelectedOrcamento().getUsCad());
        this.setDtCad(this.getSelectedOrcamento().getDtCad());
        this.setObs(this.getSelectedOrcamento().getObs());
    }

    public void todosOrcamentos() {
        this.setListaOrcamentos(orcamentoDAO.todas(filtroTituloGrupo.getNum(), filtroAno, filtroMes, "S", null));
    }

    public boolean isOrcamentoSeleciona() {
        if (selectedOrcamento != null && selectedOrcamento.getId() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void limpaId() {
        this.setId(null);
    }

    public void inserirOrcamento() {
        System.err.println(" <<<OrcamentoMB inserirOrcamento num:" + this.getId() + " Ano/Mes:" + this.getAno() + "/" + this.getMes() + " VL" + this.getVl());
        Orcamento orcamento = new Orcamento();

        orcamento.setDtCad(this.getDtCad());
        orcamento.setUsCad(this.getUsCad());
            
        System.out.println(" ");
        if (this.getId() != null) {
            System.out.println("<<<OrcamentoMB INSERT novo num:" + this.getId() + ">>>");
            orcamento.setId(this.getId());

            orcamento.setDtAlt(new Date());
            orcamento.setUsAlt("CADASTRO ORCAMENTO");
        } else {
            System.out.println("<<<OrcamentoMB UPDATE num:" + this.getId() + ">>>");
            orcamento.setDtCad(new Date());
            orcamento.setUsCad("CADASTRO ORCAMENTO");
        }

        orcamento.setGrupoTitulo(this.getGrupoTitulo());

        orcamento.setAno(this.getAno());
        orcamento.setMes(this.getMes());
        orcamento.setVl(this.getVl());
        orcamento.setObs(this.getObs());

        orcamentoDAO.inserirOrcamento(orcamento);
        this.todosOrcamentos();
        this.limpaCampos();
    }

    public void excluirOrcamento() {
        orcamentoDAO.excluirOrcamento(this.getId());

        this.todosOrcamentos();

        this.limpaCampos();
    }

    public void limpaCampos() {
        this.setId(null);
        this.setGrupoTitulo(null);
        this.setAno(null);
        this.setMes(null);
        this.setVl(null);
        this.setDtCad(null);
        this.setUsCad(null);
        this.setObs(null);
    }

    public void actionNova() {
        System.out.println(" <<<OrcamentoMB actionNova >>>");
        this.limpaCampos();
        this.preencherCamposInserir();
    }

    public void setarFiltros() {
        this.setFiltroAno(bucksUtilDAO.retornarAnoAtual());
        this.setFiltroMes(0);
        

//        this.carregarFiltroLovPago();
//        this.filtroPago = "ALL"; //TODOS
    }

//    ////////////////////////////////////////////////////////////////////////////
//    //////////////////// getters and setters ///////////////////////////////////

    public void copiarOrcamento() {
        System.out.println(" <<<OrcamentoMB copiarOrcamento (" + selectedOrcamento.getId()+ ")>>>");

        limpaCampos();

        this.setId(null);
        this.setGrupoTitulo(selectedOrcamento.getGrupoTitulo());
        this.setAno(selectedOrcamento.getAno());
        this.setMes(selectedOrcamento.getMes() + 1);
        this.setVl(selectedOrcamento.getVl());
        this.setUsCad(selectedOrcamento.getUsCad());
        this.setObs(selectedOrcamento.getObs());

        System.out.println("OrcamentoMB copiarOrcamento - copiado com sucesso");

    }

    private void preencherCamposInserir() {
        System.out.println(" <<<OrcamentoMB preencherCamposInserir>>>");
        // recuperar a conta que foi selecionada, pois entende-se que é para cadastrar titulo daquela conta
        if (selectedOrcamento != null) {
            this.setGrupoTitulo(selectedOrcamento.getGrupoTitulo());
        }
        
        this.setAno(bucksUtilDAO.retornarAnoAtual());
    }

}
