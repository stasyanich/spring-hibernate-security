package ru.skb.tech.test.springhibernatesecurity.mapper;

public interface Mapper<S,T> {
    T from(S source);

    S to(T target);
}
