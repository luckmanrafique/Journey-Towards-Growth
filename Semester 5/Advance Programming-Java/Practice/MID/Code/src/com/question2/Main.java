package com.question2;

public class Main {
    public static void main(String[] args){
        PaymentMethod p;
        p=new MobileBanking();
        System.out.println("Mobile Banking: "+p.processPayment(100));
        p=new CreditCard();
        System.out.println("Mobile Banking: "+p.processPayment(100));
        p=new Cash();
        System.out.println("Mobile Banking: "+p.processPayment(100));
    }
}
