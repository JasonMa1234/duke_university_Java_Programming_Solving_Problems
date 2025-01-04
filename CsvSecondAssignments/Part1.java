
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord records : parser){
            double currentTemp = Double.parseDouble(records.get("TemperatureF"));
            
            if (currentTemp == -9999) {
                continue;
            }
            if (coldestSoFar == null){
                coldestSoFar = records;
            }
            else{
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if (currentTemp < coldestTemp){
                    coldestSoFar = records;
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        String time = coldest.get("TimeEDT");
        System.out.println("Coldest temperature in file: " + coldest.get("TemperatureF") + "in " + time);
    }
    
    public String fileWithColdestTemperature (){
        CSVRecord coldestSoFar = null;
        String coldestName = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            
            
            if (currentTemp == -9999) {
                continue;
            }
            
            if (coldestSoFar == null){
                coldestSoFar = current;
                coldestName = f.getName();
            }
            else{
                double coldest = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldest){
                    coldestSoFar = current;
                    coldestName = f.getAbsolutePath();
                }
            }
        }
        return coldestName;
    }
    
    public void testFileWithColdestTemperature(){
        String filePath = fileWithColdestTemperature ();
        int pathLength = filePath.length();
        int nameLength = ("weather-2014-01-01.csv").length();
        String fileName = filePath.substring(pathLength - nameLength, pathLength);
        System.out.println("Coldest day was in file " + fileName);
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser();
        String coldestTemp = coldestHourInFile(parser).get("TemperatureF");
        System.out.println("Coldest temperature on that day was " + coldestTemp + "on " + fileName);
        
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestHumidityRecord = null;
        for (CSVRecord records : parser){
            String currentHumidity = records.get("Humidity");

            if (currentHumidity.equals("N/A")) {
                continue; // Skip records with "N/A" humidity
            }
            
            if (lowestHumidityRecord == null){
                lowestHumidityRecord = records;
            }
            else{
                int humidityCurrent = Integer.parseInt(records.get("Humidity"));
                int humidityLowest = Integer.parseInt(lowestHumidityRecord.get("Humidity"));
                if (humidityCurrent < humidityLowest){
                    lowestHumidityRecord = records;
                }
            }
        }
        
        return lowestHumidityRecord;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String humidityLowest = csv.get("Humidity");
        String time = csv.get("DateUTC");
        System.out.println("Lowest humidity was " + humidityLowest + "at" + time);
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumiditySoFar == null){
                lowestHumiditySoFar = current;
            }
            else{
                int humidityCurrent = Integer.parseInt(current.get("Humidity"));
                int humidityLowest = Integer.parseInt(lowestHumiditySoFar.get("Humidity"));
                if (humidityCurrent < humidityLowest){
                    lowestHumiditySoFar = current;
                }
            }
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity =  lowestHumidityInManyFiles();
        String humidity = lowestHumidity.get("Humidity");
        String time = lowestHumidity.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + "at " + time);
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double temperature = 0;
        for (CSVRecord records : parser){
            if (records.get("TemperatureF") != "-9999"){
                count += 1;
                temperature += Double.parseDouble(records.get("TemperatureF"));
            }
        }
        double average = temperature/count;
        return average;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int count = 0;
        double temperature = 0;
        for (CSVRecord records : parser){
            if (records.get("TemperatureF") != "-9999" && (records.get("Humidity") != "N/A") && (Integer.parseInt(records.get("Humidity")) >= value)){
                count += 1;
                temperature += Double.parseDouble(records.get("TemperatureF"));
            }
        }
        if (count == 0){
            return -9999;
        }
        else{
            double average = temperature/count;
            return average;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average == -9999){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + average);
        }
    }
}

