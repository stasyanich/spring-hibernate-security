package ru.skb.tech.test.springhibernatesecurity.dao;

import ru.skb.tech.test.springhibernatesecurity.entity.User;

public interface UserDao {
    User findByUserLogin(String login);

    void save(User skbUser);
}
