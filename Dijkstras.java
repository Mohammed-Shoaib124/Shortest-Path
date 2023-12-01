package pkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.*;

public class Dijkstras {
	private static final int NO_PARENT = -1;
	private static final int THRESHOLD = 250; // Adjust the threshold as needed
	private static String[] cityList = {"MillCity-NV","Eureka-NV","Wells-NV","Howe-ID","Jackpot-NV","Montello-NV","Owyhee-NV","Eugene-OR","Gresham-OR","Hillsbboro-OR","Bend-OR","Beaverton-OR","Medford-OR","Johnday-OR","Roseburg-OR","Bakercity-OR","Salem-OR","Newport-OR","Ashland-OR","Riley-OR","Seattle-WA","Spokane-WA","Tacoma-WA","Vancouver-WA","Bellevue-WA","Kent-WA","Leavenworth-WA","Yakima-WA","Richland-WA","Pullman-WA","WestWendover-NV","LosAngeles-CA","SanDiego-CA","SanJose-CA","SanFrancisco-CA","Fresno-CA","Sacramento-CA","LongBeach-CA","Redding-CA","Bakersfield-CA","Bishop-CA","Irvine-CA","Darwin-CA","Bayswater-CA","Arvin-CA","Albuquerque-NM","LasCruces-NM","RioRancho-NM","SantaFe-NM","Roswell-NM","SaltLakeCity-UT","WestValleyCity-UT","Provo-UT","WestJordan-UT","Sandy-UT","Orem-UT","Ogden-UT","Missoula-MT","GreatFalls-MT","Bozeman-MT","Butte-SilverBow-MT","Helena-MT","Kalispell-MT","Almosa-CO","cortez-CO","GrandJunction-CO","Pueblo-CO","Lamar-CO","Fortcollins-CO","Aspen-CO","Telluride-CO","Montrose-CO","Prescott-AZ","Buckeye-AZ","Sedona-AZ","Tucson-AZ","Phoenix-AZ","Chinle-AZ","Kingman-AZ","Laramie-WY","Gillette-WY","RockSprings-WY","Sheridan-WY","GreenRiver-WY","Evanston-WY","Lander-WY","Rawlins-WY","Casper-WY","Cody-WY","Dubois-WY","JordanValley-OR","Rome-OR","Adrian-OR","Nyssa-OR","Owyhee-OR","Williston-ND","Dickinson-ND","Bowman-NE","Harrison-NE","Imperial-NE","Alliance-NE","Grant-NS","ELPaso-TX","Tribune-NV","SharonSprings-KS","Loeti-KS","Boise-ID","Meridian-ID","Nampa-ID","IdahoFalls-ID","Pocatello-ID","Caldwell-ID","Ketchum-ID","Chilly-ID","May-ID","Donnelly-ID","Buhl-ID","Kamaih-ID","Butte-MT"};
	private static List<String> stringList = new ArrayList<>(Arrays.asList(cityList));
	static display_temperatures temperature;
	private int[][] adj_matrix = null;
	
	public Dijkstras(String startcity, String endcity, display_temperatures temperature, int[][] adjacencymatrix) {
		int startVertex = 0; 
		int endVertex = 0; 
		this.temperature = temperature;
		this.adj_matrix = adjacencymatrix;
		
		for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].equals(startcity)) {
            	startVertex = i;
             //   break; // Exit the loop once the city is found
            }
            if(cityList[i].equals(endcity)){
            	endVertex = i;
            }
            if(startVertex != 0 && endVertex!= 0) {
            	break;
            } 
        }
		System.out.println("startVertex: "+ startVertex + "End Vertex: " + endVertex);
		dijkstra(adj_matrix, startVertex, endVertex);
	}
	
	
	private static void dijkstra(int[][] adjacencyMatrix, int startVertex, int destinationVertex) {
        int nVertices = adjacencyMatrix[0].length;
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        shortestDistances[startVertex] = 0;
        int[] parents = new int[nVertices];
        parents[startVertex] = NO_PARENT;

        for (int i = 1; i < nVertices; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;

            // Find the nearest vertex among the non-added vertices
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            if (nearestVertex == -1) {
                // No valid nearest vertex found, break the loop
                break;
            }

            added[nearestVertex] = true;

            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (vertexIndex != nearestVertex && !added[vertexIndex] && edgeDistance > 0
                        && edgeDistance < THRESHOLD && (shortestDistance + edgeDistance) < shortestDistances[vertexIndex]) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        printSolution(startVertex, destinationVertex, shortestDistances, parents, stringList);
    }

    private static void printSolution(int startVertex, int destinationVertex, int[] distances, int[] parents, List<String> stringList) {
        int nVertices = distances.length;
        System.out.print("\nDistance from " + stringList.get(startVertex) + " to " + stringList.get(destinationVertex) + " : ");

        if (distances[destinationVertex] == Integer.MAX_VALUE) {
            System.out.println("No path found from " + startVertex + " to " + destinationVertex);
        } else {
            System.out.print(distances[destinationVertex] + " miles\n\nThe Path is as follows: ");
            printPath(destinationVertex, parents, stringList);
            
        }
    }

    private static void printPath(int currentVertex, int[] parents, List<String> stringList) {
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents, stringList);
        System.out.print(stringList.get(currentVertex) + " -> ");
        
        temperature.fetch_display(" ", stringList.get(currentVertex), 1);
    }
}