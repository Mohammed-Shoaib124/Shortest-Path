//package cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistanceDataManager {

    public List<List<String>> loadDistanceData(String csvFilePath) throws IOException {
        List<List<String>> distanceData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            // Read the CSV file line by line
            while ((line = br.readLine()) != null) {
                // Split the line into columns using a comma as the delimiter
                String[] columns = line.split(",");

                // Create a list to store the entire row data
                List<String> rowData = new ArrayList<>();

                // Add all columns to the list
                for (String value : columns) {
                    rowData.add(value.trim());
                }

                // Add the list to the main list
                distanceData.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return distanceData;
    }

}