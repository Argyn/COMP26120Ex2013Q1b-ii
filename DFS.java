import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class DFS {
	

	public static ArrayList<LinkedList<Integer>> pathes;
	public static ArrayList<Integer> costs;

	public static Graph readGraph(File file) throws IOException {
		Graph graph = new Graph();

		BufferedReader input = new BufferedReader(new FileReader(file));
		String inputLine;

		Pattern nodeP = Pattern.compile("NODE\\s(\\d+)\\s(\\d+)");
		Pattern edgeP = Pattern.compile("EDGE\\s(\\d+)\\s(\\d+)\\s(\\d+)");

		Matcher matcherNode, matcherEdge;

		while((inputLine=input.readLine())!=null) {
			matcherNode = nodeP.matcher(inputLine);
			matcherEdge = edgeP.matcher(inputLine);


			if(matcherEdge.find()) {
				int v1 = Integer.parseInt(matcherEdge.group(1));
				int v2 = Integer.parseInt(matcherEdge.group(2));
				int distance = Integer.parseInt(matcherEdge.group(3));

				graph.addEdge(v1, v2, distance);
			} else if(matcherNode.find()) {
				int value = Integer.parseInt(matcherNode.group(1));
				int cost = Integer.parseInt(matcherNode.group(2));
				graph.addVertex(value, cost);
			}
		}

		return graph;
	}

	public static void dfs(Graph graph, int source, int target, int days, int cost, int start, LinkedList<Integer> path) {
		Node startNode = graph.getVertex(source);
		
		path.add(startNode.index);

		if(target==source) {
			System.out.println("Found solution!!");
			pathes.add(path);
			costs.add(cost);

			return;
		}

		

		for(Edge e : startNode.adjEdges) {
			if(e.distance<=14 && !path.contains(e.node2.index)) {
				int newDays, newCost;
				if(e.distance+days>=14 && source!=start) {
					System.out.println("Stay overnight");
					newCost = cost + startNode.cost;
					newDays = e.distance;
				} else {
					System.out.println("Continue");
					newDays = days+e.distance;
					newCost = cost;
				}



				System.out.println("visiting "+e.node2.index);
				System.out.println("Days: "+newDays);
				System.out.println("Cost: "+newCost);
				//System.out.println(" Calling Edge "+e.node2.index);

				LinkedList<Integer> newPath =  (LinkedList<Integer>)path.clone();

				dfs(graph, e.node2.index, target, newDays, newCost, start, newPath);
			}
		}
				
	}

	public static void main(String[] args) throws IOException {

		Graph graph = readGraph(new File(args[0]));

		graph.refreshExplored();

		costs = new ArrayList<Integer>();
		pathes = new ArrayList<LinkedList<Integer>>();

		dfs(graph, 1, 8, 0, 0, 1, new LinkedList<Integer>());

		System.out.println("Possible pathes");
		int counter = 0;
		for(LinkedList<Integer> path : pathes) {
			while(path.peek()!=null) {
				System.out.print(path.poll());
				if(path.peek()!=null)
					System.out.print(" --> ");
			}
			System.out.println();
			System.out.println("Total cost:"+costs.get(counter++));
		}
	}	
}