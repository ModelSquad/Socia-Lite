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
 * @author Sevi
 */
@Entity
@Table(name = "Group1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group1.findAll", query = "SELECT g FROM Group1 g")
    , @NamedQuery(name = "Group1.findByIdGroup", query = "SELECT g FROM Group1 g WHERE g.idGroup = :idGroup")
    , @NamedQuery(name = "Group1.findByName", query = "SELECT g FROM Group1 g WHERE g.name = :name")
    , @NamedQuery(name = "Group1.findByDescription", query = "SELECT g FROM Group1 g WHERE g.description = :description")
    , @NamedQuery(name = "Group1.findByProfilePic", query = "SELECT g FROM Group1 g WHERE g.profilePic = :profilePic")})
public class Group1 implements Serializable {

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
    @JoinTable(name = "UserGroup", joinColumns = {
        @JoinColumn(name = "group", referencedColumnName = "idGroup")
        , @JoinColumn(name = "group", referencedColumnName = "idGroup")
        , @JoinColumn(name = "group", referencedColumnName = "idGroup")}, inverseJoinColumns = {
        @JoinColumn(name = "user", referencedColumnName = "idUser")
        , @JoinColumn(name = "user", referencedColumnName = "idUser")
        , @JoinColumn(name = "user", referencedColumnName = "idUser")})
    @ManyToMany
    private List<User> userList;
    @JoinColumn(name = "admin", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User admin;

    public Group1() {
    }

    public Group1(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Group1(Integer idGroup, String name) {
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
        if (!(object instanceof Group1)) {
            return false;
        }
        Group1 other = (Group1) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.Group1[ idGroup=" + idGroup + " ]";
    }
    
}
