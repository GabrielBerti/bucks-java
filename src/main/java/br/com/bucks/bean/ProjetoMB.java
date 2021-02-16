package br.com.bucks.bean;

import br.com.bucks.DAO.ProjetoDAO;
import br.com.bucks.model.Projeto;
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
public class ProjetoMB implements Serializable {

    private List<Projeto> listaProjetos = new ArrayList();
    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private Integer num;
    private String descr;
    private Double vl_orcado;
    private Date dt_prev_ini;
    private Date dt_prev_fim;
    private String obs;
    private Projeto selectedProj;

    @PostConstruct
    public void init() {
        this.todasObras();
    }

    public void linhaSelecionada(SelectEvent event) {
//        System.out.println("selectedProj.descr: " + selectedProj.getDescr());
        this.setNum(this.getSelectedProj().getNum());
        this.setDescr(this.getSelectedProj().getDescr());
        this.setVl_orcado(this.getSelectedProj().getVl_orcado());
        this.setDt_prev_ini(this.getSelectedProj().getDt_prev_ini());
        this.setDt_prev_fim(this.getSelectedProj().getDt_prev_fim());
        this.setObs(this.getSelectedProj().getObs());
    }

    public void todasObras() {
        //listaProjetos = projetos.todas();
        this.setListaProjetos(projetoDAO.todas());
    }

//    public void prepararEdicao() {
//        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
//    }
    public boolean isProjetoSeleciona() {
        if (selectedProj != null && selectedProj.getDescr() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void inserirProjeto() {
        //listaProjetos = projetos.todas();
        //this.setListaProjetos(projetoDAO.todas());
        Projeto projeto = new Projeto();

        if (this.getNum() != null) {
            projeto.setNum(this.getNum());
            
            projeto.setDt_alt(new Date());
            projeto.setUs_alt("CADASTRO OBRA");
        }

        //projeto.setNum(17);
        projeto.setDescr(this.getDescr());
        projeto.setVl_orcado(this.getVl_orcado());
        projeto.setDt_prev_ini(this.getDt_prev_ini());
        projeto.setDt_prev_fim(this.getDt_prev_fim());
        projeto.setObs(this.getObs());
        projeto.setDt_cad(new Date());
        projeto.setUs_cad("CADASTRO OBRA");

        projetoDAO.inserirProjeto(projeto);
        this.todasObras();
        this.limpaCamposProjeto();
    }

    public void excluirProjeto() {
        //listaProjetos = projetos.todas();
        //this.setListaProjetos(projetoDAO.todas());
//        Projeto projeto = new Projeto();
//
//        projeto.setNum(this.getNum());
//        //projeto.setNum(17);
//        projeto.setDescr(this.getDescr());
//        projeto.setVl_orcado(this.getVl_orcado());
//        projeto.setDt_prev_ini(this.getDt_prev_ini());
//        projeto.setDt_prev_fim(this.getDt_prev_fim());
//        projeto.setObs(this.getObs());
//        projeto.setDt_cad(new Date());
//        projeto.setUs_cad("Cadastro Obra");

        projetoDAO.excluirProjeto(this.getNum());
                
        this.todasObras();
        this.limpaCamposProjeto();
    }

    public void limpaCamposProjeto() {
        this.setNum(null);
        this.setDescr(null);
        this.setVl_orcado(null);
        this.setDt_prev_ini(null);
        this.setDt_prev_fim(null);
        this.setObs(null);
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<Projeto> getListaProjetos() {
        return listaProjetos;
    }

    public ProjetoDAO getProjetoDAO() {
        return projetoDAO;
    }

    public void setProjetoDAO(ProjetoDAO projetoDAO) {
        this.projetoDAO = projetoDAO;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Double getVl_orcado() {
        return vl_orcado;
    }

    public void setVl_orcado(Double vl_orcado) {
        this.vl_orcado = vl_orcado;
    }

    public Date getDt_prev_ini() {
        return dt_prev_ini;
    }

    public void setDt_prev_ini(Date dt_prev_ini) {
        this.dt_prev_ini = dt_prev_ini;
    }

    public Date getDt_prev_fim() {
        return dt_prev_fim;
    }

    public void setDt_prev_fim(Date dt_prev_fim) {
        this.dt_prev_fim = dt_prev_fim;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setListaProjetos(List<Projeto> listaProjetos) {
        this.listaProjetos = listaProjetos;
    }

    public ProjetoDAO getProjetos() {
        return projetoDAO;
    }

    public void setProjetos(ProjetoDAO projetos) {
        this.projetoDAO = projetos;
    }

    public Projeto getSelectedProj() {
        return selectedProj;
    }

    public void setSelectedProj(Projeto selectedProj) {
        this.selectedProj = selectedProj;
    }
//    

}
