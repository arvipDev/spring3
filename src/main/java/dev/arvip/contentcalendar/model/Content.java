package dev.arvip.contentcalendar.model;

import java.time.LocalDateTime;

public record Content(
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        Integer id,
        String desc,
        String title,
        Status status,
        Type contentType,
        String url
) {

}
