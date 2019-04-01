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
 * @author Sevi
 */
@Embeddable
public class FriendshipRequestPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_sender")
    private int userSender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_receiver")
    private int userReceiver;

    public FriendshipRequestPK() {
    }

    public FriendshipRequestPK(int userSender, int userReceiver) {
        this.userSender = userSender;
        this.userReceiver = userReceiver;
    }

    public int getUserSender() {
        return userSender;
    }

    public void setUserSender(int userSender) {
        this.userSender = userSender;
    }

    public int getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(int userReceiver) {
        this.userReceiver = userReceiver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userSender;
        hash += (int) userReceiver;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendshipRequestPK)) {
            return false;
        }
        FriendshipRequestPK other = (FriendshipRequestPK) object;
        if (this.userSender != other.userSender) {
            return false;
        }
        if (this.userReceiver != other.userReceiver) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.FriendshipRequestPK[ userSender=" + userSender + ", userReceiver=" + userReceiver + " ]";
    }
    
}
