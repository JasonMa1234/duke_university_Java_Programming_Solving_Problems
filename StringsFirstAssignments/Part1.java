
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        String gene = "";
        if (startIndex != -1){
            if (stopIndex != -1){
                if ((startIndex - stopIndex) % 3 == 0){
                    gene = dna.substring(startIndex, stopIndex + 3);
                }
            }
        }
        return gene;
    }
    
    public void testSimpleGene (){
        String dna = "AACGTATGTTGCAATAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGCGATACGTAATCG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "CGTATCGAGC";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
