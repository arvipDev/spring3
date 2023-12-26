package dev.arvip.contentcalendar.model;

public record Person(
        String firstName,
        String lastName,
        Integer Age,
        Sex sex,
        String phoneNumber,
        String address,
        Integer personId
) {
}
