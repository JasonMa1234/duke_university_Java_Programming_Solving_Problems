
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println(StringcountryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        
        StorageResource countryList = listExportersTwoProducts(parser,"cotton", "flowers");
        System.out.println("these country imports these products: ");
        for (String country : countryList.data()){
            System.out.println(country);
        }
        
        parser = fr.getCSVParser();
        
        System.out.println(numberOfExporters(parser, "cocoa"));
        
        parser = fr.getCSVParser();
                
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String StringcountryInfo (CSVParser parser, String country){
        String report = "";
        for (CSVRecord records : parser){
            if (records.get("Country").equals(country)){
                String export = records.get("Exports");
                String value = records.get("Value (dollars)");
                if ((export != "") && (value != "")){
                    report = (country + ": "+ export + ": " + value);
                }
                else{
                    report = "NOT FOUND";
                }
                break;
            }
        }
        return report;
    }
    
    public StorageResource listExportersTwoProducts (CSVParser parser, String exportItem1 , String exportItem2 ){
        StorageResource countryList = new StorageResource(); 
        for (CSVRecord records : parser){
            String export = records.get("Exports");
            if (export.contains(exportItem1)&&export.contains(exportItem2)){
                String country = records.get("Country");
                countryList.add(country);
            }
        }
        return countryList;
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord records : parser){
            String export = records.get("Exports");
            if (export.contains(exportItem)){
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int amountLength = amount.length();
        for (CSVRecord records : parser){
            String value = records.get("Value (dollars)");
            int valueLength = value.length();
            String country = records.get("Country");
            if (valueLength > amountLength){
                System.out.println(country + " " + value);
            }
        }
    }
}
