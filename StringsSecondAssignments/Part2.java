
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany (String stringa, String stringb){
        int stringLength = stringa.length();
        int timeAppear = 0;
        int currentIndex = stringb.indexOf(stringa);
        if (stringa.isEmpty()) {
            return 0; // Avoid infinite loop if stringa is empty
        }
        while (currentIndex != -1){
            timeAppear += 1;
            currentIndex = stringb.indexOf(stringa, currentIndex + stringLength);
        }
        return timeAppear;
    }
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // Expected output: 3
        System.out.println(howMany("AA", "ATAAAA")); // Expected output: 2
        System.out.println(howMany("ABCDEFG", "ABC")); // Expected output: 0
        System.out.println(howMany("", "ABC")); // Expected output: 0        
    }
}
