
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.StorageResource;
import edu.duke.FileResource;


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
    
    public StorageResource getAllGenes(String dna){
        StorageResource dnaCollect = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        
        while (startIndex != -1){
            int stopCodonTAA = findStopCodon(dna, startIndex, "TAA");
            int stopCodonTAG = findStopCodon(dna, startIndex, "TAG");
            int stopCodonTGA = findStopCodon(dna, startIndex, "TGA");
            int stopCodon = Math.min(stopCodonTAA, Math.min(stopCodonTAG, stopCodonTGA));
            if (stopCodon < dna.length()) {
                dnaCollect.add(dna.substring(startIndex, stopCodon + 3));
            }
            startIndex = dna.indexOf("ATG", stopCodon + 3);
        }
        return dnaCollect;
    }
    
    public double cgRatio(String dna) {
        double stringLength = dna.length();
        int numberOfC = 0;
        int numberOfG = 0;
        int indexOfC = dna.indexOf("C");
        int indexOfG = dna.indexOf("G");
        
        while (indexOfC != -1){
            numberOfC += 1;
            indexOfC = dna.indexOf("C",indexOfC + 1);
        }
        
        while (indexOfG != -1){
            numberOfG += 1;
            indexOfG = dna.indexOf("G",indexOfG + 1);
        }
        
        double ratioOfLetter = (numberOfC + numberOfG)/stringLength;
        return ratioOfLetter;
    }
    
    public void testGetAllGenes() {
        // Example 1
        String dna1 = "ATGAAATGAAAA";
        StorageResource genes1 = getAllGenes(dna1);
        System.out.println("Example 1 Genes:");
        for (String gene : genes1.data()) {
            System.out.println(gene);
        }
        
        // Example 2
        String dna2 = "ATGAAATGAATGTAGATGTAA";
        StorageResource genes2 = getAllGenes(dna2);
        System.out.println("Example 2 Genes:");
        for (String gene : genes2.data()) {
            System.out.println(gene);
        }
        
        // Add similar blocks for the other examples...
    }
    
    public void processGenes (StorageResource sr){
        int numberOfLongString = 0;
        int numberOfHighCG = 0;
        int geneLength = 0;
        int count = 0;
        int countTotal = 0;
        for (String gene : sr.data()){
            if (gene.length() > 60){
                System.out.println(gene);
                numberOfLongString += 1;
            }
            
            if (cgRatio(gene) > 0.35){
                System.out.println(gene);
                numberOfHighCG += 1;
            }
            
            if (gene.length() > geneLength){
                geneLength = gene.length();
            }
            
            int index = gene.indexOf("CTG");
            int countCodon = 0;
            while (index != -1){
                if (index % 3 == 0){
                    countCodon += 1;
                }
                index = gene.indexOf("CTG", index + 1);
            }
            countTotal += countCodon;
            count += 1;
        }
        System.out.println(numberOfLongString);
        System.out.println(numberOfHighCG);
        System.out.println(geneLength);
        System.out.println(count);
        System.out.println(countTotal);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dnaLower = fr.asString();
        String dna = dnaLower.toUpperCase();
        StorageResource geneCollect= getAllGenes(dna);
        processGenes(geneCollect);
    }
}
