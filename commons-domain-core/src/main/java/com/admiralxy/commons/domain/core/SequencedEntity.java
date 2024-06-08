package com.admiralxy.commons.domain.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SequencedEntity extends TimestampedEntity {
    private Long id;
}
