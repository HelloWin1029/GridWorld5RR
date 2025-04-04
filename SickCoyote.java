import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class SickCoyote extends Actor
{
	private int lifetime;
	private static final int threshold = 10;
	
	public SickCoyote()
	{
		setColor(null);
		lifetime = threshold;	
	}
	
	public SickCoyote(int lifetime)
	{
		this.lifetime = lifetime;
	}
	
	public void act()
	{
		lifetime--;
		
		if (lifetime == 0)
		{
			Coyote coyote = new Coyote();
			coyote.putSelfInGrid(getGrid(), getLocation());
		}
	}
}
