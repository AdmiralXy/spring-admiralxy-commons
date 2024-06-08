package com.admiralxy.commons.domain.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class TimestampedEntity implements BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
