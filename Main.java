package pkg;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.io.BufferedWriter;
import java.util.List;
import java.util.stream.Collectors;

import pkg.possible_paths_1.DistanceResult;

import java.io.FileWriter;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
    	
    	String formattedDateTime = null;
    	String weatherCsvFilePath = "C:/Users/saini/eclipse-workspace/Milestone3/src/pkg/weather.csv";
        String distanceCsvFilePath = "C:/Users/saini/eclipse-workspace/Milestone3/src/pkg/distance.csv";
        String seaLevelCsvFilePath = "C:/Users/saini/eclipse-workspace/Milestone3/src/pkg/seaLevel.csv";
        
        String distancewithoutheaders = "C:/Users/saini/eclipse-workspace/Milestone3/src/pkg/distance_matrix_without_citynames.csv";
        
        System.out.print("Please provide the name of your starting city and state in the specified format: Ex: (MillCity-NV) ");
        Scanner sc = new Scanner(System.in);
        String startcity = sc.nextLine();
        System.out.println();
        
        System.out.print("Please provide your destination city in the same format: (City-State) ");
        String endcity = sc.nextLine();
        System.out.println();
        
        
        System.out.print("Enter date and time of your commencement (yyyy-MM-dd HH:mm): ");
        String userInput = sc.nextLine();
        System.out.println();
        
        if (userInput.isEmpty()) {
            System.out.println("Error: Please provide a non-empty input.");
            return;
        }
        
        try {
            // Parse user input into LocalDateTime
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(userInput, inputFormatter);

            // Format the LocalDateTime into the desired output format
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            formattedDateTime = localDateTime.format(outputFormatter);

        } catch (Exception e) {
            System.out.println("Error: Unable to parse the input. Please provide a valid date and time format.");
            return;
        }
        
        //Declaration of Objects outside the try-catch block, ensuring its access across the function.
        
        CityWeatherManager cityWeatherManager = null;
        DistanceDataManager distanceDataManager = null;
        Map<String, CityData> cityWeatherMap = null;
        List<List<String>> distanceData = null;
        List<List<String>> seaLeveldata = null;
        display_temperatures temperature = null;
        possible_paths_1 paths;
        SeaLevel seaLevel;
        
        
        // Instantiate classes
        
        try {
            cityWeatherManager = new CityWeatherManager();
            distanceDataManager = new DistanceDataManager();
            seaLevel = new SeaLevel();

            // Load weather data
            cityWeatherMap = cityWeatherManager.loadWeatherData(weatherCsvFilePath);

            // Load distance data
            distanceData = distanceDataManager.loadDistanceData(distanceCsvFilePath);

            // Load seaLevel data
            seaLeveldata = seaLevel.loadSeaLevel(seaLevelCsvFilePath);

            // Combine data
            cityWeatherManager.combineData(cityWeatherMap, distanceData, seaLeveldata);

            temperature = new display_temperatures(cityWeatherMap, formattedDateTime);

        } catch (IOException e) {
            e.printStackTrace();
        }

       
        System.out.println("Please choose any one of the following: ");
        System.out.println("1. Display all possible paths from " + startcity + " to " + endcity);
        System.out.println("2. Run Dijkstra's Algorithm ");
        System.out.println("3. Run Bellman-Ford Algorithm");
        
        int user_selection = sc.nextInt();
        
        
        int[][] adjacencymatrix = distanceDataManager.readCSV(distancewithoutheaders);
        
		if(user_selection == 1) {
        	System.out.print("Specify the desired travel range (in miles) : ");
            int range = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            paths = new possible_paths_1(distanceData, startcity, endcity, range, cityWeatherMap, temperature);
            System.out.print("Displaying all the available paths in the within  " + ( paths.getDistance(startcity, endcity).getDistance() + range ) + " mi");
             
            List<List<String>> allpaths = paths.findAllPaths();
            
        }else if(user_selection == 2) {
        	
        	Dijkstras Dijkstra = new Dijkstras(startcity, endcity, temperature, adjacencymatrix);
        	
        }else if(user_selection == 3) {
        	
        	Graph bellman_ford = new Graph(120, adjacencymatrix, startcity, endcity);
            
            bellman_ford.find_and_addedges();
            
        }else {
        	System.out.println("Error selection. Please try again !");
        	return;
        }
    }
}