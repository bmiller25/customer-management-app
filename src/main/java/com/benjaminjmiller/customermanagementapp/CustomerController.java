package com.benjaminjmiller.customermanagementapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CustomerController {

    public CustomerController() {
    }

    public static String getFirstName() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        SessionFactory sessionFactory = null;
        try {
            MetadataSources metadataSources = new MetadataSources( registry );
            System.out.println("DEBUG: CREATED METADATASOURCES");
            Metadata metadata = metadataSources.buildMetadata();
            System.out.println("DEBUG: BUILT METADATASOURCES");
            sessionFactory = metadata.buildSessionFactory();
            System.out.println("DEBUG: BUILT SESSION FACTORY");
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return null;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Customer").list();
        for (Object o : result) {
            Customer customer = (Customer) o;
            session.getTransaction().commit();
            session.close();
            return customer.getFirstName();
        }

        return null;
    }

}
