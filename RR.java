import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class RR extends Critter
{
	public RR()
	{
		setColor(null);
		setDirection(Location.NORTH);
	}
	
	public void processActors(){}
	
	public boolean isThreeAwayValid(int dir)
	{
		Location loc = getLocation();
		
		for (int i = 1; i <= 2; i++){
			loc = loc.getAdjacentLocation(dir);
			if (!getGrid().isValid(loc) || getGrid().get(loc) != null)
				return false;
		}
		loc = loc.getAdjacentLocation(dir);
		if (getGrid().isValid(loc)){
			if (getGrid().get(loc) == null) return true;
			else{
				Actor actor = getGrid().get(loc);
				if (actor instanceof Boulder || actor instanceof Coyote) return true;
				return false;
			}
		}
		return false;
	}
	
	public ArrayList<Location> getMoveLocations(){
		ArrayList<Location> locs = new ArrayList<Location>();
		
		for (int i = 0; i < 360; i += 45){
			if (isThreeAwayValid(i)){
				Location loc = getLocation().getAdjacentLocation(i).getAdjacentLocation(i).getAdjacentLocation(i);
				locs.add(loc);	
			}
		}
		return locs;
	}
	
	public void makeMove(Location loc){
		int dir = getLocation.getDirectionToward(loc);
		if (!loc.equals(getLocation())){
			if (getGrid().get(loc != null){
				Actor actor = getGrid().get(loc);
				
				if (actor instanceof Boulder){
					Kaboom kaboom = new Kaboom();
					kaboom.putSelfInGrid(getGrid(), loc);
					
					removeSelfFromGrid();
					return;
				}
				else if (actor instanceof Coyote){
					ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(loc);
					if (locs.size() > 0){
						int n = (int) (Math.random() * locs.size());
						Location next = locs.get(n);
						SickCoyote sickcoyote = new SickCoyote();
						sickcoyote.putSelfInGrid(getGrid(), next);
					}
				}
			}
			moveTo(loc);
		}
	}
}
