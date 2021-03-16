package ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import ru.skb.tech.test.springhibernatesecurity.dto.SkbUser;

public class SkbMessage implements Message<SkbUser> {
    private final SkbUser skbUser;
    @Override
    public SkbUser getPayload() {
        return skbUser;
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }

    public SkbMessage(SkbUser skbUser) {
        this.skbUser = skbUser;
    }
}
