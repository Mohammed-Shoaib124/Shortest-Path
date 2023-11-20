//package cities;

import java.io.IOException;
import java.util.*;

public class Main {
    static String[] cities = {"Mill City,NV", "Eureka,NV", "Wells,NV", "Howe,ID", "Jackpot,NV", "Montello,NV", "Owyhee,NV", "Eugene,OR", "Gresham,OR", "Hillsbboro,OR",
            "Bend,OR", "Beaverton,OR", "Medford,OR", "John day,OR", "Roseburg,OR", "Baker city,OR", "salem,OR", "Newport,OR", "Ashland,OR",
            "Riley,OR", "Seattle,WA", "Spokane,WA", "Tacoma,WA", "Vancouver,WA", "Bellevue,WA", "Kent,WA", "Leavenworth,WA", "Yakima,WA",
            "Richland,WA", "Pullman,WA", "WestWendover,NV", "LosAngeles,CA", "SanDiego,CA", "SanJose,CA", "SanFrancisco,CA", "Fresno,CA",
            "Sacramento,CA", "Long Beach,CA", "Redding,CA", "Bakersfield,CA", "Bishop,CA", "Irvine,CA", "Darwin,CA", "Bayswater,CA", "Arvin,CA",
            "Albuquerque,NM", "LasCruces,NM", "RioRancho,NM", "SantaFe,NM", "Roswell,NM", "SaltLakeCity,UT", "WestValleyCity,UT", "Provo,UT",
            "WestJordan,UT", "Sandy,UT", "Orem,UT", "Ogden,UT", "Missoula,MT", "GreatFalls,MT", "Bozeman,MT", "SilverBow,MT",
            "Helena,MT", "Kalispell,MT", "Almosa,CO", "cortez,CO", "Grand Junction,CO", "Pueblo,CO", "Lamar,CO", "Fort collins,CO", "Aspen,CO",
            "Telluride,CO", "Montrose,CO", "Prescott,AZ", "Buckeye,AZ", "Sedona,AZ", "Tucson,AZ", "Phoenix,AZ", "Chinle,AZ", "Kingman,AZ",
            "Laramie,WY", "Gillette,WY", "RockSprings,WY", "Sheridan,WY", "GreenRiver,WY", "Evanston,WY", "Lander,WY", "Rawlins,WY",
            "Casper,WY", "Gillette,WY", "Cody,WY", "Dubois,WY", "JordanValley,OR", "Rome,OR", "Adrian,OR", "Nyssa,OR", "Owyhee,OR", "Williston,ND",
            "Dickinson,ND", "Bowman,ND", "Harrison,NE", "Imperial,NE", "Alliance,NE", "Grant,NE", "ElPaso,TX", "Tribune,NV", "SharonSprings,KS",
            "Loeti,KS", "Boise,ID", "Meridian,ID", "Nampa,ID", "IdahoFalls,ID", "Pocatello,ID", "Caldwell,ID", "Ketchum,ID", "Chilly,ID",
            "May,ID", "Donnelly,ID", "Buhl,ID", "Kamaih,ID", "Butte,ID"};
    static Map<String, CityData> temperature;
    private static final int NO_PARENT = -1;

    public static int getTemp(String city){
        String formatedCity = city.toLowerCase().replaceAll("\\s", "").replaceAll(",", "-");
        if(temperature.get(formatedCity)!=null) {
            return temperature.get(formatedCity).temperature;
        }
        return 34;
    }

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private static void dijkstra(int[][] adjacencyMatrix,
                                 int startVertex, int endVertex) {
        int nVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++) {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++) {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        printSolution(startVertex, shortestDistances, parents, endVertex);
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private static void printSolution(int startVertex,
                                      int[] distances,
                                      int[] parents, int endVertex) {
        int nVertices = distances.length;

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++) {
            if (endVertex == vertexIndex) {
                System.out.print("PATH : ");
                printPath(vertexIndex, parents, endVertex);
                System.out.println();
                System.out.println("DISTANCE : " + distances[vertexIndex]);
                System.out.println("GASOLINE USAGE : " + Math.ceil(distances[vertexIndex]/20) + " Gallons.");
            }
        }
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void printPath(int currentVertex,
                                  int[] parents, int endVertex) {

        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents, endVertex);
        System.out.print(cities[currentVertex] + "(" + getTemp(cities[currentVertex]) + " F)");
        if (currentVertex != endVertex) {
            System.out.print(" ---> ");
        }
//        System.out.print(temperature.get(cities[currentVertex].toLowerCase().replaceAll("\\s", "")).temperature);
    }

    private static int findIndex(String[] cities, String city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].toLowerCase().replaceAll("\\s", "").contains(city.toLowerCase().replaceAll("\\s", ""))) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        String weatherCsvFilePath = "datasets/weather_avg.txt";
        String distanceCsvFilePath = "datasets/distance_matrics.txt";
        CityGraph map = new CityGraph();

        // Generate Graph
        map.createGraph(distanceCsvFilePath, weatherCsvFilePath);
        List<List<Integer>> distanceMatrix = map.getAdjacencyMatrix();
//        System.out.println(distanceMatrix.size()+" X " + distanceMatrix.get(0).size());
        int[][] adjMat = new int[distanceMatrix.size()][distanceMatrix.get(0).size()];
        for (int i = 0; i < distanceMatrix.size(); i++) {
            for (int j = 0; j < distanceMatrix.get(i).size(); j++) {
                adjMat[i][j] = distanceMatrix.get(i).get(j);
            }
        }
        temperature = map.getTemperature();

        // Path Algorithms...
        boolean travel = true;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Source city : ");
        String source = sc.nextLine();

        while(travel){
            System.out.print("Enter the Destination city : ");
            String destination = sc.nextLine();
            int sourceIndex = findIndex(cities, source);
            int destinationIndex = findIndex(cities, destination);
            dijkstra(adjMat, sourceIndex, destinationIndex);
            System.out.print("Do you want to travel to other city ? (Y/N) : ");
            String confirm = sc.nextLine();
            if(!confirm.trim().toLowerCase().equals("y")){
                travel = false;
            }else{
                source = destination;
            }
        }


    }
}
