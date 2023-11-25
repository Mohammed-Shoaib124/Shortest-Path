package pkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class possible_paths_1 {

    private Map<String, List<String>> adjacencyList;
    private List<List<String>> distanceData;
    
    public static String constat_start;
    public static int radius;
    public Map<String, CityData> cityWeatherMap;

    public possible_paths_1(List<List<String>> distanceData, String startcity, int rad, Map<String, CityData> cityWeatherMap ) {
    	
    	List<List<String>> newdistanceData = new ArrayList<>();
    	String[] columns = null;

    	try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/saini/eclipse-workspace/githublatest/pkg/new_distance.csv"))) {
    	    String line;
    	    line = br.readLine();
    	    if (line != null) {
    	        columns = line.split(",");
    	        
    	        List<String> rowData = new ArrayList<>();

    	        // Add each column value to the rowData List
    	        for (String column : columns) {
    	            rowData.add(column);
    	        }
    	        
    	     // Add the rowData List to the beginning of the distanceData List
    	        distanceData.add(0, rowData);

    	    }
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}

//    	if (columns != null) {
//    	    for (int i = 0; i < columns.length; i++) {
//    	        System.out.println(columns[i]);
//    	    }
//    	}

        adjacencyList = new HashMap<>();
        this.distanceData = distanceData;
        this.cityWeatherMap = cityWeatherMap;
        //System.out.println(distanceData);
        buildAdjacencyList();
        constat_start = startcity;
        radius = rad;
        
    }

    private void buildAdjacencyList() {
    	
        for (List<String> row : distanceData) {
        	
            String city1 = row.get(0);
            adjacencyList.put(city1, new ArrayList<>());
            //System.out.println(city1);
            for (int i = 1; i < row.size(); i++) {
                String city2 = distanceData.get(0).get(i);
                String distanceStr = row.get(i);
                //System.out.println(distanceStr);
                try {
                    int distance = Integer.parseInt(distanceStr);
                    //System.out.println(distance);
                    if (distance > 0) {
                        adjacencyList.get(city1).add(city2);
//                        System.out.println(adjacencyList.get(city1).add(city2));
                    }
                } catch (NumberFormatException e) {
                    // Ignore non-numeric entries
                }
            }
        }
       // System.out.println("Adjacency List: " + adjacencyList);
    }

    public class DistanceResult {
        private int distance;
        private String nearestCity;

        public DistanceResult(int distance, String nearestCity) {
            this.distance = distance;
            this.nearestCity = nearestCity;
        }

        public int getDistance() {
            return distance;
        }

        public String getNearestCity() {
            return nearestCity;
        }
    }
    

    public DistanceResult getDistance(String startCity, String endCity) {
        // Use the distanceData list to find the distance between startCity and endCity
        for (List<String> row : distanceData) {
            String city1 = row.get(0);
            if (city1.equals(startCity)) {
                for (int i = 1; i < row.size(); i++) {
                    String city2 = distanceData.get(0).get(i);
                    if (city2.equals(endCity)) {
                        try {
                            int distance = Integer.parseInt(row.get(i));
                            return new DistanceResult(distance, ""); // Update the DistanceResult constructor accordingly
                        } catch (NumberFormatException e) {
                            // Handle the exception if distance is not a valid integer
                        }
                    }
                }
            }
        }

        // Return a placeholder DistanceResult in case the distance is not found
        return new DistanceResult(0, ""); // Update the DistanceResult constructor accordingly
    }


 // Change the return type of findAllPaths method
    public List<List<String>> findAllPaths(String startCity, String endCity) {
        List<List<String>> allPaths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        List<String> visitedCities = new ArrayList<>();
        int[] pathCount = {0}; // Counter for path count

        findAllPathsDFS(startCity, endCity, currentPath, allPaths, visitedCities, 0, pathCount);
        System.out.println("Completed viewing all paths in given restrictions");
        // Return the list of paths
        return allPaths;
    }


    private void findAllPathsDFS(String currentCity, String endCity, List<String> currentPath, List<List<String>> allPaths, List<String> visitedCities, int totalDistance, int[] pathCount) {
        currentPath.add(currentCity);
        visitedCities.add(currentCity);
        
        //System.out.println(visitedCities);
        if (currentCity.equals(endCity)) {
            // Found a path to the destination
            List<String> sortedPath = new ArrayList<>(currentPath);
            allPaths.add(sortedPath);

            // Print distance and path count for the discovered path
            System.out.println("Path " + pathCount[0] + ": " + sortedPath);
            System.out.println("Total Distance :" + totalDistance + " mi");
            
            
            
            for (int i = 0; i < sortedPath.size() - 1; i++) {
                String fromCity = sortedPath.get(i);
                String toCity = sortedPath.get(i + 1);
                DistanceResult result = getDistance(fromCity, toCity);
                int distance = result.getDistance();

                System.out.println(fromCity + " -> " + toCity + " (" + distance + " mi) ");
                
                CityData data = cityWeatherMap.get(toCity);

                if (data != null) {
                    System.out.println("City: " + toCity);

                    // Fetch and print weather data
                    for (Map.Entry<String, WeatherData> weatherEntry : data.getWeatherData().entrySet()) {
                        String timestamp = weatherEntry.getKey();
                        WeatherData weatherData = weatherEntry.getValue();
                        System.out.println("  Timestamp: " + timestamp + ", Weather: " + weatherData.getWeather() + ", Temperature: " + weatherData.getTemperature() + " F");
                    }

                    // Fetch and print distance data
                    System.out.println("  Distance Data: " + data.getDistanceData());

                    // Fetch and print sea level data
                    System.out.println("  Sea Level: " + data.getseaLeveldata() + " ft");
                } else {
                    System.out.println("Data not available for city: " + toCity);
                }

                
            }
            System.out.println();
            pathCount[0]++;
        } else {
            List<String> neighbors = adjacencyList.get(currentCity);
            
            //System.out.println(neighbors);
            
            if (neighbors != null) {
            	System.out.println(neighbors);
                for (String neighbor : neighbors) {
                	//System.out.println(visitedCities);
                    if (!visitedCities.contains(neighbor)) {
                        DistanceResult result = getDistance(currentCity, neighbor);
                        int distance = result.getDistance();
                        //System.out.println(distance);
                        //System.out.println(totalDistance);
                        // Check if adding the neighbor city will decrease the distance to the destination
                        //System.out.println(getDistance(constat_start, endCity).getDistance());
                        if (totalDistance + distance < (getDistance(constat_start, endCity).getDistance() + radius)) {
                            findAllPathsDFS(neighbor, endCity, currentPath, allPaths, visitedCities, totalDistance + distance, pathCount);
                        }
                    }
                }
            }
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
        visitedCities.remove(currentCity);
    }
}