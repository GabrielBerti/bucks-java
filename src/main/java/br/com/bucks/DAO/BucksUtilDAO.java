/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.BucksUtil;
import br.com.bucks.model.GrupoTitulo;
import br.com.bucks.model.Orcamento;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author La Laina
 */
public class BucksUtilDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static BucksUtilDAO instance;
//    protected EntityManager entityManager;
    
    private List<BucksUtil> listaBucksUtil = new ArrayList();

    public static BucksUtilDAO getInstance() {
        if (instance == null) {
            instance = new BucksUtilDAO();
        }

        return instance;
    }

    public Integer retornarAnoAtual() {
        Date data = new Date();
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        return calendario.get(Calendar.YEAR);
    }

    public Integer retornarMesAtual() {
        Date data = new Date();
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        return calendario.get(Calendar.MONTH) + 1;
    }
    
    public void add(BucksUtil bucksUtil){
        listaBucksUtil.add(bucksUtil);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////////Getters and Setters////////////////////////////////////////
    public List<BucksUtil> getListaBucksUtil(Integer pAno, Integer pMes) {
        return listaBucksUtil;
    }

    public void setListaBucksUtil(List<BucksUtil> listaBucksUtil) {
        this.listaBucksUtil = listaBucksUtil;
    }
    
}
