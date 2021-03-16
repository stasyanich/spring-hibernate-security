package ru.skb.tech.test.springhibernatesecurity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SkbUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "обязательно к заполнению")
    private String login;

    @NotNull(message = "обязателен к заполнению")
    @Size(min = 1, message = "обязателен к заполнению")
    @ToString.Exclude
    private String password;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "обязательно к заполнению")
    private String firstName;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "обязательно к заполнению")
    private String lastName;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "обязательно к заполнению")
    private String middleName;

    @NotNull(message = "обязателен к заполнению")
    @Email
    private String email;

    private boolean approved;

}
