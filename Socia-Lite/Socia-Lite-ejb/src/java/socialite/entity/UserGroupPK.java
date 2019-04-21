/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cherra
 */
@Embeddable
public class UserGroupPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private int user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "group")
    private int group;

    public UserGroupPK() {
    }

    public UserGroupPK(int user, int group) {
        this.user = user;
        this.group = group;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) user;
        hash += (int) group;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroupPK)) {
            return false;
        }
        UserGroupPK other = (UserGroupPK) object;
        if (this.user != other.user) {
            return false;
        }
        if (this.group != other.group) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.UserGroupPK[ user=" + user + ", group=" + group + " ]";
    }
    
}
