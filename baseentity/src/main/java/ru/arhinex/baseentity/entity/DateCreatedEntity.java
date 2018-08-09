package ru.arhinex.baseentity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import ru.arhinex.baseapi.to.Identifiable;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class DateCreatedEntity extends Identifiable {
    @Id
    private UUID id;
    @CreatedDate
    private Date created;
    @CreatedBy
    private String createdBy;
    private Date updated;
    private String updatedBy;
    private int version = 0;
    private boolean archived;
}
