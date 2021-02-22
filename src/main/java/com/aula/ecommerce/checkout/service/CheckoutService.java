package com.aula.ecommerce.checkout.service;

import com.aula.ecommerce.checkout.entity.CheckoutEntity;
import com.aula.ecommerce.checkout.resource.CheckoutRequest;

import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);
}
