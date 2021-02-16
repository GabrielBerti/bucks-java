/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.Contrato;
import br.com.bucks.model.Titulo;
import br.com.bucks.model.TituloAgrupamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author SC792765
 */
public class TituloAgrupamentoDAO implements Serializable {

    private LovDAO lovDAO = new LovDAO();
    private static TituloAgrupamentoDAO instance;

    protected EntityManager entityManager;

    public static TituloAgrupamentoDAO getInstance() {
        if (instance == null) {
            instance = new TituloAgrupamentoDAO();
        }

        return instance;
    }

    public TituloAgrupamentoDAO() {
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

    public List<Integer> ultIdTituloAgrupamento() {
        TypedQuery<Integer> query = entityManager.createQuery(
                "select max(t.id) + 1 from TituloAgrupamento t",
                Integer.class
        );

        return query.getResultList();
    }

    public void inserir(TituloAgrupamento tituloAgrupamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tituloAgrupamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void excluir(Integer p_num) {
        try {
            TituloAgrupamento tituloAgrupamento = porId(p_num);
            entityManager.getTransaction().begin();
            entityManager.remove(tituloAgrupamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<TituloAgrupamento> todos() {
        TypedQuery<TituloAgrupamento> query = entityManager.createQuery(
                "select ta from TituloAgrupamento ta",
                TituloAgrupamento.class);

        return query.getResultList();
    }

    public List<Titulo> todosTitulos() {
        TypedQuery<Titulo> query = entityManager.createQuery(
                "select t from Titulo t",
                Titulo.class
        );

        return query.getResultList();
    }

    public TituloAgrupamento porId(Integer num) {
        return entityManager.find(TituloAgrupamento.class, num);
    }

    public String getCdTipoTitAgrupDescr(Integer pCd) {
        return lovDAO.getContaCdTipoTitAgrupDescr(pCd);
    }

}
