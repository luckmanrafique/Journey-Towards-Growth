package com.question4;

public class Book extends LibraryItem{
    public Book(){
        super();
    }
    public Book(int itemId,String title,String author){
        super(itemId, title, author);
    }
    @Override
    public double calculateFine(int lateDays){
        return this.booklateFee*lateDays;
    }
}
