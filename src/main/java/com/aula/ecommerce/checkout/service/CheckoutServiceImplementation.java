package com.aula.ecommerce.checkout.service;

import com.aula.ecommerce.checkout.entity.CheckoutEntity;
import com.aula.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.aula.ecommerce.checkout.repository.CheckoutRepository;
import com.aula.ecommerce.checkout.resource.CheckoutRequest;
import com.aula.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

//import com.aula.ecommerce.checkout.event.CheckoutCreatedEvent;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImplementation implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {

        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .build();

        final CheckoutEntity entity=checkoutRepository.save(checkoutEntity);

        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(entity);


    }
}
