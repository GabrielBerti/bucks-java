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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author La Laina
 */
@Entity
@Table(name = "orcamento")
public class Orcamento implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_orcamento", sequenceName = "seq_orcamento")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_orcamento")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_titulo_grupo_id", nullable = false)
    private GrupoTitulo grupoTitulo;

    @Column(name = "ano", nullable = false)
    private Integer ano;
    
    @Column(name = "mes", nullable = false)
    private Integer mes;
    
    @Column(name = "vl", nullable = false)
    private Double vl;
    
    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;    

    @Column(name = "us_alt", length = 30)
    private String usAlt;

    @Column(name = "dt_alt")
    private Date dtAlt;

    @Column(name = "obs", length = 2000)
    private String obs;

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////getters and setters////////////////////////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GrupoTitulo getGrupoTitulo() {
        return grupoTitulo;
    }

    public void setGrupoTitulo(GrupoTitulo grupoTitulo) {
        this.grupoTitulo = grupoTitulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getVl() {
        return vl;
    }

    public void setVl(Double vl) {
        this.vl = vl;
    }

    public String getUsCad() {
        return usCad;
    }

    public void setUsCad(String usCad) {
        this.usCad = usCad;
    }

    public Date getDtCad() {
        return dtCad;
    }

    public void setDtCad(Date dtCad) {
        this.dtCad = dtCad;
    }

    public String getUsAlt() {
        return usAlt;
    }

    public void setUsAlt(String usAlt) {
        this.usAlt = usAlt;
    }

    public Date getDtAlt() {
        return dtAlt;
    }

    public void setDtAlt(Date dtAlt) {
        this.dtAlt = dtAlt;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    

    @Override
    public String toString() {
        return "Titulo => {" + "id=" + id + ", ano=" + ano + " + mes=" + mes + ", vl=" + vl + '}';
    }

}
