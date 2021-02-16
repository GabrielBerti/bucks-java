/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.GrupoTitulo;
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
public class GrupoTituloDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static GrupoTituloDAO instance;
    protected EntityManager entityManager;

    private LovDAO lovDAO = new LovDAO();

    public static GrupoTituloDAO getInstance() {
        if (instance == null) {
            instance = new GrupoTituloDAO();
        }

        return instance;
    }

    public GrupoTituloDAO() {
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

    public void inserirGrupoTitulo(GrupoTitulo grupoTitulo) {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(grupoTitulo);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public GrupoTitulo porId(Integer num) {
        return entityManager.find(GrupoTitulo.class, num);
    }

    public void excluirGrupoTitulo(Integer num) {

        try {
            entityManager.getTransaction().begin();
            GrupoTitulo grupoTitulo;
            grupoTitulo = porId(num);
            entityManager.remove(grupoTitulo);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<GrupoTitulo> todas() {
        TypedQuery<GrupoTitulo> query = entityManager.createQuery("select gr from GrupoTitulo gr",
                GrupoTitulo.class);

        return query.getResultList();
    }

    // Feito para retornar a Descricao do cdContabiliza
    // pause...fazer uma classe para tratar todos esses
    public String cdContabilizaDescr(String pCdContabiliza) {
        return lovDAO.getDescrLovSimNao(pCdContabiliza);
    }

    public String cdTipoDescr(String pCdTipo) {
        return lovDAO.getDescrLovPagarReceber(pCdTipo);
    }

    public List<GrupoTitulo> todosGruposTitulos() {
        TypedQuery<GrupoTitulo> query = entityManager.createQuery(
                "select g from GrupoTitulo g",
                GrupoTitulo.class
        );

        return query.getResultList();
    }

}
