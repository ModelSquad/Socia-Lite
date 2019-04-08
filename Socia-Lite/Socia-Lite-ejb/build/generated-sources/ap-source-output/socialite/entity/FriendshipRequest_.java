package socialite.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import socialite.entity.FriendshipRequestPK;
import socialite.entity.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-08T12:08:25")
@StaticMetamodel(FriendshipRequest.class)
public class FriendshipRequest_ { 

    public static volatile SingularAttribute<FriendshipRequest, Date> dateTime;
    public static volatile SingularAttribute<FriendshipRequest, User> user1;
    public static volatile SingularAttribute<FriendshipRequest, FriendshipRequestPK> friendshipRequestPK;
    public static volatile SingularAttribute<FriendshipRequest, String> text;
    public static volatile SingularAttribute<FriendshipRequest, User> user;

}