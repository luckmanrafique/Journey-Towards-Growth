package com.question3;

public class Application {
    public static void main(String[] args){
        Account account1=new Account(42,"Luckman Rafique",10000);
        Account account2=new Account(01,"Sugar Daddy",5000000);
        account1.deposit(500);
        account1.deposit(17500,"Salary");
        account1.withdraw(500);
        account1.withdraw(1500,"Transport");
        System.out.println("==== Account 1 ====");
        account1.showDetails();
        System.out.println("====================");
        account2.deposit(100000);
        account2.deposit(5000,"Chori ka mal");
        account2.withdraw(500);
        account2.withdraw(500000,"For chick");
        System.out.println("==== Account 2 =====");
        account2.showDetails();
    }
}
