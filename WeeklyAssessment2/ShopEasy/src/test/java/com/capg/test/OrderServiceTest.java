package com.capg.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capg.OrderService;
import com.capg.PaymentGateway;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private OrderService orderService;


    // Scenario 1: Successful Payment
    @Test
    void testSuccessfulPayment() {

        // Arrange (Stubbing)
        when(paymentGateway.processPayment(2000))
                .thenReturn(true);

        // Act
        String result = orderService.placeOrder(2000);

        // Assert
        assertEquals("Order Confirmed", result);

        // Verify interaction
        verify(paymentGateway, times(1))
                .processPayment(2000);
    }

    // Scenario 2: Payment Failure
    @Test
    void testPaymentFailure() {

        // Arrange (Stubbing)
        when(paymentGateway.processPayment(1500))
                .thenReturn(false);

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(1500)
        );
        assertEquals("Payment Failed", exception.getMessage());

        // Verify interaction
        verify(paymentGateway, times(1))
                .processPayment(1500);
    }

    // Scenario 3: Invalid Order Amount
    @Test
    void testInvalidOrderAmount() {

        // Act + Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.placeOrder(0)
        );

        assertEquals("Invalid Order Amount", exception.getMessage());

        // Verify that payment gateway was NEVER called
        verify(paymentGateway, never())
                .processPayment(anyDouble());
    }
}