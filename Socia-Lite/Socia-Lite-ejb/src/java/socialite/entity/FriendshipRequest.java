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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
    , @NamedQuery(name = "FriendshipRequest.findByIdFriendshipRequest", query = "SELECT f FROM FriendshipRequest f WHERE f.idFriendshipRequest = :idFriendshipRequest")
    , @NamedQuery(name = "FriendshipRequest.findByDate", query = "SELECT f FROM FriendshipRequest f WHERE f.date = :date")})
public class FriendshipRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFriendshipRequest")
    private Integer idFriendshipRequest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public FriendshipRequest() {
    }

    public FriendshipRequest(Integer idFriendshipRequest) {
        this.idFriendshipRequest = idFriendshipRequest;
    }

    public FriendshipRequest(Integer idFriendshipRequest, Date date) {
        this.idFriendshipRequest = idFriendshipRequest;
        this.date = date;
    }

    public Integer getIdFriendshipRequest() {
        return idFriendshipRequest;
    }

    public void setIdFriendshipRequest(Integer idFriendshipRequest) {
        this.idFriendshipRequest = idFriendshipRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFriendshipRequest != null ? idFriendshipRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendshipRequest)) {
            return false;
        }
        FriendshipRequest other = (FriendshipRequest) object;
        if ((this.idFriendshipRequest == null && other.idFriendshipRequest != null) || (this.idFriendshipRequest != null && !this.idFriendshipRequest.equals(other.idFriendshipRequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.FriendshipRequest[ idFriendshipRequest=" + idFriendshipRequest + " ]";
    }
    
}
