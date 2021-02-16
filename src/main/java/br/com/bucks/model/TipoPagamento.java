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
 * @author Lucas
 */
@Entity
@Table(name = "tipo_pagamento")
public class TipoPagamento implements Serializable {
    
    @Id
    @SequenceGenerator(name="seq_tipo_pagamento", sequenceName = "seq_tipo_pagamento")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tipo_pagamento")    
    private Integer id;
    
    @Column(name = "descr", nullable = false, length = 2000)
    private String descr;
    
    @Column(name = "dt_cad", nullable = false)
    private Date dt_cad;
    
    @Column(name = "us_cad", nullable = false, length = 30)
    private String us_cad;
    
     @Column(name = "dt_alt")
    private Date dt_alt;
    
    @Column(name = "us_alt", length = 30)
    private String us_alt;

    // pause....alterar para id
    public Integer getNum() {
        return id;
    }

    public void setNum(Integer id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public Date getDt_cad() {
        return dt_cad;
    }

    public void setDt_cad(Date dt_cad) {
        this.dt_cad = dt_cad;
    }

    public String getUs_cad() {
        return us_cad;
    }

    public void setUs_cad(String us_cad) {
        this.us_cad = us_cad;
    }

    public Date getDt_alt() {
        return dt_alt;
    }

    public void setDt_alt(Date dt_alt) {
        this.dt_alt = dt_alt;
    }

    public String getUs_alt() {
        return us_alt;
    }

    public void setUs_alt(String us_alt) {
        this.us_alt = us_alt;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final TipoPagamento other = (TipoPagamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
 
}
