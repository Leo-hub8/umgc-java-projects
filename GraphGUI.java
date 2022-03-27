package week8;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

class GraphGUI extends JFrame
{
	private RectangularGraph graph = new RectangularGraph();
	private GraphView graphView = new GraphView(graph);
	private final JButton load = new JButton("Load Graph"), 
		depthFirst = new JButton("Depth First Search"),
		breadthFirst = new JButton("Breadth First Search"),
		connected =	new JButton("Check Connected"),
		cycles = new JButton("Check Cycles"),
		components = new JButton("Show Components");
	private final JTextField message = new JTextField(40);

	public GraphGUI()
	{
		super("Graph Searches and Characteristics");
		setSize(550, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 3, 20, 10));
		buttonPanel.add(load);
		buttonPanel.add(depthFirst);
		buttonPanel.add(breadthFirst);
		buttonPanel.add(connected);
		buttonPanel.add(cycles);
		buttonPanel.add(components);
		graphView.setBorder(new TitledBorder("Undirected Graph"));
		setLayout(new FlowLayout());
		add(graphView);
		add(buttonPanel);
		add(message);
		message.setEditable(false);
		load.addActionListener(loadFile);
		depthFirst.addActionListener
			(event -> message.setText("Depth first: " + graph.depthFirst(0)));
		breadthFirst.addActionListener
			(event -> message.setText("Breadth first: " + graph.breadthFirst(0)));
		connected.addActionListener
			(event ->	message.setText("The graph is " + 
			(graph.isConnected() ? "" : "not ") + "connected"));
		cycles.addActionListener
			(event ->	message.setText("The graph " + 
			(graph.hasCycles() ? "has " : "does not have ") + "cycles"));
		components.addActionListener
			(event -> message.setText("Connected components = " +
			graph.connectedComponents()));
	}

	private final ActionListener loadFile = event ->
	{
		JFileChooser choice = new JFileChooser(new File("."));
		int option = choice.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) 
			try
			{			
				Scanner input = new Scanner(choice.getSelectedFile());
				while (input.hasNextLine())
				{
					String edgeString = input.nextLine();
					String[] edge = edgeString.split(" ");
					graph.addEdge(edge[0], edge[1]);
					graphView.repaint();
				}
			}
			catch(FileNotFoundException exception)
			{
			}
	};
	
	public static void main(String[] args) throws FileNotFoundException
	{
		GraphGUI window = new GraphGUI();
		window.setVisible(true);
	}
}
