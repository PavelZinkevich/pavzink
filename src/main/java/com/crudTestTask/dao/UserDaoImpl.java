package com.crudTestTask.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.crudTestTask.user.User;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. User details: " + user);
    }

    @Override
    public void deleteUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user!=null){
            session.delete(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully load. User details: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        for (User user: userList){
            logger.info("User list: " + user);
        }

        return userList;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUserBy(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where Name like :name");
        String name = user.getName();
        if (name != null && !name.isEmpty()) {
            query = query.setParameter("name", name);
        } else {
            query = query.setParameter("name", "%");
        }

        List<User> userList = query.list();

        for (User userFromList: userList){
            logger.info("User list: " + userFromList);
        }

        return userList;
    }

}
