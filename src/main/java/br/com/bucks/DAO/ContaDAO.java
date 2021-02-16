/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

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
public class ContaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ContaDAO instance;
    protected EntityManager entityManager;
    
    private LovDAO lovDAO = new LovDAO();

    public static ContaDAO getInstance() {
        if (instance == null) {
            instance = new ContaDAO();
        }

        return instance;
    }

    public ContaDAO() {
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

    public void inserirConta(Conta conta) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(conta);
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
    public List<Conta> todas() {
        TypedQuery<Conta> query = entityManager.createQuery(
                "select c from Conta c",
                Conta.class);

        return query.getResultList();
    }

    public Conta porId(Integer num) {
        return entityManager.find(Conta.class, num);
    }
    
    public String getCdTipoDescr(Integer pCd){
        return lovDAO.getContaCdTipoDescr(pCd);
    }
    
    public String getCdSitDescr(Integer pCd){
        return lovDAO.getCdSitDescr(pCd);
    }
    
    public List<LovDAO> carregarLov(String pRvDomain, boolean pPreencherAll){
        return lovDAO.carregarLov(pRvDomain, pPreencherAll);
    }
    
}
