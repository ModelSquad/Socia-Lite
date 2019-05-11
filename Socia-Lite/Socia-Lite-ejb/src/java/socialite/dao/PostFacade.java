/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public Vector<Post> findByUser(User user) {
        return (Vector<Post>) em.createNamedQuery("Post.findByUser")
                .setParameter("user", user).getResultList();
    }

    public void deletePost(int idPost) {
        Post post = getEntityManager().find(Post.class, idPost);
        getEntityManager().remove(post);
    }

    /*
    Find posts of the user and his friends ordered by date
    In a future version the number of post will be limited due to eficacy
     */
    public List<Post> findPostsByMultipleIds(List<Integer> ids, Integer idUser) {
        Query q;
        q = this.em.createQuery("select p from Post p "
                + "where (p.user IN :ids) and p.association IS NULL and ((not(p.user.idUser = :idUser) and (p.visibility.idVisibility = 1)) or (p.user.idUser = :idUser))"
                + "order by p.date DESC");
        q.setParameter("ids", ids);
        q.setParameter("idUser", idUser);
        return q.getResultList();
    }

    public List<Post> findPostsByGroup(String idGroup) {
        Query q;
        q = this.em.createQuery("select p from Post p where p.association.idAssociation = :id order by p.date DESC");
        q.setParameter("id", Integer.valueOf(idGroup));
        return q.getResultList();
    }
}
