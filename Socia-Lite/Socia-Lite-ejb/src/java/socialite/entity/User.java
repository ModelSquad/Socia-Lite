/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xfja
=======
 * @author cherra
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname")
    , @NamedQuery(name = "User.findByNickname", query = "SELECT u FROM User u WHERE u.nickname = :nickname")
    , @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate")
    , @NamedQuery(name = "User.findByBirthPlace", query = "SELECT u FROM User u WHERE u.birthPlace = :birthPlace")
    , @NamedQuery(name = "User.findByJob", query = "SELECT u FROM User u WHERE u.job = :job")
    , @NamedQuery(name = "User.findByJobPlace", query = "SELECT u FROM User u WHERE u.jobPlace = :jobPlace")
    , @NamedQuery(name = "User.findByStudyPlace", query = "SELECT u FROM User u WHERE u.studyPlace = :studyPlace")
    , @NamedQuery(name = "User.findByWebsite", query = "SELECT u FROM User u WHERE u.website = :website")
    , @NamedQuery(name = "User.findByProfilePic", query = "SELECT u FROM User u WHERE u.profilePic = :profilePic")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Size(max = 45)
    @Column(name = "birthPlace")
    private String birthPlace;
    @Size(max = 100)
    @Column(name = "job")
    private String job;
    @Size(max = 45)
    @Column(name = "jobPlace")
    private String jobPlace;
    @Size(max = 45)
    @Column(name = "studyPlace")
    private String studyPlace;
    @Size(max = 100)
    @Column(name = "website")
    private String website;
    @Size(max = 100)
    @Column(name = "profilePic")
    private String profilePic;
    @JoinTable(name = "UserFriend", joinColumns = {
        @JoinColumn(name = "idFriend", referencedColumnName = "idUser")}, inverseJoinColumns = {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")})
    @ManyToMany
    private List<User> userList;

    @ManyToMany(mappedBy = "userList")
    private List<User> userList1;
    @ManyToMany(mappedBy = "userList")
    private List<Association> associationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    private List<Association> associationList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReceiver")
    private List<FriendshipRequest> friendshipRequestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userSender")
    private List<FriendshipRequest> friendshipRequestList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> postList;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String email, String password, String name, String surname, String nickname, Date birthDate) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.birthDate = birthDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobPlace() {
        return jobPlace;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public void setStudyPlace(String studyPlace) {
        this.studyPlace = studyPlace;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    @XmlTransient
    public List<User> getUserList1() {
        return userList1;
    }

    public void setUserList1(List<User> userList1) {
        this.userList1 = userList1;
    }

    @XmlTransient
    public List<Association> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(List<Association> associationList) {
        this.associationList = associationList;
    }

    @XmlTransient
    public List<Association> getAssociationList1() {
        return associationList1;
    }

    public void setAssociationList1(List<Association> associationList1) {
        this.associationList1 = associationList1;
    }

    @XmlTransient
    public List<FriendshipRequest> getFriendshipRequestList() {
        return friendshipRequestList;
    }

    public void setFriendshipRequestList(List<FriendshipRequest> friendshipRequestList) {
        this.friendshipRequestList = friendshipRequestList;
    }

    @XmlTransient
    public List<FriendshipRequest> getFriendshipRequestList1() {
        return friendshipRequestList1;
    }

    public void setFriendshipRequestList1(List<FriendshipRequest> friendshipRequestList1) {
        this.friendshipRequestList1 = friendshipRequestList1;
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
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialite.entity.User[ idUser=" + idUser + " ]";
    }
    
}
