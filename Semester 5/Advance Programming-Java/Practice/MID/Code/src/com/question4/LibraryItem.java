package com.question4;

public class LibraryItem {
    private int itemId;
    private String title;
    private String author;
    final int booklateFee=10;
    final int magazinelatefee=5;
    public LibraryItem(){
        this.title=" ";
        this.author=" ";
    }
    public LibraryItem(int itemId,String title,String author){
        this.itemId=itemId;
        this.title=title;
        this.author=author;
    }
    public double calculateFine(int lateDays){
        return 0;
    }
    public void show(){
        System.out.println("Item Id: "+this.itemId+
                "\nTitle: "+this.title+"\nAuthor: "+this.author);
    }
}
