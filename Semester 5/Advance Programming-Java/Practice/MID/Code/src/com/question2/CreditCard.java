package com.question2;

public class CreditCard extends PaymentMethod {

    @Override
    public double processPayment(double amount){
        return amount+20;
    }
}
