/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import socialite.entity.PasswordReset;

/**
 *
 * @author jaysus
 */
@Stateless
public class PasswordResetFacade extends AbstractFacade<PasswordReset> {

    @PersistenceContext(unitName = "Socia-Lite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasswordResetFacade() {
        super(PasswordReset.class);
    }
    
}
