/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.ContratoPessoa;
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
public class ContratoPessoaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ContratoPessoaDAO instance;
    protected EntityManager entityManager;

    public static ContratoPessoaDAO getInstance() {
        if (instance == null) {
            instance = new ContratoPessoaDAO();
        }

        return instance;
    }

    public ContratoPessoaDAO() {
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

    public void inserir(ContratoPessoa contratoPessoa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(contratoPessoa);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<ContratoPessoa> todas() {
        TypedQuery<ContratoPessoa> query = entityManager.createQuery(
                "select cp from ContratoPessoa cp",
                ContratoPessoa.class);

        return query.getResultList();
    }
    
    public List<ContratoPessoa> porContrato(Integer pContratoNum) {
        TypedQuery<ContratoPessoa> query = entityManager.createQuery(
                "select cp from ContratoPessoa cp where cp.contratoPessoaId.contrato.id = "+pContratoNum,
                ContratoPessoa.class);

        return query.getResultList();
    }

    public ContratoPessoa porId(Integer contratoNum, Integer pessoaNum) {
        System.out.println("<<< alterar aqui... ContratoPessoa implementar contratoNum e pessoaNum");
        return entityManager.find(ContratoPessoa.class, contratoNum);
    }
}
