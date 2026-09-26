package bd.edu.bubt.sms;

public class GradingSystemBUBT {
    public static String calculate_grade(int mark){
        if(mark>=80){
            return "A+";
        }
        else if(mark>=75){
            return "A";
        }
        else if(mark>=70){
            return "A-";
        }
        else if(mark>=65){
            return "B+";
        }
        else if(mark>=60){
            return "B";
        }
        else if (mark>=55) {
            return "B-";
        }
        else if (mark>=45) {
            return "C+";
        }
        else if (mark>=40) {
            return "C";
        } else return "F";

    }
}
