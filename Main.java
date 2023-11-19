//package cities;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        String weatherCsvFilePath = "datasets/weather_avg.txt";
        String distanceCsvFilePath = "datasets/distance_matrics.txt";
        CityGraph map = new CityGraph();

        // Generate Graph
        map.createGraph(distanceCsvFilePath,weatherCsvFilePath);
        List<List<Integer>> distanceMatrix = map.getAdjacencyMatrix();
        System.out.println(distanceMatrix.size()+" X " + distanceMatrix.get(0).size());
        // Path Algorithms...
    }
}
