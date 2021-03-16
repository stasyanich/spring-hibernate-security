package ru.skb.tech.test.springhibernatesecurity.services.messageservice;

import org.springframework.retry.annotation.Retryable;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.EmailAddress;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.EmailContent;

import java.util.concurrent.TimeoutException;

/**
 * Ориентировочный интерфейс мейлера.
 */
@Retryable(value = TimeoutException.class) //дефолтный трехразовый повтор отправки сообщения
public interface SendMailer {
    void sendMail (EmailAddress toAddress, EmailContent messageBody) throws TimeoutException;
}
