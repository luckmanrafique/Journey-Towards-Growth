package com.question3;

public class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;
    public Account(){
        this.accountNumber=0;
        this.accountHolder=" ";
        this.balance=0.0;
    }
    public Account(int accountNumber,String accountHolder,double balance){
        this.accountNumber=accountNumber;
        this.accountHolder=accountHolder;
        this.balance=balance;
    }
    public void deposit(int amount){
        this.balance+=amount;
        System.out.println("Deposited: "+amount);
    }
    public void deposit(int amount,String description){
        this.balance+=amount;
        System.out.println("Deposited: "+amount+" Reference: "+description);
    }
    public void withdraw(int amount){
        if(amount>0 && amount<=balance){
            this.balance-=amount;
            System.out.println("Withdraw amount: "+amount);
        }
        else System.out.println("Invalid amount");
    }
    public void withdraw(int amount,String description){
        if(amount>0 && amount<=balance){
            this.balance-=amount;
            System.out.println("Withdraw amount: "+amount+" Reference: "+description);
        }
        else System.out.println("Invalid amount");
    }
    public void showDetails(){
        System.out.println("Account number: "+this.accountNumber+
                "\nAccount holder: "+this.accountHolder+
                "\nBalance: "+this.balance);
    }

}
