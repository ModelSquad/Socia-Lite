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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author cherra
 */
@Entity
@Table(name = "FriendshipRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FriendshipRequest.findAll", query = "SELECT f FROM FriendshipRequest f")
    , @NamedQuery(name = "FriendshipRequest.findByDateTime", query = "SELECT f FROM FriendshipRequest f WHERE f.dateTime = :dateTime")
    , @NamedQuery(name = "FriendshipRequest.findByText", query = "SELECT f FROM FriendshipRequest f WHERE f.text = :text")
    , @NamedQuery(name = "FriendshipRequest.findByFriendshipRequestId", query = "SELECT f FROM FriendshipRequest f WHERE f.friendshipRequestId = :friendshipRequestId")})
public class FriendshipRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Size(max = 200)
    @Column(name = "text")
    private String text;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "friendshipRequestId")
    private Integer friendshipRequestId;
    @JoinColumn(name = "user_receiver", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User userReceiver;
    @JoinColumn(name = "user_sender", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User userSender;

    public FriendshipRequest() {
    }

    public FriendshipRequest(Integer friendshipRequestId) {
        this.friendshipRequestId = friendshipRequestId;
    }

    public FriendshipRequest(Integer friendshipRequestId, Date dateTime) {
        this.friendshipRequestId = friendshipRequestId;
        this.dateTime = dateTime;
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

    public Integer getFriendshipRequestId() {
        return friendshipRequestId;
    }

    public void setFriendshipRequestId(Integer friendshipRequestId) {
        this.friendshipRequestId = friendshipRequestId;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendshipRequestId != null ? friendshipRequestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendshipRequest)) {
            return false;
        }
        FriendshipRequest other = (FriendshipRequest) object;
        if ((this.friendshipRequestId == null && other.friendshipRequestId != null) || (this.friendshipRequestId != null && !this.friendshipRequestId.equals(other.friendshipRequestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.FriendshipRequest[ friendshipRequestId=" + friendshipRequestId + " ]";
    }
    
}
