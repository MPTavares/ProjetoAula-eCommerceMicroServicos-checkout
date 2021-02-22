package com.aula.ecommerce.checkout.listener;

import com.aula.ecommerce.checkout.entity.CheckoutEntity;
import com.aula.ecommerce.checkout.repository.CheckoutRepository;
import com.aula.ecommerce.checkout.streaming.PaymentPaidSink;
import com.aula.ecommerce.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private CheckoutRepository checkoutRepository;

    @StreamListener (PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent event){

        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
    }

}
