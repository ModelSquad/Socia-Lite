/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sevi
 */
@Entity
@Table(name = "FriendshipRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FriendshipRequest.findAll", query = "SELECT f FROM FriendshipRequest f")
    , @NamedQuery(name = "FriendshipRequest.findByUserSender", query = "SELECT f FROM FriendshipRequest f WHERE f.friendshipRequestPK.userSender = :userSender")
    , @NamedQuery(name = "FriendshipRequest.findByUserReceiver", query = "SELECT f FROM FriendshipRequest f WHERE f.friendshipRequestPK.userReceiver = :userReceiver")
    , @NamedQuery(name = "FriendshipRequest.findByDateTime", query = "SELECT f FROM FriendshipRequest f WHERE f.dateTime = :dateTime")
    , @NamedQuery(name = "FriendshipRequest.findByText", query = "SELECT f FROM FriendshipRequest f WHERE f.text = :text")})
public class FriendshipRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendshipRequestPK friendshipRequestPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Size(max = 200)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "user_receiver", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "user_sender", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public FriendshipRequest() {
    }

    public FriendshipRequest(FriendshipRequestPK friendshipRequestPK) {
        this.friendshipRequestPK = friendshipRequestPK;
    }

    public FriendshipRequest(FriendshipRequestPK friendshipRequestPK, Date dateTime) {
        this.friendshipRequestPK = friendshipRequestPK;
        this.dateTime = dateTime;
    }

    public FriendshipRequest(int userSender, int userReceiver) {
        this.friendshipRequestPK = new FriendshipRequestPK(userSender, userReceiver);
    }

    public FriendshipRequestPK getFriendshipRequestPK() {
        return friendshipRequestPK;
    }

    public void setFriendshipRequestPK(FriendshipRequestPK friendshipRequestPK) {
        this.friendshipRequestPK = friendshipRequestPK;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendshipRequestPK != null ? friendshipRequestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendshipRequest)) {
            return false;
        }
        FriendshipRequest other = (FriendshipRequest) object;
        if ((this.friendshipRequestPK == null && other.friendshipRequestPK != null) || (this.friendshipRequestPK != null && !this.friendshipRequestPK.equals(other.friendshipRequestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.FriendshipRequest[ friendshipRequestPK=" + friendshipRequestPK + " ]";
    }
    
}
