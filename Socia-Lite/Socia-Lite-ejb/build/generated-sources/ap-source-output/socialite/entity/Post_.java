package socialite.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import socialite.entity.Media;
import socialite.entity.User;
import socialite.entity.Visibility;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-01T12:03:27")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, Date> date;
    public static volatile ListAttribute<Post, Media> mediaList;
    public static volatile SingularAttribute<Post, Visibility> visibility;
    public static volatile SingularAttribute<Post, String> text;
    public static volatile SingularAttribute<Post, String> title;
    public static volatile SingularAttribute<Post, User> user;
    public static volatile SingularAttribute<Post, Integer> idPost;
    public static volatile SingularAttribute<Post, Integer> likes;

}