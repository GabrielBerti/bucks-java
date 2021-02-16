/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author La Laina
 */
public class LovDAO implements Serializable {

    private String cd;
    private String descr;

    private List<LovDAO> lovDAO = new ArrayList<LovDAO>();

    ////////////////////////////////////////////////////////////////////////////
    /////////////////// Get das Descricoes /////////////////////////////////////
    // Situacao, pode ser usado em qlq lugar
    public String getCdSitDescr(Integer pCd) {
        switch (pCd) {
            case 1:
                return "Ativo";
            case 2:
                return "Invativo";
            default:
                return "Error on define situação. getCdSitDescr(" + pCd + ")!";
        }
    }

    // S = Sim
    // N = Não
    public String getDescrLovSimNao(String pCd) {
        if ((pCd).equals("S")) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    // P = Pagar
    // R = Receber
    public String getDescrLovPagarReceber(String pCd) {
        if ((pCd).equals("P")) {
            return "Pagar";
        } else {
            return "Receber";
        }
    }

    // ContaCorrente(1), ContaPoupança(2), Investimento(3), Outros(4);
    public String getContaCdTipoDescr(Integer pCd) {
        switch (pCd) {
            case 1:
                return "Conta Corrente";
            case 2:
                return "Conta Poupança";
            case 3:
                return "Investimento";
            case 4:
                return "Outros";
            default:
                return "Error on define tipo de conta. getContaCdTipoDescr(" + pCd + ")!";
        }

    }
    
    public String getContaCdTipoTitAgrupDescr(Integer pCd) {
        switch (pCd) {
            case 1:
                return "Agrupado";
            case 2:
                return "Parcelado";
            default:
                return "Tipo de Agrupamento não definido(" + pCd + ")!";
        }

    }

    public List<LovDAO> carregarLov(String pRvDomain, boolean pPreencherAll) {
        List<LovDAO> lov = new ArrayList<LovDAO>();
        LovDAO newLov = new LovDAO();;

        if (pPreencherAll){
            newLov.setCd("ALL");
            newLov.setDescr("All");
            lov.add(newLov);
            
            newLov = null;
            newLov = new LovDAO();            
        }
            
        
        if (pRvDomain.equals("SIM NAO")) {
            newLov.setCd("S");
            newLov.setDescr("Sim");
            lov.add(newLov);

            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("N");
            newLov.setDescr("Não");
            lov.add(newLov);
        } else if (pRvDomain.equals("ALL SIM NAO")) {
            newLov.setCd("ALL");
            newLov.setDescr("All");
            lov.add(newLov);
            
            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("S");
            newLov.setDescr("Sim");
            lov.add(newLov);

            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("N");
            newLov.setDescr("Não");
            lov.add(newLov);
            
        } else if (pRvDomain.equals("PAGAR RECEBER")) {
            newLov.setCd("P");
            newLov.setDescr("Pagar");
            lov.add(newLov);

            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("R");
            newLov.setDescr("Receber");
            lov.add(newLov);
            // pause...mudar para id
        } else if (pRvDomain.equalsIgnoreCase("CONTA.fk_conta_tipo_num")) {
            newLov.setCd("1");
            newLov.setDescr("Conta Corrente");
            lov.add(newLov);

            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("2");
            newLov.setDescr("Conta Poupança");
            lov.add(newLov);
            
            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("3");
            newLov.setDescr("Investimentos");
            lov.add(newLov);
            
            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("4");
            newLov.setDescr("Cartão de Crédito");
            lov.add(newLov);
            
            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("5");
            newLov.setDescr("Outros");
            lov.add(newLov);

        } else if (pRvDomain.equalsIgnoreCase("CONTA.fk_conta_tipo_num")) {
            newLov.setCd("1");
            newLov.setDescr("Conta Corrente");
            lov.add(newLov);

            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("2");
            newLov.setDescr("Conta Poupança");
            lov.add(newLov);

        } else if (pRvDomain.equalsIgnoreCase("CONTA.cd_sit")) {
            newLov.setCd("1");
            newLov.setDescr("Ativo");
            lov.add(newLov);

            newLov = null;
            newLov = new LovDAO();
            newLov.setCd("2");
            newLov.setDescr("Inativo");
            lov.add(newLov);
        } else {
            newLov.setCd("0");
            newLov.setDescr("*** Carregar Lista. carregarLov(" + pRvDomain + ")!!!");
            lov.add(newLov);
        }

        return lov;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
