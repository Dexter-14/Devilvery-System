package demo19505;

import base.*;
import java.util.*;

public class FactoryDemo extends Factory
{
	@Override
	public Highway createHighway()
	{
			HighwayDemo highway = new HighwayDemo();
			return highway;
	}

	@Override
	public Hub createHub(Location location)
	{
		HubDemo hub = new HubDemo(location);
	    return hub;

	}

	@Override

	public Network createNetwork()
	{
		NetworkDemo network = new NetworkDemo();
		return network;
	}

	@Override

	public Truck createTruck()
	{
		TruckDemo truck = new TruckDemo();
		return truck;
	}

}
