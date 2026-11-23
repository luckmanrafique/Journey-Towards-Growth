package bd.edu.bubt.cse.util;

public class ValidationHelper{
    public static boolean wordLengthChecker(String word){
        boolean result=true;
        if(word.length()<8) result=false;
        return result;
    }
    public static boolean wordCharacterChecker(String word,char uperRange,char lowerRange){
        boolean result=false;
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)>=lowerRange && word.charAt(i)<=uperRange) return true;
        }
        return result;
    }
    public static boolean specialCharacterChecker(String word){
        boolean result=false;
        for(int i=0;i<word.length();i++){
            if(!(word.charAt(i)>='A' && word.charAt(i)<='Z')
                    && !(word.charAt(i)>='a' && word.charAt(i)<='z')
                    && !(word.charAt(i)>='0' && word.charAt(i)<='9' ) && !(word.charAt(i)==' '))
            {
                return true;
            }
        }
        return result;
    }
}