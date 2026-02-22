package com.capg;

public interface PaymentGateway {
    boolean processPayment(double amount);
}
