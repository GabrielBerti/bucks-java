/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.Projeto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lucas
 */
public class ProjetoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ProjetoDAO instance;
    protected EntityManager entityManager;

    public static ProjetoDAO getInstance() {
        if (instance == null) {
            instance = new ProjetoDAO();
        }

        return instance;
    }

    public ProjetoDAO() {
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

    public void inserirProjeto(Projeto projeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(projeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Projeto porId(Integer num) {
        return entityManager.find(Projeto.class, num);
    }

    public void excluirProjeto(Integer num) {
        try {
            entityManager.getTransaction().begin();
            Projeto projeto;
            projeto = porId(num);
            entityManager.remove(projeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

//
//    public List<Projeto> pesquisar(String nome) {
//        String jpql = "from Projeto where descr like :descr";
//
//        TypedQuery<Projeto> query = manager
//                .createQuery(jpql, Projeto.class);
//
//        query.setParameter("descr", nome + "%");
//
//        return query.getResultList();
//    }
    public List<Projeto> todas() {
        TypedQuery<Projeto> query = entityManager.createQuery(
                "select p from Projeto p",
                Projeto.class);

        return query.getResultList();
    }

}
