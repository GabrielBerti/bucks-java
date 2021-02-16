/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author La Laina
 */
@Entity
@Table(name = "contrato_pessoa")
public class ContratoPessoa implements Serializable {

    @EmbeddedId
    private ContratoPessoaId contratoPessoaId;

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////Getters and Setters/////////////////////////////////////
    public ContratoPessoaId getContratoPessoaId() {
        return contratoPessoaId;
    }

    public void setContratoPessoaId(ContratoPessoaId contratoPessoaId) {
        this.contratoPessoaId = contratoPessoaId;
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

}
