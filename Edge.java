public class Edge {
	public Node node1;
	public Node node2;
	public int distance;

	public Edge(Node node1, Node node2, int distance) {		
		this.node1 = node1;
		this.node2 = node2;
		this.distance = distance;
		node1.addEdge(this);
	}
}