/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "projeto")
public class Projeto implements Serializable {
    
    @Id
    @SequenceGenerator(name="projeto_seq", sequenceName = "projeto_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "projeto_seq")    
    private Integer num;
    
    @Column(name = "descr", nullable = false, length = 2000)
    private String descr;
    
    @Column(name = "dt_cad", nullable = false)
    private Date dt_cad;
    
    @Column(name = "us_cad", nullable = false, length = 30)
    private String us_cad;
    
    @Column(name = "vl_orcado")
    private Double vl_orcado;
    
    @Column(name = "dt_prev_ini", nullable = false)
    private Date dt_prev_ini;
    
    @Column(name = "dt_prev_fim")
    private Date dt_prev_fim;
    
    @Column(name = "dt_alt")
    private Date dt_alt;
    
    @Column(name = "us_alt", length = 30)
    private String us_alt;
    
    @Column(name = "obs", length = 2000)
    private String obs;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    public Double getVl_orcado() {
        return vl_orcado;
    }

    public void setVl_orcado(Double vl_orcado) {
        this.vl_orcado = vl_orcado;
    }

    public Date getDt_prev_ini() {
        return dt_prev_ini;
    }

    public void setDt_prev_ini(Date dt_prev_ini) {
        this.dt_prev_ini = dt_prev_ini;
    }

    public Date getDt_prev_fim() {
        return dt_prev_fim;
    }

    public void setDt_prev_fim(Date dt_prev_fim) {
        this.dt_prev_fim = dt_prev_fim;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "Projeto{" + "num=" + num + ", descr=" + descr + ", dt_cad=" + dt_cad + ", us_cad=" + us_cad + ", vl_orcado=" + vl_orcado + ", dt_prev_ini=" + dt_prev_ini + ", dt_prev_fim=" + dt_prev_fim + ", dt_alt=" + dt_alt + ", us_alt=" + us_alt + ", obs=" + obs + '}';
    }
 
}
