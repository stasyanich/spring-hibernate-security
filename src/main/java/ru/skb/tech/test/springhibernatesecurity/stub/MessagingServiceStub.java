package ru.skb.tech.test.springhibernatesecurity.stub;

import lombok.SneakyThrows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import ru.skb.tech.test.springhibernatesecurity.dto.SkbUser;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto.MessageId;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.MessagingService;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class MessagingServiceStub implements MessagingService {

    @Override
    public <T> MessageId send(Message<T> msg) {
        return new MessageId(UUID.randomUUID());
    }

    @Override
    public <T> Message<T> receive(MessageId messageId) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        // return our stub message here.
        return new Message<T>() {
            @Override
            public T getPayload() {
                return (T) new SkbUser();
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };
    }

    @Override
    public <R, A> Message<A> doRequest(Message<R> request) throws TimeoutException {
        final MessageId messageId = send(request);

        return receive(messageId);
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
