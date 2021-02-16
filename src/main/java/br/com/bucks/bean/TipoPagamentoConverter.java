/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.DAO.TipoPagamentoDAO;
import br.com.bucks.model.TipoPagamento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author La Laina
 */

@FacesConverter(forClass=TipoPagamento.class, value="tipoPagamentoConverter")
public class TipoPagamentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            TipoPagamento tipoPagamento = new TipoPagamentoDAO().porId(id);
            System.out.println(tipoPagamento.getDescr());
            return tipoPagamento;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        TipoPagamento tipoPagamento = (TipoPagamento) value;
        if (tipoPagamento != null) {
            return String.valueOf(tipoPagamento.getNum());
        } else {
            return null;
        }

    }

}
