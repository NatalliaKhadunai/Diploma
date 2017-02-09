package com.minsk24.util;

import com.minsk24.bean.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Natallia_Khadunai on 2/8/2017.
 */
public class SessionFactoryUtil {
    private static SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Article.class)
            .addAnnotatedClass(Account.class)
            .addAnnotatedClass(Comment.class)
            .addAnnotatedClass(Tag.class)
            .addAnnotatedClass(Event.class)
            .addAnnotatedClass(UserBeforeEventRate.class)
            .addAnnotatedClass(UserAfterEventRate.class)
            .addAnnotatedClass(Advertisement.class)
            .configure().buildSessionFactory();

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
