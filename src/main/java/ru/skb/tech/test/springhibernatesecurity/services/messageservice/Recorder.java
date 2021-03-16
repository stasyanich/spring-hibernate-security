package ru.skb.tech.test.springhibernatesecurity.services.messageservice;

public interface Recorder<T> {
     void record(T object);
}
