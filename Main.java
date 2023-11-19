//package cities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

 public class Main {

    public static void main(String[] args) {
    	String weatherCsvFilePath = "/uploads/weather_avg.csv";
        String distanceCsvFilePath ="/uploads/distance_matrics.csv";
        try {
           // int count=0;
        	CityWeatherManager cityWeatherManager = new CityWeatherManager();
            DistanceDataManager distanceDataManager = new DistanceDataManager();
            // Load temperature data
            Map<String, CityData> cityTemperatureMap = cityWeatherManager.loadTemperatureData(weatherCsvFilePath);

            // Load distance data
            List<List<String>> distanceData = distanceDataManager.loadDistanceData(distanceCsvFilePath);

            // Combine temperature and distance data
            cityWeatherManager.combineData(cityTemperatureMap, distanceData);
           //System.out.println("Total number of cities: " + cityTemperatureMap.size());
            // Print the combined data
            for (Map.Entry<String, CityData> entry : cityTemperatureMap.entrySet()) {
                String cityName = entry.getKey();
                CityData cityData = entry.getValue();
               // System.out.println(cityName);
               // count=count+1;
               System.out.println("City: " + cityName);
              System.out.println("  Temperature: " + cityData.getTemperature());
              System.out.println("  Distance Data: " + cityData.getDistanceData());
            //[MillCity-NV,Eureka-NV,Wells-NV,Jackpot-NV,Montello-NV,Owyhee-NV,Eugene-OR,Gresham-OR,Hillsbboro-OR,Bend-OR]
            // System.out.println("  Distance Data: " + cityData.getDistanceData().get(2);//to get a value of distance at index 2 which is source city to Wells-NV
               System.out.println();
            }
                // System.out.println("count"+count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
   }
}
