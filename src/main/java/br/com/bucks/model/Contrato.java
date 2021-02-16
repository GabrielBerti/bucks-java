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
import javax.persistence.Enumerated;
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
@Table(name = "contrato")
public class Contrato implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_contrato", sequenceName = "seq_contrato")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contrato")
    private Integer id;

    @Column(name = "fk_contrato_tipo_id", nullable = false)
    @Enumerated
    private ContratoTipoEnum contratoTipo; // pause...refatorar

    @Column(name = "descr", nullable = false, length = 100)
    private String descr;

    @Column(name = "dt_base", nullable = false)
    private Date dtBase;

    @Column(name = "vl_base")
    private Double vlBase;

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;

    @Column(name = "dt_alt")
    private Date dtAlt;

    @Column(name = "us_alt", length = 30)
    private String usAlt;

    @Column(name = "obs", length = 2000)
    private String obs;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////getters and setters/////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Integer getNum() {
        return id;
    }

    public void setNum(Integer id) {
        this.id = id;
    }

    public ContratoTipoEnum getContratoTipo() {
        return contratoTipo;
    }

    public void setContratoTipo(ContratoTipoEnum contratoTipo) {
        this.contratoTipo = contratoTipo;
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

    public Date getDtAlt() {
        return dtAlt;
    }

    public void setDtAlt(Date dtAlt) {
        this.dtAlt = dtAlt;
    }

    public String getUsAlt() {
        return usAlt;
    }

    public void setUsAlt(String usAlt) {
        this.usAlt = usAlt;
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
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
