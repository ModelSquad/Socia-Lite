/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import socialite.entity.User;

/**
 *
 * @author cherra
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Socia-Lite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByEmail (String email) {
        Iterator it = em.createNamedQuery("User.findByEmail")
        .setParameter("email", email).getResultList().iterator();
        return (it.hasNext()) ? (User)it.next() : null;
    }  
    public User findByIdUser (Integer id) {
        Iterator it = em.createNamedQuery("User.findByIdUser")
        .setParameter("idUser", id).getResultList().iterator();
        return (it.hasNext()) ? (User)it.next() : null;
    } 
}
