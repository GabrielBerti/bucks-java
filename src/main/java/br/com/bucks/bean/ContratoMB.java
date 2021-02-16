package br.com.bucks.bean;

import br.com.bucks.DAO.ContratoDAO;
import br.com.bucks.DAO.ContratoPessoaDAO;
import br.com.bucks.DAO.PessoaDAO;
import br.com.bucks.model.Contrato;
import br.com.bucks.model.ContratoPessoa;
import br.com.bucks.model.ContratoPessoaId;
import br.com.bucks.model.ContratoTipoEnum;
import br.com.bucks.model.Pessoa;
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
public class ContratoMB implements Serializable {

    // Contrato
    private List<Contrato> listaContratos = new ArrayList();
    private List<ContratoTipoEnum> listaContratoTipos = new ArrayList();
    private ContratoDAO contratoDAO = new ContratoDAO();

//    private Contrato contrato;
    //Contrato
    private Integer num;
    private String descr, obs;
    private Date dtBase;
    private Double vlBase;
    private ContratoTipoEnum contratoTipo;

    // ContratoPessoa
    private List<ContratoPessoa> listaContratoPessoas = new ArrayList();
    private ContratoPessoaDAO contratoPessoaDAO = new ContratoPessoaDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private ContratoPessoa contratoPessoa;
    private List<Pessoa> lovPessoas = new ArrayList();

    private Contrato contrato;
    private Pessoa pessoa;

    @PostConstruct
    public void init() {
        this.todosContratos();

        this.carregarContratoPessoas();
        this.carregarLovPessoas();

    }

    public void carregarContratoPessoas() {
        System.out.println("<<<carregarContratoPessoas>>> testando => num:" + this.num);
        this.setListaContratoPessoas(contratoPessoaDAO.porContrato(this.num));
    }

    public void carregarLovPessoas() {
        this.setLovPessoas(pessoaDAO.todas());
    }

    public void contratoPessoaLinhaSelecionada(SelectEvent event) {
        this.setContrato(this.getSelectedContratoPessoa().getContratoPessoaId().getContrato());
        this.setPessoa(this.getSelectedContratoPessoa().getContratoPessoaId().getPessoa());
    }

    public boolean isContratoPessoaSeleciona() {
        if (contratoPessoa != null && contratoPessoa.getDtCad() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void inserirContratoPessoa() {

        System.out.println(" ");
        System.out.println("<<<ContratoMB.inserir>>> carregar");

        ContratoPessoa contratoPessoa = new ContratoPessoa();

        System.out.println("<<<ContratoMB.inserir>>> carregar contratoPessoaid:");
        Contrato contrato = new Contrato();
        contrato = contratoDAO.porId(this.getSelectedContrato().getNum());

        System.out.println("<<<ContratoMB.inserir>>> carregar contratoPessoaid:");
        ContratoPessoaId contratoPessoaId = new ContratoPessoaId();
        contratoPessoaId.setContrato(contrato);
//        contratoPessoaid.setContrato(this.getContrato());
        contratoPessoaId.setPessoa(this.getPessoa());

        System.out.println("<<<ContratoMB.inserir>>> carregado contratoPessoaid:");

        contratoPessoa.setContratoPessoaId(contratoPessoaId);
        contratoPessoa.setDtCad(new Date());
        contratoPessoa.setUsCad("CADASTRO CONTRATO");

        contratoPessoaDAO.inserir(contratoPessoa);

        this.carregarContratoPessoas();
        this.limpaCamposContratoPessoa();
    }

    public void limpaCamposContratoPessoa() {
        this.setContrato(null);
        this.setPessoa(null);
    }

    public void todosContratos() {
        this.setListaContratos(contratoDAO.todas());

        //prencher o objeto Contrato
//        this.setContratoTipo(ContratoTipoEnum.);
    }

    public void linhaSelContrato(SelectEvent event) {
        this.setNum(this.getSelectedContrato().getNum());
        this.setContratoTipo(this.getSelectedContrato().getContratoTipo());
        this.setDescr(this.getSelectedContrato().getDescr());
        this.setDtBase(this.getSelectedContrato().getDtBase());
        this.setVlBase(this.getSelectedContrato().getVlBase());
        this.setObs(this.getSelectedContrato().getObs());

        //preencher DataTable ContratoPessoa
        this.carregarContratoPessoas();
    }

    public void linhaSelContratoPessoa(SelectEvent event) {
        this.setContrato(this.getSelectedContratoPessoa().getContratoPessoaId().getContrato());
        this.setPessoa(this.getSelectedContratoPessoa().getContratoPessoaId().getPessoa());
    }

    public boolean isContratoSeleciona() {
        if (contrato != null && contrato.getDescr() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void limpaNum() {
        this.setNum(null);
    }

    public void inserirContrato() {
        Contrato contrato = new Contrato();

        System.out.println(" ");
        if (this.getNum() != null) {
            System.out.println("<<<ContratoMB novo num: " + this.getNum() + ">>>");
            contrato.setNum(this.getNum());

            contrato.setDtAlt(new Date());
            contrato.setUsAlt("CADASTRO TITULO");
        }

        System.out.println("<<<ContratoMB num: " + this.getNum() + ">>>");
        contrato.setContratoTipo(this.getContratoTipo());
        contrato.setDescr(this.getDescr());
        contrato.setDtBase(this.getDtBase());
        contrato.setVlBase(this.getVlBase());
        contrato.setObs(this.getObs());
        contrato.setDtCad(new Date());
        contrato.setUsCad("CADASTRO CONTRATO");

        contratoDAO.inserir(contrato);

        this.todosContratos();
        this.limpaCampos();
    }

    public void excluirContrato() {
        contratoDAO.excluir(this.getNum());

        this.todosContratos();
    }

    public void limpaCampos() {
        this.setNum(null);
        this.setContratoTipo(null);
        this.setDescr(null);
        this.setDtBase(null);
        this.setVlBase(null);
        this.setObs(null);
    }
    
    public void actionNova() {
        System.out.println(" <<<ContratoMB actionNova >>>");
        this.limpaCampos();
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////GETTERS AND SETTERS///////////////////////////////////////////
    public List<Contrato> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<Contrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public ContratoDAO getContratoDAO() {
        return contratoDAO;
    }

    public void setContratoDAO(ContratoDAO contratoDAO) {
        this.contratoDAO = contratoDAO;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getDtBase() {
        return dtBase;
    }

    public void setDtBase(Date dtBase) {
        this.dtBase = dtBase;
    }

    public Double getVlBase() {
        return vlBase;
    }

    public void setVlBase(Double vlBase) {
        this.vlBase = vlBase;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Contrato getSelectedContrato() {
        return contrato;
    }

    public void setSelectedContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public ContratoTipoEnum getContratoTipo() {
        return contratoTipo;
    }

    public void setContratoTipo(ContratoTipoEnum contratoTipo) {
        this.contratoTipo = contratoTipo;
    }

    /*Retornar lista para o selectOneMenu*/
    public ContratoTipoEnum[] getContratoTipos() {
        return ContratoTipoEnum.values();
    }

//    public EnumSet<ContratoTipoEnum> listarAtributos() {
//        return EnumSet.allOf(ContratoTipoEnum.class);
//    }
    public List<ContratoTipoEnum> getListaContratoTipo() {
        return listaContratoTipos;
    }

    public void setListaContratoTipo(List<ContratoTipoEnum> listaContratoTipo) {
        this.listaContratoTipos = listaContratoTipo;
    }
//
//    public void todosContratoTipo() {        
//        setListaContratoTipo((List<ContratoTipoEnum>) EnumSet.allOf(ContratoTipoEnum.class));
////        this.setListaContas(tituloDAO.todasContas());
//    }

    public List<Pessoa> getLovPessoas() {
        return lovPessoas;
    }

    public void setLovPessoas(List<Pessoa> lovPessoas) {
        this.lovPessoas = lovPessoas;
    }

    public List<ContratoPessoa> getListaContratoPessoas() {
        return listaContratoPessoas;
    }

    public void setListaContratoPessoas(List<ContratoPessoa> listaContratoPessoas) {
        this.listaContratoPessoas = listaContratoPessoas;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ContratoPessoa getSelectedContratoPessoa() {
        return contratoPessoa;
    }

    public void setSelectedContratoPessoa(ContratoPessoa contratoPessoa) {
        this.contratoPessoa = contratoPessoa;
    }

    public ContratoPessoa getContratoPessoa() {
        return contratoPessoa;
    }

    public void setContratoPessoa(ContratoPessoa contratoPessoa) {
        this.contratoPessoa = contratoPessoa;
    }

}
