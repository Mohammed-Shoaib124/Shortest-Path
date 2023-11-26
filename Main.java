package pkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.io.BufferedWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
    	
    	String weatherCsvFilePath = "C:/Users/saini/eclipse-workspace/githublatest/pkg/weathertest.csv";
        String distanceCsvFilePath = "C:/Users/saini/eclipse-workspace/githublatest/pkg/distance_matrics.csv";
        String seaLevelCsvFilePath = "C:/Users/saini/eclipse-workspace/githublatest/pkg/seaLevel.csv";
        
        try {
        	 CityWeatherManager cityWeatherManager = new CityWeatherManager();
             DistanceDataManager distanceDataManager = new DistanceDataManager();
             SeaLevel seaLevel = new SeaLevel();

             // Loading weather data
             Map<String, CityData> cityWeatherMap = cityWeatherManager.loadWeatherData(weatherCsvFilePath);

             // Loading distance data
             List<List<String>> distanceData = distanceDataManager.loadDistanceData(distanceCsvFilePath);
                
            // loading seaLevel data
            List<List<String>> seaLeveldata = seaLevel.loadSeaLevel(seaLevelCsvFilePath);
            
             // Combining data
            cityWeatherManager.combineData(cityWeatherMap, distanceData,seaLeveldata);
            
            possible_paths_1 paths = new possible_paths_1(distanceData, "SaltLakeCity-UT", "Albuquerque-NM", 10 ,cityWeatherMap);
            
            List<List<String>> allpaths = paths.findAllPaths("SaltLakeCity-UT", "Albuquerque-NM");
            
//            String filePath = "B:/Fall 2023/Algorithms/Project/allpaths.txt";
//            
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//                for (List<String> path : allpaths) {
//                    // Join the elements of the path into a comma-separated string
//                    String pathString = String.join(" -> ", path);
//                    // Write the path to the file
//                    writer.write(pathString);
//                    writer.newLine(); // Move to the next line for the next path
//                }
//                System.out.println("Paths written to file: " + filePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            
            

            // Print the combined data
//            for (Map.Entry<String, CityData> entry : cityWeatherMap.entrySet()) {
//                String cityName = entry.getKey();
//                CityData cityData = entry.getValue();
//
//                System.out.println("City: " + cityName);
//                for (Map.Entry<String, WeatherData> weatherEntry : cityData.getWeatherData().entrySet()) {
//                    String timestamp = weatherEntry.getKey();
//                    WeatherData weatherData = weatherEntry.getValue();
//                    System.out.println("  Timestamp: " + timestamp + ", Weather: " + weatherData.getWeather() + ", Temperature: " + weatherData.getTemperature());
//                    
//                }
//
//                System.out.println("  Distance Data: " + cityData.getDistanceData());
//                System.out.println("sea level: "+ cityData.getseaLeveldata()+"ft");
//                System.out.println();
//            }
            long endTime = System.currentTimeMillis();

            // Calculate and print the runtime
            long runtime = endTime - startTime;
            System.out.println("Runtime: " + runtime + " milliseconds");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
