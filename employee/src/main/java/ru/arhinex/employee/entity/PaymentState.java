package ru.arhinex.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.CodeNamedEntity;

import java.util.Date;

@Getter
@Setter
@Document
public class PaymentState extends CodeNamedEntity {
    private Graid graid;
    private Employee employee;
    private Integer currentPaySum;
    private Date applyFromDate;
}
