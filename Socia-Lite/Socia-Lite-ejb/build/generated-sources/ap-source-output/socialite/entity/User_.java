package socialite.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import socialite.entity.FriendshipRequest;
import socialite.entity.Group1;
import socialite.entity.Post;
import socialite.entity.User;
import socialite.entity.UserGroup;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-21T13:48:38")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> website;
    public static volatile ListAttribute<User, User> userList1;
    public static volatile ListAttribute<User, Post> postList;
    public static volatile ListAttribute<User, Group1> group1List;
    public static volatile SingularAttribute<User, String> profilePic;
    public static volatile ListAttribute<User, FriendshipRequest> friendshipRequestList;
    public static volatile SingularAttribute<User, Date> birthDate;
    public static volatile SingularAttribute<User, String> jobPlace;
    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, String> birthPlace;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> studyPlace;
    public static volatile ListAttribute<User, User> userList;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> nickname;
    public static volatile ListAttribute<User, FriendshipRequest> friendshipRequestList1;
    public static volatile SingularAttribute<User, String> job;
    public static volatile SingularAttribute<User, UserGroup> userGroup;
    public static volatile SingularAttribute<User, String> email;

}