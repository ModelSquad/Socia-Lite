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
import socialite.entity.FriendshipRequest;

/**
 *
 * @author Sevi
 */
@Stateless
public class FriendshipRequestFacade extends AbstractFacade<FriendshipRequest> {

    @PersistenceContext(unitName = "Socia-Lite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FriendshipRequestFacade() {
        super(FriendshipRequest.class);
    }
    
    public FriendshipRequest findByFriendshipRequestId(Integer friendshipRequestId) {
        Iterator it = em.createNamedQuery("FriendshipRequest.findByFriendshipRequestId")
        .setParameter("friendshipRequestId", friendshipRequestId).getResultList().iterator();
        return (it.hasNext()) ? (FriendshipRequest)it.next() : null;
    }
    
}
