import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Boulder extends Actor
{
	private int lifetime;
	private static final int THRESHOLD = 3;
	
	public Boulder(int lifetime)
	{
		setColor(null);
		this.lifetime = lifetime;
	}
	
	public Boulder()
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
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(gr, loc);
		}
		else if (lifetime < THRESHOLD)
		{
			setColor(Color.RED);
		}
	}
}
