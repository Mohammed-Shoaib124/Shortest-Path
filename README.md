# TRAVEL PLANNER

## Overview
Welcome to the Travel Planner, a Java-based application designed to enhance your travel experience by providing comprehensive insights into weather conditions, optimal routes, and other crucial factors. Whether you're planning a road trip, business travel, or a leisurely journey, this program aims to make your travel decisions informed and efficient.

## Installation
### Prerequisites
Java Development Kit (JDK) installed on your machine.
CSV files containing weather, distance, and sea level data.
## File Paths: 
Adjust the following file paths in the Main.java file to point to your CSV files:
weather.csv  FilePath
distance.csv  FilePath
seaLevel.csv  FilePath
distance_matrix _without_citynames.csv
Also add the path of new_distance_matrics.csv file to the possible_paths_1.java 

## Input
- Starting city and state: [City-State]
- Destination city and state: [City-State]
- Date and time of commencement: yyyy-MM-dd HH:mm
## User Options :
1. Display All Possible Paths:
  a. Prompts for a travel range (in miles).
  b. Displays all available paths within the specified range.
2. Run Dijkstra's Algorithm:
  a. Finds the shortest path between the starting and destination cities         using Dijkstra's algorithm.
3. Run Bellman-Ford Algorithm:
  a. Determines the shortest path considering negative edge weights using        Bellman-Ford algorithm

## Steps
- Extract all the files of the project 
- Then set the paths in the respective files mentioned above
- Run Main.java
- Enter the inputs in the formats specified
## Output Format 
The Travel Planner provides various output formats depending on the user's selection. Here are the details for each option:

## 1. Display All Possible Paths
After specifying the desired travel range, the program will display all available paths within the specified distance.
The output will consist of a list of paths, where each path is represented as a sequence of cities. The total distance of each path will also be indicated.

![Snap1](https://github.com/Mohammed-Shoaib124/Shortest-Path/assets/151399787/6affa7bb-b2d1-43a5-83af-95978c0ebd95)


![Snap1b](https://github.com/Mohammed-Shoaib124/Shortest-Path/assets/151399787/3b8d5bcb-19fe-4198-b964-ad13010444b4)

## 2 Run Dijkstra's Algorithm
When choosing Dijkstra's Algorithm, the program will output the shortest path between the specified starting and destination cities.
The output will include the path itself, along with the total distance of the selected route.

![Snap2final](https://github.com/Mohammed-Shoaib124/Shortest-Path/assets/151399787/ee33daeb-c69e-44a6-924e-a32dcc7dd366)

## 3. Run Bellman-Ford Algorithm
Running the Bellman-Ford Algorithm will yield the shortest path considering negative edge weights (if any).
The output will display the optimized path and the total distance, considering the modified weights.
![snap3](https://github.com/Mohammed-Shoaib124/Shortest-Path/assets/151399787/529ec1c2-edc4-461c-8509-bb524fa69040)


## SourceCode OnlineGDB
[SourceCode](https://onlinegdb.com/XfIqGzbXI)






