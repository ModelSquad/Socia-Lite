/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Visibility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visibility.findAll", query = "SELECT v FROM Visibility v")
    , @NamedQuery(name = "Visibility.findByIdVisibility", query = "SELECT v FROM Visibility v WHERE v.idVisibility = :idVisibility")
    , @NamedQuery(name = "Visibility.findByName", query = "SELECT v FROM Visibility v WHERE v.name = :name")})
public class Visibility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVisibility")
    private Integer idVisibility;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visibility")
    private List<Post> postList;

    public Visibility() {
    }

    public Visibility(Integer idVisibility) {
        this.idVisibility = idVisibility;
    }

    public Visibility(Integer idVisibility, String name) {
        this.idVisibility = idVisibility;
        this.name = name;
    }

    public Integer getIdVisibility() {
        return idVisibility;
    }

    public void setIdVisibility(Integer idVisibility) {
        this.idVisibility = idVisibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVisibility != null ? idVisibility.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visibility)) {
            return false;
        }
        Visibility other = (Visibility) object;
        if ((this.idVisibility == null && other.idVisibility != null) || (this.idVisibility != null && !this.idVisibility.equals(other.idVisibility))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.Visibility[ idVisibility=" + idVisibility + " ]";
    }
    
}
