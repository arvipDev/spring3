package dev.arvip.contentcalendar.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record Content(

        @DateTimeFormat @NotNull
        LocalDateTime dateCreated,
        @DateTimeFormat @Nullable
        LocalDateTime dateUpdated,
        @NumberFormat
        Integer id,
        @NotBlank(message = "description is mandatory")
        String desc,
        @NotBlank(message = "title is mandatory")
        String title,
        Status status,
        Type contentType,
        String url
) {

}
