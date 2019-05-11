/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public PasswordReset getByUrlId(String urlId) {
        Query q;
        q = this.em.createQuery("select pr from PasswordReset pr where pr.url = :urlId order by pr.expiritionDate desc");
        q.setParameter("urlId", urlId);
        List<PasswordReset> passwordReset = (List<PasswordReset>)q.getResultList();
        return (passwordReset.isEmpty()) ? null : passwordReset.get(0);
    }
    
}
