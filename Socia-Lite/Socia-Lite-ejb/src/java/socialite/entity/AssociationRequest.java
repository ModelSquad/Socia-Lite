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
 * @author xfja
 */
@Entity
@Table(name = "AssociationRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssociationRequest.findAll", query = "SELECT a FROM AssociationRequest a")
    , @NamedQuery(name = "AssociationRequest.findByDateTime", query = "SELECT a FROM AssociationRequest a WHERE a.dateTime = :dateTime")
    , @NamedQuery(name = "AssociationRequest.findByText", query = "SELECT a FROM AssociationRequest a WHERE a.text = :text")
    , @NamedQuery(name = "AssociationRequest.findByAssociationRequestId", query = "SELECT a FROM AssociationRequest a WHERE a.associationRequestId = :associationRequestId")})
public class AssociationRequest implements Serializable {

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
    @Column(name = "associationRequestId")
    private Integer associationRequestId;
    @JoinColumn(name = "association_receiver", referencedColumnName = "idAssociation")
    @ManyToOne(optional = false)
    private Association associationReceiver;
    @JoinColumn(name = "user_sender", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User userSender;

    public AssociationRequest() {
    }

    public AssociationRequest(Integer associationRequestId) {
        this.associationRequestId = associationRequestId;
    }

    public AssociationRequest(Integer associationRequestId, Date dateTime) {
        this.associationRequestId = associationRequestId;
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

    public Integer getAssociationRequestId() {
        return associationRequestId;
    }

    public void setAssociationRequestId(Integer associationRequestId) {
        this.associationRequestId = associationRequestId;
    }

    public Association getAssociationReceiver() {
        return associationReceiver;
    }

    public void setAssociationReceiver(Association associationReceiver) {
        this.associationReceiver = associationReceiver;
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
        hash += (associationRequestId != null ? associationRequestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssociationRequest)) {
            return false;
        }
        AssociationRequest other = (AssociationRequest) object;
        if ((this.associationRequestId == null && other.associationRequestId != null) || (this.associationRequestId != null && !this.associationRequestId.equals(other.associationRequestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.AssociationRequest[ associationRequestId=" + associationRequestId + " ]";
    }
    
}
