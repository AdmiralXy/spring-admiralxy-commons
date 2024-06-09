package io.github.admiralxy.commons.domain.core;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class GuidEntity extends TimestampedEntity {
    private UUID id;
}
