import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String weatherCsvFilePath = "/uploads/weather.csv";
        String distanceCsvFilePath = "/uploads/distance.csv";
        String seaLevelCsvFilePath = "/uploads/seaLevel.csv";
        
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
             
   

            // Print the combined data
            for (Map.Entry<String, CityData> entry : cityWeatherMap.entrySet()) {
                String cityName = entry.getKey();
                CityData cityData = entry.getValue();

                System.out.println("City: " + cityName);
                for (Map.Entry<String, WeatherData> weatherEntry : cityData.getWeatherData().entrySet()) {
                    String timestamp = weatherEntry.getKey();
                    WeatherData weatherData = weatherEntry.getValue();
                    System.out.println("  Timestamp: " + timestamp + ", Weather: " + weatherData.getWeather() + ", Temperature: " + weatherData.getTemperature());
                    
                }

                System.out.println("  Distance Data: " + cityData.getDistanceData());
                System.out.println("sea level: "+ cityData.getseaLeveldata()+"ft");
                System.out.println();
            }
        
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
