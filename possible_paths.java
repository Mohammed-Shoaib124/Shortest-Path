package cities1;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // String weatherCsvFilePath = "/weather.csv";
        // String distanceCsvFilePath = "/distance_matrics.csv";

        // String weatherCsvFilePath = "/uploads/weather_avg.csv";
//        String distanceCsvFilePath = "B:/Fall 2023/Algorithms/Project/Sam Code/distances.csv";
        String distanceCsvFilePath = "B:Fall 2023/Algorithms/Project/distance_matrics.csv";

        try {
            // CityWeatherManager cityWeatherManager = new CityWeatherManager();
            DistanceDataManager distanceDataManager = new DistanceDataManager();
            // Load temperature data
            // Map<String, CityData> cityTemperatureMap =
            // cityWeatherManager.loadTemperatureData(weatherCsvFilePath);

            // Load distance data
            List<List<String>> distanceData = distanceDataManager.loadDistanceData(distanceCsvFilePath);
//            System.out.println(distanceData);


            String startCity = "Mill City-NV"; // Replace with actual start city
            String endCity = "Howe-ID"; // Replace with actual end city
            String startState = startCity.split("-")[1]; // Extract state from the city format "City-State"
            String endState = endCity.split("-")[1]; // Extract state from the city format "City-State"
            
            possible_paths paths = new possible_paths(distanceData, startCity);
 
 			List<List<String>> allPaths = paths.findAllPaths(startCity, endCity);

            // Find all paths from startCity to endCity within the same state
            //List<List<String>> sameStatePaths = paths.findPathsByStates(startState, endState, Integer.MAX_VALUE);

            // Print the paths and their distances
//            for (List<String> path : sameStatePaths) {
//                int totalDistance = paths.calculateTotalDistance(path);
//                System.out.println("Path: " + path);
//                System.out.println("Total Distance: " + totalDistance + " mi");
//                System.out.println();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
