package br.com.bucks.bean;

import br.com.bucks.DAO.CgRefCodesDAO;
import br.com.bucks.DAO.ContaDAO;
import br.com.bucks.DAO.LovDAO;
import br.com.bucks.model.CgRefCodes;
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
public class CgRefCodesMB implements Serializable {

  private List<CgRefCodes> listaCgRefCodes = new ArrayList();
  private CgRefCodesDAO cgRefCodesDAO = new CgRefCodesDAO();

  private Integer num;
  private String rvLowValue;
  private String rvHighValue;
  private String rvAbreviation;
  private String rvDomain;
  private String rvMeaning;

  private CgRefCodes selectedCgRefCodes;

  @PostConstruct
  public void init() {
    this.todasCgRefCodes();
  }

  public void todasCgRefCodes() {
    this.setListaCgRefCodes(cgRefCodesDAO.todasCgRefCodes());
  }

  public void inserirCgRefCodes() {
    System.out.println("CgRefCodesMB - inserirCgRefCodes");

    CgRefCodes cgRefCodes = new CgRefCodes();

    if (this.getNum() != null) {
      cgRefCodes.setNum(this.getNum());
    }

    cgRefCodes.setRvAbreviation(this.getRvAbreviation().toUpperCase());
    cgRefCodes.setRvDomain(this.getRvDomain().toUpperCase());
    cgRefCodes.setRvHighValue(this.getRvHighValue().toUpperCase());
    cgRefCodes.setRvLowValue(this.getRvLowValue().toUpperCase());
    cgRefCodes.setRvMeaning(this.getRvMeaning());

    cgRefCodesDAO.inserirCgRefCodes(cgRefCodes);
    this.todasCgRefCodes();
    //this.limpaCampos();
  }

  public void limpaNum() {
    this.setNum(null);
  }
  
  public void excluirCgRefCodes() {
    cgRefCodesDAO.excluirCgRefCodes(this.getNum());

    this.todasCgRefCodes();

    this.limpaCampos();
  }

  public void limpaCampos() {
    this.setNum(null);
    this.setRvAbreviation(null);
    this.setRvDomain(null);
    this.setRvHighValue(null);
    this.setRvLowValue(null);
    this.setRvMeaning(null);
  }

  public List<CgRefCodes> getListaCgRefCodes() {
    return listaCgRefCodes;
  }

  public void setListaCgRefCodes(List<CgRefCodes> listaCgRefCodes) {
    this.listaCgRefCodes = listaCgRefCodes;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getRvLowValue() {
    return rvLowValue;
  }

  public void setRvLowValue(String rvLowValue) {
    this.rvLowValue = rvLowValue;
  }

  public String getRvHighValue() {
    return rvHighValue;
  }

  public void setRvHighValue(String rvHighValue) {
    this.rvHighValue = rvHighValue;
  }

  public String getRvAbreviation() {
    return rvAbreviation;
  }

  public void setRvAbreviation(String rvAbreviation) {
    this.rvAbreviation = rvAbreviation;
  }

  public String getRvDomain() {
    return rvDomain;
  }

  public void setRvDomain(String rvDomain) {
    this.rvDomain = rvDomain;
  }

  public String getRvMeaning() {
    return rvMeaning;
  }

  public void setRvMeaning(String rvMeaning) {
    this.rvMeaning = rvMeaning;
  }

  public CgRefCodes getSelectedCgRefCodes() {
    return selectedCgRefCodes;
  }

  public void setSelectedCgRefCodes(CgRefCodes selectedCgRefCodes) {
    this.selectedCgRefCodes = selectedCgRefCodes;
  }

  public boolean isSelecionado() {
    if (selectedCgRefCodes != null && selectedCgRefCodes.getNum() != null) {
      return true;
    } else {
      return false;
    }

  }

  public void linhaSelecionada(SelectEvent event) {
    this.setNum(this.getSelectedCgRefCodes().getNum());
    this.setRvAbreviation(this.getSelectedCgRefCodes().getRvAbreviation());
    this.setRvDomain(this.getSelectedCgRefCodes().getRvDomain());
    this.setRvHighValue(this.getSelectedCgRefCodes().getRvHighValue());
    this.setRvLowValue(this.getSelectedCgRefCodes().getRvLowValue());
    this.setRvMeaning(this.getSelectedCgRefCodes().getRvMeaning());
  }

}
