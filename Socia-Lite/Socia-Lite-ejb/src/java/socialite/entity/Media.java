/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xfja
 */
@Entity
@Table(name = "Media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m")
    , @NamedQuery(name = "Media.findByIdMedia", query = "SELECT m FROM Media m WHERE m.idMedia = :idMedia")
    , @NamedQuery(name = "Media.findByMediaUrl", query = "SELECT m FROM Media m WHERE m.mediaUrl = :mediaUrl")})
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedia")
    private Integer idMedia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "mediaUrl")
    private String mediaUrl;
    @JoinColumn(name = "post", referencedColumnName = "idPost")
    @ManyToOne(optional = false)
    private Post post;

    public Media() {
    }

    public Media(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public Media(Integer idMedia, String mediaUrl) {
        this.idMedia = idMedia;
        this.mediaUrl = mediaUrl;
    }

    public Integer getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedia != null ? idMedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.idMedia == null && other.idMedia != null) || (this.idMedia != null && !this.idMedia.equals(other.idMedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.Media[ idMedia=" + idMedia + " ]";
    }
    
}
