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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author La Laina
 */
@Entity
@Table(name = "titulo_extrato")
public class TituloExtrato implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_titulo_extrato", sequenceName = "seq_titulo_extrato")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_titulo_extrato")
    private Integer id;

    @JoinColumn(name = "fk_titulo_id", nullable = true, referencedColumnName = "num")
    @Column(name = "fk_titulo_id")
    private Integer fkTituloNum;

    @Column(name = "vl", nullable = false)
    private Double vl;

    @Column(name = "vl_saldo_ant", nullable = false)
    private Double vlSaldoAnt;

    @Column(name = "vl_saldo_atu", nullable = false)
    private Double vlSaldoAtu;

    @Column(name = "us_cad", nullable = false, length = 30)
    private String usCad;

    @Column(name = "dt_cad", nullable = false)
    private Date dtCad;

    @Column(name = "us_alt", length = 30)
    private String usAlt;

    @Column(name = "dt_alt")
    private Date dtAlt;

    ////////////////////////////////////////////////////////////////////////////
    public Integer getNum() {
        return id;
    }

    public void setNum(Integer id) {
        this.id = id;
    }

    public Integer getFkTituloNum() {
        return fkTituloNum;
    }

    public void setFkTituloNum(Integer fkTituloNum) {
        this.fkTituloNum = fkTituloNum;
    }

    public Double getVl() {
        return vl;
    }

    public void setVl(Double vl) {
        this.vl = vl;
    }

    public Double getVlSaldoAnt() {
        return vlSaldoAnt;
    }

    public void setVlSaldoAnt(Double vlSaldoAnt) {
        this.vlSaldoAnt = vlSaldoAnt;
    }

    public Double getVlSaldoAtu() {
        return vlSaldoAtu;
    }

    public void setVlSaldoAtu(Double vlSaldoAtu) {
        this.vlSaldoAtu = vlSaldoAtu;
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

}
