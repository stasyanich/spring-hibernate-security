package ru.skb.tech.test.springhibernatesecurity.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skb.tech.test.springhibernatesecurity.entity.User;
import ru.skb.tech.test.springhibernatesecurity.dto.SkbUser;


@Service
public class SkbUserMapper implements Mapper<SkbUser, User> {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User from(SkbUser source) {
        User target = null;
        if (source != null) {
            target = new User();
            target.setId(source.getId());
            target.setLogin(source.getLogin());
            target.setFirstName(source.getFirstName());
            target.setLastName(source.getLastName());
            target.setMiddleName(source.getMiddleName());
            target.setEmail(source.getEmail());
            target.setPassword(passwordEncoder.encode(source.getPassword()));
        }
        return target;
    }

    @Override
    public SkbUser to(User target) {
        SkbUser source = null;
        if (target !=null) {
            source = new SkbUser();
            source.setId(target.getId());
            source.setLogin(target.getLogin());
            source.setFirstName(target.getFirstName());
            source.setLastName(target.getLastName());
            source.setMiddleName(target.getMiddleName());
            source.setEmail(target.getEmail());
        }
        return source;
    }
}
