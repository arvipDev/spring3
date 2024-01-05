package dev.arvip.contentcalendar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.NumberFormat;


public record Person(
        String firstName,
        String lastName,
        Integer age,
        Sex sex,
        String phoneNumber,
        String address,
        @Id
        @NumberFormat
        Integer personId
) {
}
