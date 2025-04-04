import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Stone extends Rock
{
	private int lifetime;
	private static final int THRESHOLD = 3;
	
	public Stone(int lifetime)
	{
		setColor(null);
		this.lifetime = lifetime;
	}
	
	public Stone()
	{
		setColor(null);
		lifetime = (int) (Math.random() * 200) + 1;
	}
	
	public void act(){
		lifetime--;
		
		if (lifetime == 0)
		{
			Location loc = getLocation();
			Grid<Actor> gr = getGrid();
			Boulder boulder = new Boulder();
			boulder.putSelfInGrid(gr, loc);
		}
		else if (lifetime < THRESHOLD)
		{
			setColor(Color.GREEN);
		}
	}
}
