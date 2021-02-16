/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.DAO.GrupoTituloDAO;
import br.com.bucks.model.GrupoTitulo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author La Laina
 */

@FacesConverter(forClass=GrupoTitulo.class, value="grupoTituloConverter")
public class GrupoTituloConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.isEmpty()) {
            return null;
        } else {
            Integer id = Integer.parseInt(value);
            GrupoTitulo grupoTitulo = new GrupoTituloDAO().porId(id);
            System.out.println(grupoTitulo.getDescr());
            return grupoTitulo;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        GrupoTitulo grupoTitulo = (GrupoTitulo) value;
        if (grupoTitulo != null) {
            return String.valueOf(grupoTitulo.getNum());
        } else {
            return null;
        }

    }

}
