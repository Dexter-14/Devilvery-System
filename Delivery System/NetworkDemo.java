package demo19505;

import base.*;
import java.util.*;

public class NetworkDemo extends Network
{
	ArrayList<Hub> arrhub = new ArrayList<>();
	ArrayList<Highway> arrhighway = new ArrayList<>();
	ArrayList<Truck> arrtruck = new ArrayList<>();

	@Override
	public void add(Hub hub)
	{
		arrhub.add(hub);
	}

	@Override
	public void add(Highway hwy)
	{
		arrhighway.add(hwy);
	}

	@Override
	public void add(Truck truck)
	{
		arrtruck.add(truck);
	}

	@Override
	public void start()
	{
		for(Hub h : arrhub)
		{
			h.start();
		}

		for(Truck hi: arrtruck)
		{
			hi.start();
		}

	}

	@Override
	public void redisplay(Display disp)
	{
		for(Hub h : arrhub)
		{
			h.draw(disp);
		}

		for(Highway hi : arrhighway)
		{
			hi.draw(disp);

		}

		for(Truck t : arrtruck)
		{
			t.draw(disp);
		}

	}
	//int min = Integer.MAX_VALUE;

	@Override
	protected Hub findNearestHubForLoc(Location loc)
	{
		ArrayList<Integer>dist = new ArrayList<>();
		for(Hub h: arrhub)
		{	
			dist.add(h.getLoc().distSqrd(loc));
		}
		return arrhub.get(dist.indexOf(Collections.min(dist)));
	}
}
