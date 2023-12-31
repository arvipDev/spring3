package dev.arvip.contentcalendar.model;

import org.springframework.data.annotation.Id;

public record Person(
        String firstName,
        String lastName,
        Integer age,
        Sex sex,
        String phoneNumber,
        String address,
        @Id
        Integer personId
) {
}
