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
@Table(name = "titulo")
public class Titulo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_titulo", sequenceName = "seq_titulo")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_titulo")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_conta_id", nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "fk_titulo_grupo_id", nullable = false)
    private GrupoTitulo grupoTitulo;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_pagamento_id", nullable = false)
    private TipoPagamento tipoPagamento;

    @ManyToOne
    @JoinColumn(name = "fk_contrpes_contrato_id", nullable = true)
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "fk_contrpes_pessoa_id", nullable = true)
    private Pessoa pessoa;

    @Column(name = "descr", nullable = false, length = 200)
    private String descr;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;

    @Column(name = "dt_vcto", nullable = false)
    private Date dtVcto;

    @Column(name = "dt_pgto")
    private Date dtPgto;

    @Column(name = "vl", nullable = false)
    private Double vl;

    @Column(name = "parcela", nullable = false)
    private Integer parcela;

    @Column(name = "parcela_total", nullable = false)
    private Integer parcelaTotal;

    @Column(name = "us_alt", length = 30)
    private String usAlt;

    @Column(name = "dt_alt")
    private Date dtAlt;

    @Column(name = "obs", length = 2000)
    private String obs;

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////getters and setters////////////////////////////////////
    public Integer getNum() {
        return id;
    }

    public void setNum(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public GrupoTitulo getGrupoTitulo() {
        return grupoTitulo;
    }

    public void setGrupoTitulo(GrupoTitulo grupoTitulo) {
        this.grupoTitulo = grupoTitulo;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    public Date getDtVcto() {
        return dtVcto;
    }

    public void setDtVcto(Date dtVcto) {
        this.dtVcto = dtVcto;
    }

    public Date getDtPgto() {
        return dtPgto;
    }

    public void setDtPgto(Date dtPgto) {
        this.dtPgto = dtPgto;
    }

    public Double getVl() {
        return vl;
    }

    public void setVl(Double vl) {
        this.vl = vl;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Integer getParcelaTotal() {
        return parcelaTotal;
    }

    public void setParcelaTotal(Integer parcelaTotal) {
        this.parcelaTotal = parcelaTotal;
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
        return "Titulo => {" + "id=" + id + ", descr=" + descr + ", vl=" + vl + '}';
    }

}
