package ru.arhinex.baseentity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.arhinex.baseentity.entity.DateCreatedEntity;

import java.util.UUID;

public interface BaseRepository<T extends DateCreatedEntity> extends MongoRepository<T, UUID> {
}
