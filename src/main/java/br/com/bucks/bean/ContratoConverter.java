/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.DAO.ContratoDAO;
import br.com.bucks.model.Contrato;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author La Laina
 */

@FacesConverter(forClass=Contrato.class, value="contratoConverter")
public class ContratoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            Contrato contrato = new ContratoDAO().porId(id);
            return contrato;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Contrato contrato = (Contrato) value;
        if (contrato != null) {
            return String.valueOf(contrato.getNum());
        } else {
            return null;
        }

    }

}
