package com.question2;

public class Cash extends PaymentMethod{

    @Override
    public double processPayment(double amount){
        return amount;
    }
}
