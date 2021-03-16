package ru.skb.tech.test.springhibernatesecurity.stub;


import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.EmailAddress;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.EmailContent;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.SendMailer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class SendMailerStub implements SendMailer {
    private final static Logger LOG = LoggerFactory.getLogger(SendMailerStub.class);

    @Override
    public void sendMail(EmailAddress toAddress, EmailContent messageBody) throws TimeoutException {
        LOG.debug("try to send email: {}, {}", toAddress, messageBody);
        if(shouldThrowTimeout()) {
            sleep();
            throw new TimeoutException("Timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        // ok.
        LOG.info("Message sent to {}, body {}.", toAddress, messageBody);
    }

    @SneakyThrows
    private static void sleep() {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    private static boolean shouldSleep() {
        return new Random().nextInt(10) == 1;
    }

    private static boolean shouldThrowTimeout() {
        return new Random().nextInt(10) == 1;
    }
}