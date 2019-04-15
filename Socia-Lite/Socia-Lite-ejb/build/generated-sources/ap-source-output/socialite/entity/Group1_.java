package socialite.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import socialite.entity.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-15T17:48:48")
@StaticMetamodel(Group1.class)
public class Group1_ { 

    public static volatile ListAttribute<Group1, User> userList;
    public static volatile SingularAttribute<Group1, Integer> idGroup;
    public static volatile SingularAttribute<Group1, String> profilePic;
    public static volatile SingularAttribute<Group1, String> name;
    public static volatile SingularAttribute<Group1, String> description;
    public static volatile SingularAttribute<Group1, User> admin;

}