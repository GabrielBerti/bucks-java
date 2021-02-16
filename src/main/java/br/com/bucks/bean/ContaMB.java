package br.com.bucks.bean;

import br.com.bucks.DAO.ContaDAO;
import br.com.bucks.DAO.LovDAO;
import br.com.bucks.model.Conta;
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
public class ContaMB implements Serializable {

    private List<Conta> listaContas = new ArrayList();
    private ContaDAO contaDAO = new ContaDAO();

    private List<LovDAO> lovContaTipo = new ArrayList();
    private List<LovDAO> lovCdSit = new ArrayList();

    private Integer num;
    private Integer contaTipo;
    private Integer cdSit;
    private String descr;
    private Double vlSaldo;
    private String obs;

    private Conta selectedConta;

    @PostConstruct
    public void init() {
        this.todasContas();
        this.carregarLovContaTipo();
        this.carregarLovCdSit();
    }

    public void todasContas() {
        this.setListaContas(contaDAO.todas());
    }

    public void inserirConta() {
        System.out.println("ContaMB - inserirConta");

        Conta conta = new Conta();

        if (this.getNum() != null) {
            conta.setNum(this.getNum());
            conta.setDt_alt(new Date());
            conta.setUs_alt("CADASTRO CONTA");
        }

        conta.setContaTipo(this.getContaTipo());
        conta.setDescr(this.getDescr());
        conta.setCdSit(this.getCdSit());
        conta.setVlSaldo(this.getVlSaldo());
        conta.setCdSit(this.getCdSit());
        conta.setObs(this.getObs());
        conta.setDt_cad(new Date());
        conta.setUs_cad("CADASTRO CONTA");

        contaDAO.inserirConta(conta);
        this.todasContas();
        this.limpaCampos();
    }

    public void limpaCampos() {
        this.setNum(null);
        this.setCdSit(num);
        this.setContaTipo(num);
        this.setDescr(null);
        this.setVlSaldo(null);
        this.setCdSit(null);
        this.setObs(null);
    }

    public void limpaNum() {
        this.setNum(null);
    }

    public void actionNova() {
        System.out.println(" <<<ContaMB actionNova >>>");
        this.limpaNum();
        this.setVlSaldo(0.0);
        this.setCdSit(1);
    }

    public List<Conta> getListaContas() {
        return listaContas;
    }

    public ContaDAO getContaDAO() {
        return contaDAO;
    }

    public void setContaDAO(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public Integer getContaTipo() {
        return contaTipo;
    }

    public void setContaTipo(Integer contaTipo) {
        this.contaTipo = contaTipo;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Double getVlSaldo() {
        return vlSaldo;
    }

    public void setVlSaldo(Double vlSaldo) {
        this.vlSaldo = vlSaldo;
    }

    public Integer getCdSit() {
        return cdSit;
    }

    public void setCdSit(Integer cdSit) {
        this.cdSit = cdSit;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setListaContas(List<Conta> listaContas) {
        this.listaContas = listaContas;
    }

    public ContaDAO getContas() {
        return contaDAO;
    }

    public void setContas(ContaDAO contas) {
        this.contaDAO = contas;
    }

    public String getCdTipoDescr(Integer pCd) {
        return contaDAO.getCdTipoDescr(pCd);
    }

    public String getCdSitDescr(Integer pCd) {
        return contaDAO.getCdSitDescr(pCd);
    }

    public void linhaSelecionada(SelectEvent event) {
        this.setNum(this.getSelectedConta().getNum());
        this.setContaTipo(this.getSelectedConta().getContaTipo());
        this.setCdSit(this.getSelectedConta().getCdSit());
        this.setDescr(this.getSelectedConta().getDescr());
        this.setVlSaldo(this.getSelectedConta().getVlSaldo());
        this.setObs(this.getSelectedConta().getObs());
    }

    public Conta getSelectedConta() {
        return selectedConta;
    }

    public void setSelectedConta(Conta selectedConta) {
        this.selectedConta = selectedConta;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public boolean isSelecionado() {
        if (selectedConta != null && selectedConta.getDescr() != null) {
            return true;
        } else {
            return false;
        }

    }

    public List<LovDAO> getLovContaTipo() {
        return lovContaTipo;
    }

    public void setLovContaTipo(List<LovDAO> lovContaTipo) {
        this.lovContaTipo = lovContaTipo;
    }

    public void carregarLovContaTipo() {
        this.setLovContaTipo(contaDAO.carregarLov("CONTA.fk_conta_tipo_num", false));
    }

    public List<LovDAO> getLovCdSit() {
        return lovCdSit;
    }

    public void setLovCdSit(List<LovDAO> lovCdSit) {
        this.lovCdSit = lovCdSit;
    }

    public void carregarLovCdSit() {
        this.setLovCdSit(contaDAO.carregarLov("CONTA.cd_sit", false));
    }

}
