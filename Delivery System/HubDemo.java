package demo19505;

import base.*;
import java.util.*;


public class HubDemo extends Hub
{

	public HubDemo(Location loc) 
	{
		super(loc);
	}

	protected void dfs(Hub hub, HashMap<Hub,Boolean> visited)
	{
		visited.put(hub,true);
		for(Highway highway : hub.getHighways())
		{
			if(!visited.getOrDefault(highway.getEnd(), false))
			{
				dfs(highway.getEnd() , visited);
			}
		}	
	}

	@Override
	public Highway getNextHighway(Hub last, Hub dest)
	{
		for(Highway highway : getHighways())
		{
			HashMap<Hub,Boolean> visited = new HashMap<>();
			visited.put(this,true);
			dfs(highway.getEnd() , visited);

			if(dest == null)
			{
				return null;
			}

			//if(visited.containskey(dest))

			if(visited.get(dest))
			{
				return highway;
			}
		}
		return null;
	}

	@Override
	public synchronized boolean add(Truck truck)
	{
		if(c<getCapacity())
		{
			listtruck.add(truck);
			c++;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void remove(Truck truck)
	{
		c--;
		listtruck.remove(truck);
	}

	@Override
	protected void processQ(int deltaT)
	{
		for(Truck lt : listtruck)
		{
			if(Network.getNearestHub((lt.getDest())) != this)
			{
				if(getNextHighway(this , Network.getNearestHub(lt.getDest())).add(lt))
				{
					this.remove(lt);
					lt.enter(getNextHighway(this , Network.getNearestHub(lt.getDest())));
				}
			}

		}


	}
	
	int c = 0;
	ArrayList<Truck> listtruck = new ArrayList<>();
}
