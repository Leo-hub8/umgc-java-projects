package week8;

import java.util.*;

class Graph<V>
{
	private enum Marks {UNDISCOVERED, DISCOVERED}; 
	private List<V> vertices = new ArrayList();
	private List<List<Integer>> edges = new ArrayList();
	private Marks[] marks;
	private boolean cycle;

	public V getVertex(int i)
	{
		return vertices.get(i);
	}

	public int getIndex(Vertex vertex)
	{
		return vertices.indexOf(vertex);
	}

	public List<V> getVertices()
	{
		return vertices;
	}

	public List<Integer> getNeighbors(int i)
	{
		return edges.get(i);
	}

	public int getSize()
	{
		return vertices.size();
	}

	public void addVertex(V vertex)
	{
		vertices.add(vertex);
		edges.add(new ArrayList());
	}

	public void addEdge(int left, int right)
	{
		edges.get(left).add(right);
		edges.get(right).add(left);
	}

	public ArrayList<V> depthFirst(int start)
	{
		ArrayList<V> list = new ArrayList();
		marks = new Marks[getSize()];
		for (int i = 0; i < marks.length; i++)
			marks[i] = Marks.UNDISCOVERED;
		cycle = false;
		dfs(start, start, list);
		return list;
	}

	private void dfs(int previous, int current, ArrayList<V> list)
	{
		if (marks[current] == Marks.DISCOVERED)
		{
			cycle = true;
			return;
		}
		marks[current] = Marks.DISCOVERED;
		list.add(vertices.get(current));
		for (int next = 0; next < getSize(); next++)
			if (next != previous && edges.get(current).contains(next))
				dfs(current, next, list);
	}

	public ArrayList<V> breadthFirst(int start)
	{
		ArrayList<V> list = new ArrayList();
		Queue<Integer> queue = new LinkedList<Integer>();
		int current = start;
		for (int i = 0; i < marks.length; i++)
			marks[i] = Marks.UNDISCOVERED;
		queue.offer(start);
		while (!queue.isEmpty())
		{
			current = queue.remove();
			marks[current] = Marks.DISCOVERED;
			list.add(vertices.get(current));
			for (int next = 0; next < getSize(); next++)
				if (edges.get(current).contains(next) && 
					marks[next] != Marks.DISCOVERED)
					queue.offer(next);
		}
		return list;
	}

	public boolean isConnected()
	{
		depthFirst(0);
		for (Marks mark : marks)
			if (mark == Marks.UNDISCOVERED)
				return false;
		return true;
	}

	boolean hasCycles()
	{
		for (int vertex = 0; vertex < getSize(); vertex++)
		{
			depthFirst(vertex);
			if (cycle)
				return true;
		}
		return false;
	}
}