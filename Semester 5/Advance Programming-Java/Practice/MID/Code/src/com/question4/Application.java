package com.question4;

public class Application {
    public static void main(String[] args){
        Book b=new Book(101,"Java Programming","James Gosling");
        Magazine m=new Magazine(60,"Papa ki pari","Sugar Daddy");
        System.out.println("----Book info----");
        b.show();
        System.out.println("Fine for 2 days: "+b.calculateFine(2));
        System.out.println("----Magazine info----");
        m.show();
        System.out.println("Fine for 2 days: "+m.calculateFine(2));
    }
}
