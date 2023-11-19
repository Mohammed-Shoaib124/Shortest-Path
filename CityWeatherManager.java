//package cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;
import java.util.ArrayList;

public class CityWeatherManager {
    public Map<String, CityData> loadTemperatureData(String csvFilePath) throws IOException {
        Map<String, CityData> cityTemperatureMap = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    //Skip the header line
                    headerSkipped = true;
                    continue;
                }

                String[] columns = line.split(",");
                String cityName = columns[0].trim();
                int temperature;

                try {
                    temperature = Integer.parseInt(columns[1].trim());
                } catch (NumberFormatException e) {
                    // Handle the case where temperature is not a valid integer
                    System.out.println("Skipping invalid temperature for city " + cityName);
                    continue;
                }

                CityData cityData = new CityData(temperature);
                cityTemperatureMap.put(cityName, cityData);
            }
        }

        return cityTemperatureMap;
    }


    public void combineData(Map<String, CityData> cityTemperatureMap, List<List<String>> distanceData) {
        for (List<String> rowData : distanceData) {
            if (rowData.size() < 2) {
                // Skip rows with insufficient data
                continue;
            }

            String cityName = rowData.get(0).trim();
            CityData cityData = cityTemperatureMap.getOrDefault(cityName, new CityData(0));

            // Parse distance values into integers
            List<Integer> distanceValues = rowData.subList(1, rowData.size()).stream()
                    .map(Integer::parseInt)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            // Set the distance data for the city
            cityData.setDistanceData(distanceValues);

            // Add distance data to the main map using city name as the key
            cityTemperatureMap.put(cityName, cityData);
        }
    }

}