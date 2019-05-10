/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import socialite.entity.Association;
import socialite.entity.AssociationRequest;
import socialite.entity.User;

/**
 *
 * @author cherra
 */
@Stateless
public class AssociationFacade extends AbstractFacade<Association> {

    @PersistenceContext(unitName = "Socia-Lite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssociationFacade() {
        super(Association.class);
    }

    public List<Association> findNotAssociation(User user) {
        List<Integer> associationsId = new ArrayList<Integer>();
        for(Association association : user.getAssociationList()){
            associationsId.add(association.getIdAssociation());
        }
        for(AssociationRequest associationRequest : user.getAssociationRequestList()){
            associationsId.add(associationRequest.getAssociationReceiver().getIdAssociation());
        }
        Query q = this.em.createQuery("select a from Association a where a.idAssociation not in :listaAsociaciones");
        q.setParameter("listaAsociaciones", associationsId);
        return q.getResultList();
    }
    
}
