
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            if ((currentIndex - startIndex)%3 == 0){
                return currentIndex;
            }
            else{
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon (){
        String dna = "TAGATGAAATAG";
        System.out.println(findStopCodon(dna, 6, "TAG"));
        
        dna = "ATGAAATAAAGGGTAG";
        System.out.println(findStopCodon(dna, 0, "TAG"));
        
        dna = "ATGAAATAAACCCGGG";
        System.out.println(findStopCodon(dna, 0, "TAG"));
        
        dna = "ATGAAATGATAA";
        System.out.println(findStopCodon(dna, 0, "TAG"));
        
        dna = "CCCATGAAATAGCCC";
        System.out.println(findStopCodon(dna, 0, "TAG"));
    }
}
