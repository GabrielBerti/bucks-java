/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.GrupoTitulo;
import br.com.bucks.model.Orcamento;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class OrcamentoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static OrcamentoDAO instance;
    protected EntityManager entityManager;

    public static OrcamentoDAO getInstance() {
        if (instance == null) {
            instance = new OrcamentoDAO();
        }

        return instance;
    }

    public OrcamentoDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("persistence");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public void inserirOrcamento(Orcamento orcamento) {

        try {
            System.out.println(" <<<OrcamentoDAO inserirOrcamento orcamento:" + orcamento.toString() + ">>>");
            entityManager.getTransaction().begin();
            entityManager.merge(orcamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Orcamento porId(Integer id) {
        return entityManager.find(Orcamento.class, id);
    }

    public void excluirOrcamento(Integer id) {
        try {
            entityManager.getTransaction().begin();
            Orcamento orcamento;
            orcamento = porId(id);
            entityManager.remove(orcamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Orcamento> todas(
            Integer pTituloGrupoId,
            Integer pAno,
            Integer pMes,
            String pPago,
            String pTipo) {
        String cmd;
        cmd = "select o from Orcamento o "
                + " where o.ano = :filtroAno ";

        if (pMes != 0) {
            cmd = cmd + "   and o.mes = " + pMes;
        } else {
            // tratar o orcamento que lancei mes 0 sendo o total do ano
            // a ideia é retirar essa estrutura
            cmd = cmd + "   and o.mes > 0 ";
        }
        
        if (pMes != 0) {
            cmd = cmd + "   and o.mes = " + pMes;
        }        
        
        if (pTipo != null){
            cmd = cmd + "   and o.grupoTitulo.cdTipo = '" + pTipo + "'";
        }
        
        if (pTituloGrupoId != null && pTituloGrupoId > 0) {
            cmd = cmd + " and o.grupoTitulo.id = " + pTituloGrupoId;
        }

//        if (!pPago.equals("ALL")) {
//            if (pPago.equals("S")) {
//                cmd = cmd + " and t.dtPgto is not null ";
//            } else {
//                cmd = cmd + " and t.dtPgto is null ";
//            }
//        }

        TypedQuery<Orcamento> query = entityManager.createQuery(
                cmd,
                Orcamento.class
        );

        query.setParameter("filtroAno", pAno);
//        query.setParameter("filtroMes", pMes);

        return query.getResultList();
    }

//    public List<Orcamento> consultarFiltrando(Integer pAno, Integer pMes) throws ParseException {
//
//        String sql;
//        sql = "select t from Orcamento t where 1=1 ";
//
//        if (pAno > 2005) {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            String dateInStringInicio = "01-" + String.format("%015d", pMes) + "-" + pAno;
//            String dateInStringFinal = "30-" + String.format("%015d", pMes) + "-" + pAno;
//
//            Date dateInicio = formatter.parse(dateInStringInicio);
//            Date dateFinal = formatter.parse(dateInStringFinal);
//            System.out.println(dateInicio);
//            System.out.println(formatter.format(dateInicio));
//            System.out.println(dateFinal);
//            System.out.println(formatter.format(dateFinal));
//
//            sql = sql + " and t.dtVecto >= " + dateInicio + " and t.dtVecto <= " + dateFinal;
//        }
//
//        TypedQuery<Orcamento> query = entityManager.createQuery(
//                sql,
//                Orcamento.class
//        );
//
//        return query.getResultList();
//
//    }

    public List<Orcamento> todasContas() {
        TypedQuery<Orcamento> query = entityManager.createQuery(
                "select c from Orcamento c",
                Orcamento.class
        );

        return query.getResultList();
    }

    public List<GrupoTitulo> todosGruposTitulos() {
        TypedQuery<GrupoTitulo> query = entityManager.createQuery(
                "select g from GrupoTitulo g",
                GrupoTitulo.class
        );

        return query.getResultList();
    }

}
