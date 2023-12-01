package pkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.lang.*;
import java.util.*;

class Graph {
    private int V;
    private List<int[]> edges;
    private int sourceVertex = 0; 
    private int destination = 0;
    private int[][] adjMat = null;
    private String startcity = null;
    private String endcity = null;
    String[] cityList = {"MillCity-NV","Eureka-NV","Wells-NV","Howe-ID","Jackpot-NV","Montello-NV","Owyhee-NV","Eugene-OR","Gresham-OR","Hillsbboro-OR","Bend-OR","Beaverton-OR","Medford-OR","Johnday-OR","Roseburg-OR","Bakercity-OR","Salem-OR","Newport-OR","Ashland-OR","Riley-OR","Seattle-WA","Spokane-WA","Tacoma-WA","Vancouver-WA","Bellevue-WA","Kent-WA","Leavenworth-WA","Yakima-WA","Richland-WA","Pullman-WA","WestWendover-NV","LosAngeles-CA","SanDiego-CA","SanJose-CA","SanFrancisco-CA","Fresno-CA","Sacramento-CA","LongBeach-CA","Redding-CA","Bakersfield-CA","Bishop-CA","Irvine-CA","Darwin-CA","Bayswater-CA","Arvin-CA","Albuquerque-NM","LasCruces-NM","RioRancho-NM","SantaFe-NM","Roswell-NM","SaltLakeCity-UT","WestValleyCity-UT","Provo-UT","WestJordan-UT","Sandy-UT","Orem-UT","Ogden-UT","Missoula-MT","GreatFalls-MT","Bozeman-MT","Butte-SilverBow-MT","Helena-MT","Kalispell-MT","Almosa-CO","cortez-CO","GrandJunction-CO","Pueblo-CO","Lamar-CO","Fortcollins-CO","Aspen-CO","Telluride-CO","Montrose-CO","Prescott-AZ","Buckeye-AZ","Sedona-AZ","Tucson-AZ","Phoenix-AZ","Chinle-AZ","Kingman-AZ","Laramie-WY","Gillette-WY","RockSprings-WY","Sheridan-WY","GreenRiver-WY","Evanston-WY","Lander-WY","Rawlins-WY","Casper-WY","Cody-WY","Dubois-WY","JordanValley-OR","Rome-OR","Adrian-OR","Nyssa-OR","Owyhee-OR","Williston-ND","Dickinson-ND","Bowman-NE","Harrison-NE","Imperial-NE","Alliance-NE","Grant-NS","ELPaso-TX","Tribune-NV","SharonSprings-KS","Loeti-KS","Boise-ID","Meridian-ID","Nampa-ID","IdahoFalls-ID","Pocatello-ID","Caldwell-ID","Ketchum-ID","Chilly-ID","May-ID","Donnelly-ID","Buhl-ID","Kamaih-ID","Butte-MT"};

    public Graph(int vertices, int[][] adjacency_matrix, String startcity, String endcity) {
    	
        V = vertices;
        edges = new ArrayList<>();
        this.adjMat = adjacency_matrix;
        this.startcity = startcity;
        this.endcity = endcity;
        
    }

    public void addEdge(int u, int v, int w) {
        edges.add(new int[]{u, v, w});
    }
    
    public void find_and_addedges() {
    	
    	for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].equals(startcity)) {
            	sourceVertex = i;
             //   break; // Exit the loop once the city is found
            }
            if(cityList[i].equals(endcity)){
            	destination = i;
            }
            if(sourceVertex != 0 && destination!= 0) {
            	break;
            } 
        }
    	
        for(int i=0;i<adjMat.length;i++){
        	for(int j=0;j<adjMat[i].length;j++){
		        if(adjMat[i][j] != 0 && !(sourceVertex == i && destination == j ||sourceVertex == j && destination == i)){
                    if(adjMat[i][j]<250){
                        addEdge(i, j, adjMat[i][j]);
                    }
                    
		        }
		    }
		    
		}
        bellmanFord(sourceVertex,destination);
    }

    public void bellmanFord(int src,int dest) {
        int[] dist = new int[V];
        int[] pred = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Arrays.fill(pred, -1);

        // Relax all edges |V| - 1 times
        for (int i = 0; i < V ; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v] ) {
                    dist[v] = dist[u] + weight;
                    pred[v] = u;
                }
            }
        }

        // Check for negative weight cycles
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Print the shortest paths
        System.out.println("Shortest paths from the source vertex:");
 
            printPath(src, dest, pred);
            System.out.println(" (Distance: " + dist[dest] + ")");
    }

    private void printPath(int src, int dest, int[] pred) {
        List<Integer> path = new ArrayList<>();
        int current = dest;
        while (current != -1) {
            path.add(0, current);
            current = pred[current];
        }
        System.out.print("Path from " + src + " to " + dest + ": " + path);
    }
}

//public class Main {
//    public static void main(String[] args) {
//		int[][] adjMat = new int[120][120];
//        int k=0;
//        try (BufferedReader br = new BufferedReader(new FileReader("distance2.csv"))) {
//            String line;
//
//            // Read the CSV file line by line
//            while ((line = br.readLine()) != null) {
//                // Split the line into columns using a comma as the delimiter
//                String[] columns = line.split(",");
//                for(int j=0;j<columns.length;j++){
//                    adjMat[k][j] = Integer.parseInt(columns[j]);
//                }
//                k++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Graph g = new Graph(120);
//        int sourceVertex = 0;
//        int destination = 2;
//        
//		for(int i=0;i<adjMat.length;i++){
//		    for(int j=0;j<adjMat[i].length;j++){
//		        if(adjMat[i][j] != 0 && !(sourceVertex == i && destination == j ||sourceVertex == j && destination == i)){
//                    if(adjMat[i][j]<250){
//                        g.addEdge(i, j, adjMat[i][j]);
//                    }
//                    
//		        }
//		    }
//		    
//		}
//        
//
//        
//        g.bellmanFord(sourceVertex,destination);
//    }
//}