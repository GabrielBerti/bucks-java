/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.DAO.ContaDAO;
import br.com.bucks.model.Conta;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sc484025
 */

@FacesConverter(forClass=Conta.class, value="contaConverter")
public class ContaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            Conta conta = new ContaDAO().porId(id);
            System.out.println(conta.getDescr());
            return conta;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Conta conta = (Conta) value;
        if (conta != null) {
            return String.valueOf(conta.getNum());
        } else {
            return null;
        }

    }

}
