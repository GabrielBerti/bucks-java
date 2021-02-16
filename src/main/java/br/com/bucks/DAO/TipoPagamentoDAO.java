/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.TipoPagamento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class TipoPagamentoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static TipoPagamentoDAO instance;
    protected EntityManager entityManager;

    public static TipoPagamentoDAO getInstance() {
        if (instance == null) {
            instance = new TipoPagamentoDAO();
        }

        return instance;
    }

    public TipoPagamentoDAO() {
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

    public void inserirTipoPagamento(TipoPagamento tipoPagamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tipoPagamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public TipoPagamento porId(Integer num) {
        return entityManager.find(TipoPagamento.class, num);
    }

    public void excluirTipoPagamento(Integer num) {
        //projeto.setNum(15);
        try {
            System.out.println("num " + num);
            entityManager.getTransaction().begin();
            TipoPagamento tipo_pagamento;
            tipo_pagamento = porId(num);
            entityManager.remove(tipo_pagamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<TipoPagamento> todas() {
        TypedQuery<TipoPagamento> query = entityManager.createQuery("select tp from TipoPagamento tp",
                TipoPagamento.class);

        return query.getResultList();
    }

}
