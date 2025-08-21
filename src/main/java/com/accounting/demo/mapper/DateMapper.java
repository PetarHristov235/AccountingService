package com.accounting.demo.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class DateMapper {
    @Named("toOffsetUtc")
    public java.time.OffsetDateTime toOffsetUtc(LocalDateTime v) {
        return v == null ? null :
                v.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
    }
}