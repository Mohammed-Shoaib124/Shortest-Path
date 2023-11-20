import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityGraph {
    List<List<Integer>> adjacencyMatrix = new ArrayList<>();
    Map<String, CityData> cityTemperatureMap;
    List<List<String>> distanceData;

    public void createGraph(String distanceCsvFilePath, String weatherCsvFilePath) {
        try {
            // int count=0;
            CityWeatherManager cityWeatherManager = new CityWeatherManager();
            DistanceDataManager distanceDataManager = new DistanceDataManager();
            // Load temperature data
            this.cityTemperatureMap = cityWeatherManager.loadTemperatureData(weatherCsvFilePath);
            // Load distance data
            this.distanceData = distanceDataManager.loadDistanceData(distanceCsvFilePath);
            // Combine temperature and distance data
            cityWeatherManager.combineData(cityTemperatureMap, distanceData);
            for (Map.Entry<String, CityData> entry : cityTemperatureMap.entrySet()) {
                String cityName = entry.getKey();
                CityData cityData = entry.getValue();
//                System.out.println(cityName+" "+cityData.temperature);
                this.adjacencyMatrix.add(cityData.getDistanceData());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<List<Integer>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Map<String, CityData> getTemperature() {
        return cityTemperatureMap;

    }

}
