/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.TituloExtrato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author La Laina
 */
public class TituloExtratoDAO {
    
    private static TituloExtratoDAO instance;
    protected EntityManager entityManager;
    
    public static TituloExtratoDAO getInstance() {
        if (instance == null) {
            instance = new TituloExtratoDAO();
        }

        return instance;
    }
    
    public TituloExtratoDAO() {
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
    
    public void inserir (TituloExtrato tituloExtrato){
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tituloExtrato);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    public TituloExtrato porId(Integer num){
        return entityManager.find(TituloExtrato.class, num);
    }
    
//    public void excluir(Integer num){
//        try{
//            entityManager.getTransaction().begin();
//            TituloExtrato tituloExtrato;
//            tituloExtrato = porId(num);
//            entityManager.remove(tituloExtrato);
//            entityManager.getTransaction().commit();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            entityManager.getTransaction().rollback();
//        }
//    }
    
    public List<TituloExtrato> todas (){
        TypedQuery<TituloExtrato> query = entityManager.createQuery(
                "select te from TituloExtrato te"
              , TituloExtrato.class );
        
        return query.getResultList();
    }
    
}
