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

// pause...refatorar nome da classe
@Entity
@Table(name = "titulo_grupo")
public class GrupoTitulo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_titulo_grupo", sequenceName = "seq_titulo_grupo")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_titulo_grupo")
    private Integer id;

    @Column(name = "descr", nullable = false, length = 2000)
    private String descr;

    @Column(name = "cd_tipo", nullable = false, length = 1)
    private String cdTipo;

    @Column(name = "cd_contabiliza", nullable = false, length = 1)
    private String cdContabiliza; // S = Sim / N = Não

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;

    @Column(name = "dt_alt")
    private Date dtAlt;

    @Column(name = "us_alt", length = 30)
    private String usAlt;

    ////////////////////////////////////////////////////////////////////////////
    //////////////// Getters and Setters ///////////////////////////////////////
    public Integer getNum() {
        return id;
    }

    public void setNum(Integer id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(String cdTipo) {
        this.cdTipo = cdTipo;
    }

    public String getCdContabiliza() {
        return cdContabiliza;
    }

    public void setCdContabiliza(String cdContabiliza) {
        this.cdContabiliza = cdContabiliza;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final GrupoTitulo other = (GrupoTitulo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
