/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

/**
 *
 * @author La Laina
 */
public enum ContratoTipoEnum {

        Compra(1),
	Casamento(2),
	niveranos(3),
	Ballet(4),
	Batizado(5),
	Acompanhamento(6),
	Ensaio(7),
	EnsaioInfantil(8),
	Formatura(9),
	Gestante(10),
	Newborn(11),
	NiverInfantil(12),
	NiverAdulto(13),
	Parto(14),
	Publicidade(15),
	Album(16),
	Freela(17),
	Chá(18),
	Produtos(19),
	Comemorativa(20),
        aux21(21),
        aus22(22);   

    private Integer cd;
    private String descr;

    ContratoTipoEnum(Integer cd) {
        this.cd = cd;
    }

    public Integer getCd() {
        return cd;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
