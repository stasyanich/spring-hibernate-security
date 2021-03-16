package ru.skb.tech.test.springhibernatesecurity.services.messageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MessageId {
    private UUID id;
}
