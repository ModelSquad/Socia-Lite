/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import socialite.entity.FriendshipRequest;
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
    
    public List<User> findNotFriends(User user){
        List<User> notFriends = null;
        Query q = this.em.createQuery("select u from User u where u.idUser not in :friends and u.idUser != :user");
        List<Integer> idFriends =new ArrayList<Integer>();
        for(User friend : user.getUserList()){
            idFriends.add(friend.getIdUser());
        }
        for(FriendshipRequest fr : user.getFriendshipRequestList()){
            idFriends.add(fr.getUserSender().getIdUser());
        }
        for(FriendshipRequest fr : user.getFriendshipRequestList1()){
            idFriends.add(fr.getUserReceiver().getIdUser());
        }
        q.setParameter("friends", idFriends);
        q.setParameter("user", user.getIdUser());
        return q.getResultList();
    }
}
