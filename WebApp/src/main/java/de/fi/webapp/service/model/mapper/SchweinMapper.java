package de.fi.webapp.service.model.mapper;

import de.fi.webapp.persistence.entity.SchweinEntity;
import de.fi.webapp.presentation.dto.SchweinDTO;
import de.fi.webapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity schwein);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> schweinEntities);
}
