import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void totalBirths (){
        FileResource fr = new FileResource();
        int girlNameNum = 0;
        int boyNameNum = 0;
        int totalNum = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            String gender = rec.get(1);
            int nameNum = Integer.parseInt(rec.get(2));
            if (gender.equals("F")){
                girlNameNum += 1;
            }
            else{
                boyNameNum += 1;
            }
        }
        totalNum = girlNameNum + boyNameNum;
        System.out.println("number of girls names : " + girlNameNum);
        System.out.println("number of boys names : " + boyNameNum);
        System.out.println("number of total names : " + totalNum);
    }
    
    public void testTotalBirths (){
        totalBirths();
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        int numOfChildren = 0;
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            String nameInFile = rec.get(0);
            String genderInFile = rec.get(1);
            if (genderInFile.equals(gender)){
                if (nameInFile.equals(name)){
                    rank += 1;
                    break;
                }
                else{
                    rank += 1;
                }
                numOfChildren += 1;
            }
        }
        if (rank == numOfChildren){
            rank = -1;
        }
        return rank;
    }
    
    public void testGetRank(){
        int rank1 = getRank(1960, "Frank", "M");
        System.out.println("rank for rank1: " + rank1);
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        String name = null;
        for (CSVRecord rec : fr.getCSVParser(false)){
            String recGender = rec.get(1);
            String recName = rec.get(0);
            if (recGender.equals(gender)){
                rank -= 1;
                if (rank == 0){
                    name = rec.get(0);
                    break;
                }
            }
        }
        if (rank != 0){
            name = "NO NAME";
        }
        return name;
    }
    
    public void testGetName(){
        System.out.println("the name is: " + getName(2012, 350, "F"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr1 = new FileResource();
        FileResource fr2 = new FileResource();
        int rank = 0;
        int numOfChildren = 0;
        String pro = null;
        for (CSVRecord rec : fr1.getCSVParser(false)){
            String nameInFile = rec.get(0);
            String genderInFile = rec.get(1);
            if (genderInFile.equals(gender)){
                if (nameInFile.equals(name)){
                    rank += 1;
                    break;
                }
                else{
                    rank += 1;
                }
                numOfChildren += 1;
            }
        }
        if (rank == numOfChildren){
            rank = -1;
        }
        String newName = null;
        for (CSVRecord rec : fr2.getCSVParser(false)){
            String recGender = rec.get(1);
            String recName = rec.get(0);
            if (recGender.equals(gender)){
                rank -= 1;
                if (rank == 0){
                    newName = rec.get(0);
                    break;
                }
            }
        }
        if (rank != 0){
            name = "NO NAME";
        }
        if (gender.equals("F")){
            pro = "she";
        }
        else{
            pro = "he";
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if " + pro + " was born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1972, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        File fileWithHighestRank = null;
        int highestRank = -1;
        String year = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int rank = 0;
            int numOfChildren = 0;
            for (CSVRecord rec : fr.getCSVParser(false)){
                String recName = rec.get(0);
                String recGender = rec.get(1);
                if (recGender.equals(gender)){
                    if (recName.equals(name)){
                        rank += 1;
                        break;
                    }
                    else{
                        rank += 1;
                    }
                    numOfChildren += 1;
                }
            }
            if (rank == numOfChildren){
                    rank = 0;
                }
            if ((fileWithHighestRank == null) || (highestRank > rank) || (rank != -1)){
                highestRank = rank;
                fileWithHighestRank = f;
            }
        }
        if (highestRank == -1){
            return -1;
        }
        else{
            return Integer.parseInt((fileWithHighestRank.getName()).substring(3,7));
        }
    }
    
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mich", "F"));
    }
    
    public double getAverageRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double total = 0;
        int fileNum = 0;
        String year = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int rank = 0;
            int numOfChildren = 0;
            for (CSVRecord rec : fr.getCSVParser(false)){
                String recName = rec.get(0);
                String recGender = rec.get(1);
                if (recGender.equals(gender)){
                    if (recName.equals(name)){
                        rank += 1;
                        break;
                    }
                    else{
                        rank += 1;
                    }
                    numOfChildren += 1;
                }
            }
            if (rank == numOfChildren){
                    rank = 0;
                }
            total += rank;
            fileNum += 1;
        }
        if (total == 0){
            return -1;
        }
        else{
            return total/fileNum;
        }
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int rank = 0;
        int birth = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            String recName = rec.get(0);
            String recGender = rec.get(1);
            int recBirth = Integer.parseInt(rec.get(2));
            if (recGender.equals(gender)){
                if (recName.equals(name)){
                    break;
                }
                else{
                    birth += recBirth;
                }
            }
        }
        return birth;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(2012, "Drew", "M"));
    }
}
