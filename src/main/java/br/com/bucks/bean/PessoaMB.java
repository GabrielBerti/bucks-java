package br.com.bucks.bean;

import br.com.bucks.DAO.PessoaDAO;
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
public class PessoaMB implements Serializable {

    private List<Pessoa> listaPessoas = new ArrayList();
    private PessoaDAO pessoaDAO = new PessoaDAO();

    private Pessoa selectedPessoa;

    private Integer id;
    private String nome, obs;

    @PostConstruct
    public void init() {
        this.todasPessoas();
    }

    public void todasPessoas() {
        this.setListaPessoas(pessoaDAO.todas());
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setId(this.getSelectedPessoa().getId());
        this.setNome(this.getSelectedPessoa().getNome());
        this.setObs(this.getSelectedPessoa().getObs());
    }

    public boolean isPessoaSeleciona() {
        if (selectedPessoa != null && selectedPessoa.getNome() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void limpaNum() {
        this.setId(null);
    }

    public void inserir() {
        Pessoa pessoa = new Pessoa();

        if (this.getId() != null) {
            pessoa.setId(this.getId());

            pessoa.setDtAlt(new Date());
            pessoa.setUsAlt("CADASTRO PESSOA");
        }

        pessoa.setNome(this.getNome());
        pessoa.setObs(this.getObs());
        pessoa.setDtCad(new Date());
        pessoa.setUsCad("CADASTRO PESSOA");

        pessoaDAO.inserir(pessoa);

        this.todasPessoas();
        this.limpaCampos();
    }

    public void excluir() {
        pessoaDAO.excluir(this.getId());

        this.todasPessoas();
    }

    public void limpaCampos() {
        this.setId(null);
        this.setNome(null);
        this.setObs(null);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////GETTERS AND SETTERS///////////////////////////////////////////
    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getSelectedPessoa() {
        return selectedPessoa;
    }

    public void setSelectedPessoa(Pessoa selectedPessoa) {
        this.selectedPessoa = selectedPessoa;
    }

}
