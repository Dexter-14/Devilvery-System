package demo19505;

import base.*;

public class TruckDemo extends Truck 
{
    Highway currHighway;
    int covered;
    int time = 0;
    int Length;
    Hub lastHub;
    boolean status;
    String state = "start";


    public Hub getLastHub() 
    {
        return lastHub;
    };

    public void enter(Highway hwy) 
    {
        if(hwy == null) {return; }

        this.state = "moving";
        this.currHighway = hwy;
        lastHub = hwy.getStart();
        covered = 0;
        int x1 = currHighway.getStart().getLoc().getX();
        int x2 = currHighway.getEnd().getLoc().getX();
        int y1 = currHighway.getStart().getLoc().getY();
        int y2 = currHighway.getEnd().getLoc().getY();

        Length = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    };

    @Override
    public String getTruckName() 
    {
        return "Truck19505";
    }

    @Override
    protected void update(int deltaT) 
    {
        if (state.matches("start")) 
        {
            Hub nearestHub = NetworkDemo.getNearestHub(getLoc());

            setLoc(nearestHub.getLoc());
            if (nearestHub.add(this)) 
            {
                state = "OnHub";
            }
        }

        else if (!state.matches("OnHighway") && time > getStartTime()  && currHighway != null) 
        {
            int x1 = currHighway.getStart().getLoc().getX();
            int y1 = currHighway.getStart().getLoc().getY();
            int x2 = currHighway.getEnd().getLoc().getX();
            int y2 = currHighway.getEnd().getLoc().getY();
            
            int speed;
            speed = currHighway.getMaxSpeed();
            int distance; 
            distance = speed*deltaT/500;

            double hypo = Math.sqrt(Math.pow(y2 - y1 , 2) + Math.pow(x2 - x1 , 2));
            double dX = distance*(x2 - x1)/hypo;
            double dY = distance*(y2 - y1)/hypo;
            covered =  covered + distance;

            if (covered >= Length) 
            {
                if (currHighway.getEnd().add(this)) 
                {
                    currHighway.remove(this);
                    state = "OnHub";
                } 
                else 
                {
                    state = "OnHighway";
                }
                setLoc(currHighway.getEnd().getLoc());
            } 
            else 
            {
                Location loc = new Location(getLoc().getX() + ((int) dX), getLoc().getY() + ((int) dY));
                setLoc(loc);
            }
        }

        else if (getLoc().getX() == Network.getNearestHub(getDest()).getLoc().getX() && getLoc().getY() == Network.getNearestHub(getDest()).getLoc().getY()) 
        {
            setLoc(getDest());
        } 

        else if (state.matches("OnHighway")) 
        {
            if (currHighway.getEnd().add(this)) 
            {
                currHighway.remove(this);
                state = "OnHub";
            }
        } 

        time = time + deltaT;
    };

}
