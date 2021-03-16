package ru.skb.tech.test.springhibernatesecurity.services.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skb.tech.test.springhibernatesecurity.dao.UserDao;
import ru.skb.tech.test.springhibernatesecurity.entity.User;
import ru.skb.tech.test.springhibernatesecurity.mapper.Mapper;
import ru.skb.tech.test.springhibernatesecurity.dto.SkbUser;

@Service
public class UserServiceImpl implements UserService<SkbUser> {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapper<SkbUser, User> mapper;

    @Override
    @Transactional
    public SkbUser findByUserLogin(String login) {
        LOG.info("Поиск пользователя по логину: {}", login );
        final User byUserLogin = userDao.findByUserLogin(login);
        return mapper.to(byUserLogin);
    }

    @Override
    @Transactional
    public void save(SkbUser skbUser) {
        final User user = mapper.from(skbUser);
        userDao.save(user);
    }
}
