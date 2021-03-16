package ru.skb.tech.test.springhibernatesecurity.services.messageservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ru.skb.tech.test.springhibernatesecurity.dto.SkbUser;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.EmailAddress;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.EmailContent;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.MessageId;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.SkbMessage;

import java.util.concurrent.TimeoutException;

@Service
public class RecorderImpl implements Recorder<SkbUser> {
    private final static Logger LOG = LoggerFactory.getLogger(RecorderImpl.class);

    @Autowired
    private MessagingService messagingService;
    @Autowired
    private SendMailer sendMailer;

    @Override
    public void record(SkbUser skbUser) {
        new Thread(() -> {
            Message<Object> message = null;
            //отправка
            final MessageId messageId = send(new SkbMessage(skbUser));
            //ожидание
            try {
                message = receive(messageId);
            } catch (TimeoutException e) {
                LOG.warn("получение невозможно: {}", e.getMessage());
            }
            final Object payload = message.getPayload() != null ? message.getPayload() : null;
            if (payload instanceof SkbUser) {
                try {
                    sendEmailToUser((SkbUser) payload);
                } catch (TimeoutException e) {
                    LOG.warn("отправка почты невозможна: {}", e.getMessage());
                }
            }
        }).start();
    }

    public void sendEmailToUser(SkbUser user) throws TimeoutException {
        LOG.debug("try to send user to SendEmail {}", user);
        final EmailAddress emailAddress = new EmailAddress(user.getEmail());
        final String answer = user.isApproved() ? "подтверждена" : "отклонена";
        final EmailContent emailContent = new EmailContent("Ваша учетная запись " + answer);
        sendMailer.sendMail(emailAddress, emailContent);
        LOG.debug("success send user to SendEmail {}", user);

    }

    public Message<Object> receive(MessageId messageId) throws TimeoutException {
        LOG.debug("try to receive messageId: {} from messagingService", messageId);
        return messagingService.receive(messageId);
    }


    public MessageId send(SkbMessage message) {
        LOG.debug("try to send message: {} to messagingService", message);
        return messagingService.send(message);
    }

}
