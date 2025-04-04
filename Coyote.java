import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class Coyote extends Critter
{
	private int numStep;
	private int sleepCount;
	private boolean walkedIntoWall;
	
	public Coyote()
	{
		numStep = 0;
		sleepCount = 0;
		walkedIntoWall = false;
		setColor(null);
		int dir = (int) (Math.random() * 8) * 45;
		setDirection(dir);
	}
	
	public void processActors(ArrayList<Actor> actors){ }
    
    public ArrayList<Location> getMoveLocations()
    {
		ArrayList<Location> locs = new ArrayList<Location>();
		Location next = getLocation().getAdjacentLocation(getDirection());
		if (getGrid().isValid(next)) locs.add(next);
		return locs;
	}
	
	public Location getEmptyAdjacentLocationsAtRandom(){
		ArrayList<Location> nextLocs = getGrid().getEmptyAdjacentLocations(getLocation());
		if (nextLocs.size() == 0)
			return getLocation();
			
		int n = (int) (Math.random() * nextLocs.size());
		return nextLocs.get(n);
	}
	
	public void makeMove(Location loc)
    {
		if (sleepCount > 0){ // Coyote is in sleeping mode
			if (sleepCount == 5){ // Done sleeping
				numStep = 0;
				sleepCount = 0;
				
				if (!walkedIntoWall){
					Location locForStone = getEmptyAdjacentLocationsAtRandom();
					if (locForStone != getLocation()){
						Stone stone = new Stone();
						stone.putSelfInGrid(getGrid(), locForStone);
					}
				}
				
				Location next = getEmptyAdjacentLocationsAtRandom();
				if (next == getLocation()) return;
				
				setDirection(getLocation().getDirectionToward(next));
				walkedIntoWall = false;
			}
			else sleepCount++; // Keep sleeping
			return;
		}
		
		// Invalid next location so just sleep
		if (loc.equals(getLocation())){
			sleepCount = 1;
			walkedIntoWall = true;
		}
		else{
			if (getGrid().get(loc) != null){ // There is a actor in front of coyote
				if (getGrid().get(loc) instanceof Boulder){ // The actor is a boulder, coyote is removed
					Kaboom kaboom = new Kaboom();
					kaboom.putSelfInGrid(getGrid(), loc);
					removeSelfFromGrid();
					return;
				}
				else sleepCount = 1; // The actor is not a boulder, coyote sleeps
			}
			else{
				if (numStep < 5){ // Haven't walked 5 steps in a row
					super.makeMove(loc);
					numStep++;
				}
				else sleepCount = 1; // Have walked 5 steps in a row, so coyote sleeps
			}
			
		}
    }
}
