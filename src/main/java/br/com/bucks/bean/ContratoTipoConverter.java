/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.bean;

import br.com.bucks.model.Contrato;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author La Laina
 */

//@FacesConverter(forClass=ContratoTipoEnum.class, value="contratoTipoConverter")
//public class ContratoTipoConverter implements Converter {

//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component,
//            String value) {
//
//        if (value == null || value.isEmpty()) {
//            return null;
//        } else {
//            Integer id = Integer.parseInt(value);
//            ContratoTipoEnum contratoTipoEnum = new ContratoTipoEnum(id).;
//            Contrato contrato = new Contrato();
//            contrato.getContratoTipo().setDescr(value);
//            System.out.println(contratoTipoEnum.getDescr());

//            ContratoTipoEnum contratoTipoEnum = new 
//            return contratoTipoEnum;
//            
//            Integer id = Integer.parseInt(value);
//            Conta conta = new ContaDAO().porId(id);
//            System.out.println(conta.getDescr());
//            return conta;
            
//        }
//
//    }

//    @Override
//    public String getAsString(FacesContext context, UIComponent component,
//            Object value) {
//        ContratoTipoEnum conta = (ContratoTipoEnum) value;
//        if (conta != null) {
//            return String.valueOf(conta.getNum());
//        } else {
//            return null;
//        }
//
//    }

//}
