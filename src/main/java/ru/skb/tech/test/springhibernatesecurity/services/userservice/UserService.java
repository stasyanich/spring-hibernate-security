package ru.skb.tech.test.springhibernatesecurity.services.userservice;


public interface UserService<T> {
    T findByUserLogin(String login);
    void save(T object);
}
