package bd.edu.NSU.sms;

public class GradingSystemNSU {
    public static String calculate_grade(int mark) {
        if (mark >= 93) {
            return "A";
        } else if (mark >= 90) {
            return "A-";
        } else if (mark >= 87) {
            return "B+";
        } else if (mark >= 83) {
            return "B";
        } else if (mark >= 80) {
            return "B-";
        } else if (mark >= 77) {
            return "C+";
        } else if (mark >= 73) {
            return "C";
        } else if (mark >= 70) {
            return "C-";
        } else if (mark >= 67) {
            return "D+";
        } else if (mark >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
