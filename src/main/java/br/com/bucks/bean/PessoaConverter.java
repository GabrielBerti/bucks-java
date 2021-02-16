/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.DAO.PessoaDAO;
import br.com.bucks.model.Pessoa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author La Laina
 */

@FacesConverter(forClass=Pessoa.class, value="pessoaConverter")
public class PessoaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            Pessoa pessoa = new PessoaDAO().porId(id);
            System.out.println(pessoa.getNome());
            return pessoa;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Pessoa pessoa = (Pessoa) value;
        if (pessoa != null) {
            return String.valueOf(pessoa.getId());
        } else {
            return null;
        }

    }

}
