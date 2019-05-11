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
 * @author jaysus
 */
@Entity
@Table(name = "PasswordReset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswordReset.findAll", query = "SELECT p FROM PasswordReset p")
    , @NamedQuery(name = "PasswordReset.findByIdPasswordReset", query = "SELECT p FROM PasswordReset p WHERE p.idPasswordReset = :idPasswordReset")
    , @NamedQuery(name = "PasswordReset.findByUrl", query = "SELECT p FROM PasswordReset p WHERE p.url = :url")
    , @NamedQuery(name = "PasswordReset.findByExpiritionDate", query = "SELECT p FROM PasswordReset p WHERE p.expiritionDate = :expiritionDate")})
public class PasswordReset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPasswordReset")
    private Integer idPasswordReset;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiritionDate")
    @Temporal(TemporalType.DATE)
    private Date expiritionDate;
    @JoinColumn(name = "usuario", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User usuario;

    public PasswordReset() {
    }

    public PasswordReset(Integer idPasswordReset) {
        this.idPasswordReset = idPasswordReset;
    }

    public PasswordReset(Integer idPasswordReset, String url, Date expiritionDate) {
        this.idPasswordReset = idPasswordReset;
        this.url = url;
        this.expiritionDate = expiritionDate;
    }

    public Integer getIdPasswordReset() {
        return idPasswordReset;
    }

    public void setIdPasswordReset(Integer idPasswordReset) {
        this.idPasswordReset = idPasswordReset;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpiritionDate() {
        return expiritionDate;
    }

    public void setExpiritionDate(Date expiritionDate) {
        this.expiritionDate = expiritionDate;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPasswordReset != null ? idPasswordReset.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasswordReset)) {
            return false;
        }
        PasswordReset other = (PasswordReset) object;
        if ((this.idPasswordReset == null && other.idPasswordReset != null) || (this.idPasswordReset != null && !this.idPasswordReset.equals(other.idPasswordReset))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.PasswordReset[ idPasswordReset=" + idPasswordReset + " ]";
    }
    
}
