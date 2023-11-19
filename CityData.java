//package cities;
//import java.util.LinkedHashMap;

import java.util.List;
import java.util.ArrayList;


public class CityData {
    public int temperature;
    public List<Integer> distanceData;

    public CityData(int temperature) {
        this.temperature = temperature;
        this.distanceData = new ArrayList<>();
    }

    public int getTemperature() {
        return temperature;
    }

    public List<Integer> getDistanceData() {
        return distanceData;
    }

    public void setDistanceData(List<Integer> distanceData) {
        this.distanceData = distanceData;
    }
}