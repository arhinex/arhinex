package ru.arhinex.baseapi.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateCreatedTO extends Identifiable {

    private UUID id;
    private Date created;
    private String createdBy;
    private Date updated;
    private String updatedBy;
    private boolean archived;
}
