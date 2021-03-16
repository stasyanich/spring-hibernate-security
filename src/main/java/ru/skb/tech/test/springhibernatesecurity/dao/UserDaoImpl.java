package ru.skb.tech.test.springhibernatesecurity.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.skb.tech.test.springhibernatesecurity.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {
    private final static Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserLogin(String login) {
        User result = null;
        final Session session = entityManager.unwrap(Session.class);
        final Query<User> query = session.createQuery("from User where login=:login", User.class);
        query.setParameter("login", login);
        try {
            result = query.getSingleResult();
        } catch (Exception e) {
            //NOP
        }

        return result;
    }

    @Override
    public void save(User user) {
        final Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }
}
