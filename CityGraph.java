import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityGraph {
    List<List<Integer>> adjacencyMatrix = new ArrayList<>();
    public void createGraph(String distanceCsvFilePath , String weatherCsvFilePath) throws IOException {
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
            for (Map.Entry<String, CityData> entry : cityTemperatureMap.entrySet()) {
                String cityName = entry.getKey();
                CityData cityData = entry.getValue();
                this.adjacencyMatrix.add(cityData.getDistanceData());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  List<List<Integer>> getAdjacencyMatrix(){
        return adjacencyMatrix;
    }

}
