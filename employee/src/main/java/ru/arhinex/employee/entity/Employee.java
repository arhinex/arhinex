package ru.arhinex.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.arhinex.baseentity.entity.DateCreatedEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document
public class Employee extends DateCreatedEntity {
    private String fio;
    private Date birthDate;
    private Date startWorkDate;
    private Date endWorkDate;
    private PaymentState currentPaymentState;
    private List<Competention> competentions = new ArrayList<>();
    private List<PaymentState> paymentStates = new ArrayList<>();
}
