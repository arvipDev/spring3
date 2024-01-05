package dev.arvip.contentcalendar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


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
