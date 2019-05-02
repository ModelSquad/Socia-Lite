package socialite.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import socialite.entity.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-02T13:54:12")
@StaticMetamodel(FriendshipRequest.class)
public class FriendshipRequest_ { 

    public static volatile SingularAttribute<FriendshipRequest, Date> dateTime;
    public static volatile SingularAttribute<FriendshipRequest, User> userSender;
    public static volatile SingularAttribute<FriendshipRequest, Integer> friendshipRequestId;
    public static volatile SingularAttribute<FriendshipRequest, String> text;
    public static volatile SingularAttribute<FriendshipRequest, User> userReceiver;

}