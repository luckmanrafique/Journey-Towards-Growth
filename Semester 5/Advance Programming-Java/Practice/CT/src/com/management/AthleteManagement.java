package com.management;

import com.academy.Athlete;

public class AthleteManagement {
    public static void main(){
        Athlete athlete1= new Athlete("Shimul",60,88);
        Athlete athlete2=new Athlete("Jim",46,39);
        Athlete athlete3=new Athlete("Kaliya",60,65);

        athlete1.display();
        athlete2.display();
        athlete3.display();

    }
}
