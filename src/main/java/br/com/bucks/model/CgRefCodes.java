/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Gabriel Berti
 */
@Entity
@Table(name = "cg_ref_codes")
public class CgRefCodes implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "seq_cg_ref_codes", sequenceName = "seq_cg_ref_codes")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cg_ref_codes")
  private Integer id;

  @Column(name = "rv_low_value", nullable = false, length = 300)
  private String rvLowValue;

  @Column(name = "rv_high_value", length = 300)
  private String rvHighValue;

  @Column(name = "rv_abreviation", length = 300)
  private String rvAbreviation;

  @Column(name = "rv_domain", nullable = false, length = 300)
  private String rvDomain;

  @Column(name = "rv_meaning", length = 300)
  private String rvMeaning;

  public Integer getNum() {
    return id;
  }

  public void setNum(Integer id) {
    this.id = id;
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

}
