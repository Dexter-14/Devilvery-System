package demo19505;

import base.*;
import java.util.*;


public class HighwayDemo extends Highway
{
	@Override
	public synchronized boolean hasCapacity()
	{
		if(c>=getCapacity())
		{
			return false;
		}
		else
		{
			return true;
		}

	}

	@Override
	public synchronized boolean add(Truck truck)
	{
		if(c < getCapacity())
		{
			hitruck.add(truck);
			c++;
			return true;
		}

		else
		{
			return false;
		}
	}

	@Override
	public synchronized void remove(Truck truck)
	{
		c--;
		hitruck.remove(truck);
	}

	private int c = 0;
	private ArrayList<Truck> hitruck = new ArrayList<>();
	
}
