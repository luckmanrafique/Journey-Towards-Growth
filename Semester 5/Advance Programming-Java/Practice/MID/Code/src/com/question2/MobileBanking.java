package com.question2;

public class MobileBanking extends PaymentMethod {

    @Override
    public double processPayment(double amount){
        return amount+(amount*0.01);
    }
}
