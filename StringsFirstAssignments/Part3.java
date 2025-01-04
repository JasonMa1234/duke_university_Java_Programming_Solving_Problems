
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurrences (String stringa, String stringb){
        int indexOfStringa1 = stringb.indexOf(stringa);
        int indexOfStringa2 = stringb.indexOf(stringa, indexOfStringa1);
        String appearTwice = "false";
        if (indexOfStringa2 != -1){
            appearTwice = "true";
        }
        return appearTwice;
    }
    
    public void testing (){
        String testStringa = "ab";
        System.out.println("stringa is " + testStringa);
        String testStringb = "ababababbbbbb";
        System.out.println("stringb is " + testStringb);
        System.out.println(twoOccurrences(testStringa, testStringb));
        
        testStringa = "abcd";
        System.out.println("stringa is " + testStringa);
        testStringb = "aabbccdd";
        System.out.println("stringb is " + testStringb);
        System.out.println(twoOccurrences(testStringa, testStringb));
    }
}
