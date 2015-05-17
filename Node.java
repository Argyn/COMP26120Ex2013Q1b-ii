import java.util.ArrayList;

public class Node {
	public int index;
	public int cost;

	public ArrayList<Edge> adjEdges;

	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
		adjEdges = new ArrayList<Edge>();
	}

	public void addEdge(Edge e) {
		adjEdges.add(e);
	}

}