/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.Contrato;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author La Laina
 */
public class ContratoDAO implements Serializable {

    private static ContratoDAO instance;

    protected EntityManager entityManager;

    public static ContratoDAO getInstance() {
        if (instance == null) {
            instance = new ContratoDAO();
        }

        return instance;
    }

    public ContratoDAO() {
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

    public void inserir(Contrato contrato) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(contrato);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void excluir(Integer p_num) {
        try {
            Contrato contrato = porId(p_num);
            entityManager.getTransaction().begin();
            entityManager.remove(contrato);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Contrato> todas() {
        TypedQuery<Contrato> query = entityManager.createQuery(
                "select c from Contrato c",
                Contrato.class);

        return query.getResultList();
    }

    public Contrato porId(Integer num) {
        return entityManager.find(Contrato.class, num);
    }

}
