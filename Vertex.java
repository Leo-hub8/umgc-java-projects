package week8;

class Vertex implements Displayable
{
	private static final int INCREMENT = 100, MAX_X = 500;
	private static int nextX = INCREMENT, nextY = INCREMENT;
	private final String name;
	private final int x;
	private final int y;
	
	public Vertex(String name)
	{
		this.name = name;
		x = nextX;
		y = nextY;
		nextX += INCREMENT;
		if (nextX == MAX_X)
		{
			nextX = INCREMENT;
			nextY += INCREMENT;
		}
	}
	
	@Override
	public int getX() 
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}