package com.benjaminjmiller.customermanagementapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * The CustomerController links the model and the view. It is used to get or update data in the
 * Customer model.
 */
public class CustomerController {

    private Session session;

    public CustomerController() {
        // A session is initialized only once when the controller is constructed.
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = null;
        try {
            MetadataSources metadataSources = new MetadataSources( registry );
            Metadata metadata = metadataSources.buildMetadata();
            sessionFactory = metadata.buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            return;
        }
        session = sessionFactory.openSession();
    }

    // Adds a customer to the DB
    public void addCustomer(String firstName, String lastName, String gender, String address, String phoneNumber,
                            String nameOnCreditCard, String creditCardNumber, String expirationDate, String csc,
                            boolean isRewardsMember, long rewardsPoints) {
        Customer customer = new Customer(firstName, lastName, gender, address, phoneNumber, nameOnCreditCard,
                                         creditCardNumber, expirationDate, csc, isRewardsMember, rewardsPoints);

        session.beginTransaction();
        session.persist(customer);
        session.getTransaction().commit();
    }

    // Adds multiple customers to the DB
    public void addCustomers(List<Customer> customers) {
        session.beginTransaction();
        for (Customer customer : customers) {
            session.persist(customer);
        }
        session.getTransaction().commit();
    }

    // Deletes the customer with the given ID from the DB
    public void deleteCustomer(long id) {
        session.beginTransaction();
        Customer customer = new Customer();
        customer.setId(id);
        session.remove(customer);
        session.getTransaction().commit();
    }

    // Updates the data in the DB for the given customer
    public void updateCustomer(Customer customer) {
        session.beginTransaction();
        session.merge(customer);
        session.getTransaction().commit();
    }

    // Returns the Customer with the given ID
    public Customer getCustomer(long id) {
        session.beginTransaction();
        Customer result = session.get(Customer.class, id);
        session.getTransaction().commit();
        return result;
    }

    // Returns all the customers in the DB
    public List<Customer> getCustomers() {
        session.beginTransaction();
        List result = session.createQuery("from Customer").list();
        session.getTransaction().commit();
        return result;
    }

}
