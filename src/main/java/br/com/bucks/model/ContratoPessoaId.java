/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author La Laina
 */
@Embeddable
public class ContratoPessoaId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "fk_contrato_id", nullable = false)
    private Contrato contrato; // pause id

    @ManyToOne
    @JoinColumn(name = "fk_pessoa_id", nullable = false)
    private Pessoa pessoa; // pause id

    //getters e setters omitidos
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

}
