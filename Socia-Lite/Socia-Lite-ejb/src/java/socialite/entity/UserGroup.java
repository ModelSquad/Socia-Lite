/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xfja
 */
@Entity
@Table(name = "UserGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u")
    , @NamedQuery(name = "UserGroup.findByUser", query = "SELECT u FROM UserGroup u WHERE u.userGroupPK.user = :user")
    , @NamedQuery(name = "UserGroup.findByGroup", query = "SELECT u FROM UserGroup u WHERE u.userGroupPK.group = :group")})
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserGroupPK userGroupPK;

    public UserGroup() {
    }

    public UserGroup(UserGroupPK userGroupPK) {
        this.userGroupPK = userGroupPK;
    }

    public UserGroup(int user, int group) {
        this.userGroupPK = new UserGroupPK(user, group);
    }

    public UserGroupPK getUserGroupPK() {
        return userGroupPK;
    }

    public void setUserGroupPK(UserGroupPK userGroupPK) {
        this.userGroupPK = userGroupPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGroupPK != null ? userGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.userGroupPK == null && other.userGroupPK != null) || (this.userGroupPK != null && !this.userGroupPK.equals(other.userGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.UserGroup[ userGroupPK=" + userGroupPK + " ]";
    }
    
}
