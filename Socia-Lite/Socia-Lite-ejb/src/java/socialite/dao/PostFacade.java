/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import socialite.entity.Post;
import socialite.entity.User;

/**
 *
 * @author Sevi
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {

    @PersistenceContext(unitName = "Socia-Lite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public Vector<Post> findByUser (User user) {
        return (Vector<Post>) em.createNamedQuery("Post.findByUser")
        .setParameter("user", user).getResultList();
    }
}
