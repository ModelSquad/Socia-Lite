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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import socialite.entity.FriendshipRequest;
import socialite.entity.User;

/**
 *
 * @author Sevi
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
    
    public User findByNickname (String nickname){
        Query q;
        q = em.createNamedQuery("User.findByNickname");
        q.setParameter("nickname", nickname);
        User user;
        try{
            user = (User) q.getSingleResult();
        }catch(NoResultException e){
            user=null;
        }
        return user;
        
    }
    
//    public List<User> findByNameAndSurname(String[] user){
//        Query q;
//        q = this.em.createQuery("select u from User u where u.name like :name and u.surname like :surname");
//        if(user.length==4){ //If user has a middle name
//            q.setParameter("name", user[0]+" "+user[1]);
//            q.setParameter("surname", user[2]+" "+user[3]);
//        }else if(user.length==3){
//            q.setParameter("name", user[0]);
//            q.setParameter("surname",user[1]+" "+user[2]);
//        }else if(user.length==2){
//            q.setParameter("name", user[0]);
//            q.setParameter("surname",user[1]);
//        }else if (user.length==1){
//            q = em.createNamedQuery("User.findByName");
//            q.setParameter("name", user[0]);
//        }
//        return q.getResultList();    
//    }
    
    
}
