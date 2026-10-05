package com.inheritance;

public class Application{
    public static void main(String[] args){
        Animal animal=new Animal(true);
        Human human=new Human(true,"Brown","Male");
        animal.eat();
        human.eat();
    }
}