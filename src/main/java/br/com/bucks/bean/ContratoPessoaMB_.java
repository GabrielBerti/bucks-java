package br.com.bucks.bean;

import br.com.bucks.DAO.ContratoPessoaDAO;
import br.com.bucks.DAO.PessoaDAO;
import br.com.bucks.model.Contrato;
import br.com.bucks.model.ContratoPessoa;
import br.com.bucks.model.ContratoPessoaId;
import br.com.bucks.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
//@RequestScoped
public class ContratoPessoaMB_ implements Serializable {

    private List<ContratoPessoa> listaContratoPessoas = new ArrayList();

    private ContratoPessoaDAO contratoPessoaDAO = new ContratoPessoaDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();    

    private ContratoPessoa selectedContratoPessoa;
    
    private List<Pessoa> lovPessoas = new ArrayList();

    private Contrato contrato;
    private Pessoa pessoa;
    private Date dtCad;
    private String usCad;

    @PostConstruct
    public void init() {
        this.todos();
        this.carregarLovPessoas();
    }

    public void todos() {
        this.setListaContratoPessoas(contratoPessoaDAO.todas());
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setContrato(this.getSelectedContratoPessoa().getContratoPessoaId().getContrato());
        this.setPessoa(this.getSelectedContratoPessoa().getContratoPessoaId().getPessoa());
        this.setDtCad(this.getSelectedContratoPessoa().getDtCad());
        this.setUsCad(this.getSelectedContratoPessoa().getUsCad());
    }

    public boolean isContratoPessoaSeleciona() {
        if (selectedContratoPessoa != null && selectedContratoPessoa.getDtCad() != null) {
            return true;
        } else {
            return false;
        }

    }
    
    public void carregarLovPessoas() {
        this.setLovPessoas(pessoaDAO.todas());
    }

    public void inserir() {

        System.out.println(" ");
        System.out.println("<<<ContratoPessoaMB.inserir>>> carregar");
        ContratoPessoa contratoPessoa = new ContratoPessoa();

        System.out.println("<<<ContratoPessoaMB.inserir>>> carregar contratoPessoaid:");
        ContratoPessoaId contratoPessoaid = new ContratoPessoaId();
        contratoPessoaid.setContrato(this.getContrato());
        contratoPessoaid.setPessoa(this.getPessoa());

        System.out.println("<<<ContratoPessoaMB.inserir>>> carregado contratoPessoaid:");

        contratoPessoa.setContratoPessoaId(contratoPessoaid);
        contratoPessoa.setDtCad(new Date());
        contratoPessoa.setUsCad("CADASTRO CONTRATO");

        contratoPessoaDAO.inserir(contratoPessoa);

        this.todos();
        this.limpaCampos();
    }

//    public void excluir() {
//        contratoPessoaDAO.excluir(this.getNum());
//
//        this.todosContratos();
//    }
    public void limpaCampos() {
        this.setContrato(null);
        this.setPessoa(null);
        this.setDtCad(null);
        this.setUsCad(null);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////GETTERS AND SETTERS///////////////////////////////////////////
    public List<ContratoPessoa> getListaContratoPessoas() {
        return listaContratoPessoas;
    }

    public void setListaContratoPessoas(List<ContratoPessoa> listaContratoPessoas) {
        this.listaContratoPessoas = listaContratoPessoas;
    }

    public Date getDtCad() {
        return dtCad;
    }

    public void setDtCad(Date dtCad) {
        this.dtCad = dtCad;
    }

    public String getUsCad() {
        return usCad;
    }

    public void setUsCad(String usCad) {
        this.usCad = usCad;
    }

    public ContratoPessoa getSelectedContratoPessoa() {
        return selectedContratoPessoa;
    }

    public void setSelectedContratoPessoa(ContratoPessoa selectedContratoPessoa) {
        this.selectedContratoPessoa = selectedContratoPessoa;
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

    public List<Pessoa> getLovPessoas() {
        return lovPessoas;
    }

    public void setLovPessoas(List<Pessoa> lovPessoas) {
        this.lovPessoas = lovPessoas;
    }

    
    
}
