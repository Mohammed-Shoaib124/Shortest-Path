package cities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class possible_paths {

    private Map<String, List<String>> adjacencyList;
    private List<List<String>> distanceData;

    public possible_paths(List<List<String>> distanceData) {
        adjacencyList = new HashMap<>();
        this.distanceData = distanceData;
        buildAdjacencyList();
    }

    private void buildAdjacencyList() {
        for (List<String> row : distanceData) {
            String city1 = row.get(0);
            adjacencyList.put(city1, new ArrayList<>());

            for (int i = 1; i < row.size(); i++) {
                String city2 = distanceData.get(0).get(i);
                String distanceStr = row.get(i);

                try {
                    int distance = Integer.parseInt(distanceStr);

                    if (distance > 0) {
                        adjacencyList.get(city1).add(city2);
                    }
                } catch (NumberFormatException e) {
                    // Ignore non-numeric entries
                }
            }
        }
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

        // Return the list of paths
        return allPaths;
    }


    private void findAllPathsDFS(String currentCity, String endCity, List<String> currentPath, List<List<String>> allPaths, List<String> visitedCities, int totalDistance, int[] pathCount) {
        currentPath.add(currentCity);
        visitedCities.add(currentCity);

        if (currentCity.equals(endCity)) {
            // Found a path to the destination
            List<String> sortedPath = new ArrayList<>(currentPath);
            allPaths.add(sortedPath);

            // Print distance and path count for the discovered path
            System.out.println("Path " + pathCount[0] + ": " + sortedPath);
            System.out.println("Distance for Path " + pathCount[0] + ": " + totalDistance);
            pathCount[0]++;
        } else {
            List<String> neighbors = adjacencyList.get(currentCity);

            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visitedCities.contains(neighbor)) {
                        DistanceResult result = getDistance(currentCity, neighbor);
                        int distance = result.getDistance();

                        findAllPathsDFS(neighbor, endCity, currentPath, allPaths, visitedCities, totalDistance + distance, pathCount);
                    }
                }
            }
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
        visitedCities.remove(currentCity);
    }
}