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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author SC792765
 */
@Entity
@Table(name = "titulo_agrupamento")
public class TituloAgrupamento implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Column(name = "id", nullable = false)
    private Integer id;
    
    
    @ManyToOne
    @JoinColumn(name = "fk_titulo_id", nullable = false)
    private Titulo titulo;

    @Column(name = "cd_tipo", nullable = false)
    private Integer cdTipo;

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;
    
    @Id
    @Column(name = "dt_alt")
    private Date dtAlt;

    @Column(name = "us_alt", length = 30)
    private String usAlt;

    public void limpaNum() {
        this.setId(null);
    }

    public void limpaCampos() {
        this.setId(null);
        this.setTitulo(null);
        this.setCdTipo(null);
        this.setUsCad(null);
        this.setUsAlt(null);
        this.setDtCad(null);
        this.setDtAlt(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Integer getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(Integer cdTipo) {
        this.cdTipo = cdTipo;
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

}
