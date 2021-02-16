/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author La Laina
 */
@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_conta", sequenceName = "seq_conta")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_conta")
    private Integer id;

    @Column(name = "fk_conta_tipo_id", nullable = false)
    private Integer contaTipo; //pause...mudar para id

    @Column(name = "descr", nullable = false, length = 100)
    private String descr;

    @Column(name = "vl_saldo")
    private Double vlSaldo;

    @Column(name = "cd_sit", nullable = false)
    private Integer cdSit;

    @Column(name = "dt_cad", nullable = false)
    private Date dt_cad;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String us_cad;

    @Column(name = "dt_alt")
    private Date dt_alt;

    @Column(name = "us_alt", length = 30)
    private String us_alt;

    @Column(name = "obs", length = 2000)
    private String obs;

    public Integer getNum() {
        return id;
    }

    public void setNum(Integer id) {
        this.id = id;
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

    public Date getDt_cad() {
        return dt_cad;
    }

    public void setDt_cad(Date dt_cad) {
        this.dt_cad = dt_cad;
    }

    public Date getDt_alt() {
        return dt_alt;
    }

    public void setDt_alt(Date dt_alt) {
        this.dt_alt = dt_alt;
    }

    public String getUs_cad() {
        return us_cad;
    }

    public void setUs_cad(String us_cad) {
        this.us_cad = us_cad;
    }

    public Date getDtAlt() {
        return dt_alt;
    }

    public String getUs_alt() {
        return us_alt;
    }

    public void setUs_alt(String us_alt) {
        this.us_alt = us_alt;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conta other = (Conta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
