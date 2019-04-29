/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cherra
 */
@Entity
@Table(name = "Association")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Association.findAll", query = "SELECT a FROM Association a")
    , @NamedQuery(name = "Association.findByIdGroup", query = "SELECT a FROM Association a WHERE a.idGroup = :idGroup")
    , @NamedQuery(name = "Association.findByName", query = "SELECT a FROM Association a WHERE a.name = :name")
    , @NamedQuery(name = "Association.findByDescription", query = "SELECT a FROM Association a WHERE a.description = :description")
    , @NamedQuery(name = "Association.findByProfilePic", query = "SELECT a FROM Association a WHERE a.profilePic = :profilePic")})
public class Association implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGroup")
    private Integer idGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    @Size(max = 100)
    @Column(name = "profilePic")
    private String profilePic;
    @JoinTable(name = "UserAssociation", joinColumns = {
        @JoinColumn(name = "group", referencedColumnName = "idGroup")}, inverseJoinColumns = {
        @JoinColumn(name = "user", referencedColumnName = "idUser")})
    @ManyToMany
    private List<User> userList;
    @JoinColumn(name = "admin", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User admin;

    public Association() {
    }

    public Association(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Association(Integer idGroup, String name) {
        this.idGroup = idGroup;
        this.name = name;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Association)) {
            return false;
        }
        Association other = (Association) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.Association[ idGroup=" + idGroup + " ]";
    }
    
}
