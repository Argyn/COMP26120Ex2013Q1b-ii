import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

public class Graph {
	private ArrayList<Node> vertices;
	private ArrayList<Edge> edges;
	private HashMap<Integer, Node> verticesMap;
	private HashMap<Integer, Boolean> explored;

	public Graph() {
		vertices = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		verticesMap = new HashMap<Integer, Node>();
		explored = new HashMap<Integer, Boolean>();
	}

	public void addVertex(int index, int cost) {
		Node node = new Node(index, cost);
		vertices.add(node);
		verticesMap.put(index, node);
	}

	public void addEdge(int source, int target, int distance) {
		Edge e = new Edge(verticesMap.get(source), verticesMap.get(target), distance);
		edges.add(e);
		e = new Edge(verticesMap.get(target), verticesMap.get(source), distance);
		edges.add(e);

	}

	public Node getVertex(int index) {
		return verticesMap.get(index);
	}

	public void refreshExplored() {
		Set<Integer> keys = verticesMap.keySet();
		for(Integer index : keys) {
			explored.put(index, false);
		}	
	}

	public void setExplored(int index) {
		explored.put(index, true);
	}

	public Boolean isExplored(int index) {
		return explored.get(index);
	}


}