/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import socialite.entity.AssociationRequest;

/**
 *
 * @author cherra
 */
@Stateless
public class AssociationRequestFacade extends AbstractFacade<AssociationRequest> {

    @PersistenceContext(unitName = "Socia-Lite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssociationRequestFacade() {
        super(AssociationRequest.class);
    }
    
}
