package br.com.bucks.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("persistence");
          
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

        public void closeEmf(){
            emf.close();    
        }
//        
//	public void close(EntityManager em) {
//		em.close();
//	}
}
