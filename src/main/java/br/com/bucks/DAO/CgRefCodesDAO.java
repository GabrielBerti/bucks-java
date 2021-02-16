/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.CgRefCodes;
import br.com.bucks.model.Conta;
import java.io.Serializable;
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
public class CgRefCodesDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static CgRefCodesDAO instance;
    protected EntityManager entityManager;

    public static CgRefCodesDAO getInstance() {
        if (instance == null) {
            instance = new CgRefCodesDAO();
        }

        return instance;
    }

    public CgRefCodesDAO() {
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

    public void inserirCgRefCodes(CgRefCodes cgRefCodes) {
        try {
            entityManager.getTransaction().begin();                        
            entityManager.merge(cgRefCodes);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    public void excluirCgRefCodes(Integer num) {
        try {
            entityManager.getTransaction().begin();
            CgRefCodes cgRefCodes;
            cgRefCodes = porId(num);
            entityManager.remove(cgRefCodes);
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
    public List<CgRefCodes> todasCgRefCodes() {
        TypedQuery<CgRefCodes> query = entityManager.createQuery(
                "select cg from CgRefCodes cg",
                CgRefCodes.class);

        return query.getResultList();
    }

    public CgRefCodes porId(Integer num) {
        return entityManager.find(CgRefCodes.class, num);
    }    
    
}
