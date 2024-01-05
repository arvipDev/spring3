package dev.arvip.contentcalendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value="cc")
public record ContentCalendarConfiguration(
        //NOTE - these names should match with the ones provided in the properties/yml file
        String appName,
        String appStatus
) {}
