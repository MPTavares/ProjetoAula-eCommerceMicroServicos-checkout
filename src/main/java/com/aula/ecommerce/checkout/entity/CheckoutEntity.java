package com.aula.ecommerce.checkout.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.clients.admin.CreateDelegationTokenOptions;

import javax.persistence.*;

@Builder
@Entity
@Data
public class CheckoutEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @Column
    @Enumerated (value = EnumType.STRING)
    private Status status;

    public enum Status {
        CREATED,
        APPROVED,
        REJECTED
    }

}
