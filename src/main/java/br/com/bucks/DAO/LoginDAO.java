/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.Login;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class LoginDAO {

    public boolean existe(Login Login) {

        EntityManager em = new JPAUtil().getEntityManager();
        TypedQuery<Login> query = em
                .createQuery(
                        "select u from Login u where u.email = :pEmail and u.senha = :pSenha",
                        Login.class);

        query.setParameter("pEmail", Login.getEmail());
        query.setParameter("pSenha", Login.getSenha());

        try {
            Login resultado = query.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }

        em.close();

        return true;
    }

}
