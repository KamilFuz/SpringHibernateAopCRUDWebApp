package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDAOHibernateImpl implements CustomerDAO{

    private EntityManager entityManager;

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOHibernateImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

//        Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
        org.hibernate.query.Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);

        List<Customer> customers = theQuery.getResultList();
        return customers;
    }

    @Override
    public List<Customer> getCustomers() {

        Session currentSession = entityManager.unwrap(Session.class);
//        Session currentSession = sessionFactory.getCurrentSession();

        org.hibernate.query.Query<Customer> theQuery =
                currentSession.createQuery("from Customer", Customer.class);

        List<Customer> customers = theQuery.getResultList();
        return customers;

    }

    @Override
    public void saveCustomer(Customer customer) {

        // get the current hibernate session

//        Session currentSession = sessionFactory.getCurrentSession();
        Session currentSession = entityManager.unwrap(Session.class);

        // save the customer

        currentSession.saveOrUpdate(customer);

    }

    @Override
    public Customer getCustomer(int id) {

        // get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // read from database using the primary key

        Customer customer = currentSession.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        // get the current hibernate session

        Session currentSession = entityManager.unwrap(Session.class);

        // delete customer from database by primary key

        Query query = currentSession.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();

    }
}
