package com.aula.ecommerce.checkout.config;

import com.aula.ecommerce.checkout.streaming.CheckoutCreatedSource;
import com.aula.ecommerce.checkout.streaming.PaymentPaidSink;
import com.aula.ecommerce.payment.event.PaymentCreatedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value ={CheckoutCreatedSource.class, PaymentPaidSink.class})
public class StreamingConfig {
}
