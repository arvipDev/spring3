package dev.arvip.contentcalendar.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record Content(

        @DateTimeFormat @NotNull
        LocalDateTime dateCreated,
        @DateTimeFormat @Nullable
        LocalDateTime dateUpdated,
        @Id
        @NumberFormat
        Integer id,

        @Column(value = "description")
        @NotBlank(message = "description is mandatory")
        String desc,
        @NotBlank(message = "title is mandatory")
        String title,
        Status status,
        Type contentType,
        String url
) {

}
