
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    public int countGenes (String dna){
        int numOfGenes = 0;
        int startIndex = dna.indexOf("ATG");
        while (startIndex != -1){
            int stopIndexTAA = findStopCodon(dna, startIndex,"TAA");
            int stopIndexTAG = findStopCodon(dna, startIndex,"TAG");
            int stopIndexTGA = findStopCodon(dna, startIndex,"TGA");
            int stopIndex = Math.min(stopIndexTAA, Math.min(stopIndexTAG, stopIndexTGA));
            
            if (stopIndex != dna.length()) {
                numOfGenes += 1;
                startIndex = dna.indexOf("ATG", stopIndex + 3);
            } else {
                startIndex = -1;  
            }
        }
        return numOfGenes;
    }
    public void testCountGenes (){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT")); // Expected: 2
        System.out.println(countGenes("ATGTAAATGTAAGTAG"));  // Expected: 2
        System.out.println(countGenes("GCTAGCGTAATAGC"));     // Expected: 0
        System.out.println(countGenes("ATGGTATAAGTAG"));      // Expected: 1
        System.out.println(countGenes("ATGAAATGAAAATGTAGATGAGCTAA"));// Expected: 3
    }
}
