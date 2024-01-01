package dev.arvip.contentcalendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value="cc")
public record ContentCalendarConfiguration(
        String appName,
        String appStatus
) {}
