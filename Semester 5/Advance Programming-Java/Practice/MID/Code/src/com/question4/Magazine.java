package com.question4;

public class Magazine extends LibraryItem{
    public Magazine(){
        super();
    }
    public Magazine(int itemId,String title,String author){
        super(itemId,title,author);
    }
    @Override
    public double calculateFine(int lateDays){
        return this.magazinelatefee*lateDays;
    }
}
