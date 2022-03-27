package week8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;
import javax.swing.*;

public class GraphView extends JPanel
{
	private final Graph<? extends Displayable> graph;

	public GraphView(Graph<? extends Displayable> graph)
	{
		this.graph = graph;
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		List<? extends Displayable> vertices = graph.getVertices();
		for (int i = 0; i < graph.getSize(); i++)
		{
			int x = vertices.get(i).getX();
			int y = vertices.get(i).getY();
			String name = vertices.get(i).getName();
			graphics.fillOval(x - 8, y - 8, 16, 16); 
			graphics.drawString(name, x - 12, y - 12); 
		}
		for (int i = 0; i < graph.getSize(); i++)
		{
			List<Integer> neighbors = graph.getNeighbors(i);
			int x1 = graph.getVertex(i).getX();
			int y1 = graph.getVertex(i).getY();
			for (int vertex : neighbors)
			{
				int x2 = graph.getVertex(vertex).getX();
				int y2 = graph.getVertex(vertex).getY();
				graphics.drawLine(x1, y1, x2, y2); 
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}
}