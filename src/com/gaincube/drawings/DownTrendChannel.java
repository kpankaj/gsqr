

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.gaincube.util.GCConstants;

/**
 *
 * @author Pankaj
 */
public class DownTrendChannel {

    private double slope;
    private boolean active;
    private DownTrend mainTrend;
    private DownTrend upperTrend;
    private double higherX;
    private double upperX;
    private double upperY;
    private int channelPartCount;
    private ArrayList<DownTrend> trends;
    private int noOfTrends;
    private int noOfMatchingPtsForChannel;

    public DownTrendChannel(DownTrend mainTrend,double higherX, double upperX,double upperY,int channelPartCount)
    {
        trends =  new ArrayList<DownTrend>();
        this.slope = mainTrend.getSlope();
        this.mainTrend = mainTrend;
        this.higherX = higherX;
        this.upperX = upperX;
        this.upperY = upperY;
        this.channelPartCount = 10;
        this.noOfTrends = 5;
        mainTrend.setDrawFlag(true);
        trends.add(mainTrend);
        createDownTrendChannel();
    }

    public void createDownTrendChannel()
    {
        double channelRange = (upperX-higherX)/channelPartCount;

        upperTrend = mainTrend;
        
        DownTrend trend = null;
        
        
        int j=0;
        int p=1;
        boolean moveUpFlag = true;

        if(mainTrend.isActive())
        {
              while(moveUpFlag)
              {
            	    if(p>GCConstants.NoOfDownTrendChannels)
            	    	break;
            	    
                    //trend= new UpTrend(upperX,(mainTrend.getYPos()-(channelRange*p)),mainTrend.getSlope(), 
                    //		mainTrend.getCurrentY(),mainTrend.getYSize(),mainTrend.getXSize(), 
                    //		mainTrend.getyOffset(),mainTrend.getyOffset(), mainTrend.getTrendReversalPoints());
                    
                    trend= new DownTrend((upperX+(channelRange*p)),upperY,mainTrend.getSlope(),
                            mainTrend.getCurrentY(),mainTrend.getYSize(),mainTrend.getXSize(),mainTrend.getTrendReversalPoints(),Color.RED);

                    if((trend.getMatchPointsCount()==0))
                    {
                        j++;
                        if(j==100)
                            moveUpFlag = false;
                    }
                    else
                    {
                        j = 0;
                        //trends.add(trend);
                        //n++;
                    }
                    trends.add(trend);
                    p++;
                }

              setTrendChannelProperties();

        }

      /*  if(mainTrend.isActive())
        {

        		for(int a=0;a<GCConstants.NoOfDownTrendChannels;a++)
        		{
            	  	trend= new DownTrend((upperX+(channelRange*a)),upperY,mainTrend.getSlope(),
                          mainTrend.getCurrentY(),mainTrend.getYSize(),mainTrend.getXSize(),mainTrend.getTrendReversalPoints(),Color.red);
                   
                    if((trend.getMatchPointsCount()>0))
                    {
                        trends.add(trend);
                    }
                }
           	    
                setTrendChannelProperties();
            }*/
    }

      
    public void drawTrendChannel(Graphics2D g)
    {
    	//mainTrend.drawTrend(g, Color.RED,true);
    		
    	 for(int i=0;i<trends.size();i++)
         {
    		 DownTrend downTrend = ((DownTrend)(this.trends.get(i)));

//             if((i==(trends.size()-1)))
//            	 downTrend.drawTrend(g, Color.RED,true);
//             else
            	 downTrend.drawTrend(g, Color.RED,false);
         }
    }

    
    public int setMPtsCountForTrendChannel()
    {
        int countMPt = mainTrend.getMatchPointsCount();

        System.err.println("MNP"+trends.size());
        
        for(int i=0;i<trends.size();i++)
        {
        	DownTrend downTrend = (DownTrend)(this.trends.get(i));
            countMPt = countMPt + downTrend.getMatchPointsCount();
        }

        this.noOfMatchingPtsForChannel = countMPt;
        
        return noOfMatchingPtsForChannel;
        
    }
    
    public int getMPtsCountForTrendChannel()
    {
        return noOfMatchingPtsForChannel;
    }
    
    

    public int getNoOfMatchingPtsForChannel() {
		return noOfMatchingPtsForChannel;
	}


	public void setNoOfMatchingPtsForChannel(int noOfMatchingPtsForChannel) {
		this.noOfMatchingPtsForChannel = noOfMatchingPtsForChannel;
	}


	public int getNoOfTrends() {
		return noOfTrends;
	}


	public void setNoOfTrends(int noOfTrends) {
		this.noOfTrends = noOfTrends;
	}


	public ArrayList<DownTrend> getTrends() {
		return trends;
	}


	public void setTrends(ArrayList<DownTrend> trends) {
		this.trends = trends;
	}


	public double getUpperX() {
		return upperX;
	}


	public void setUpperX(double upperX) {
		this.upperX = upperX;
	}


	private void setTrendChannelProperties()
    {
        if(this.slope>0)
        {
            active = true;
            setMPtsCountForTrendChannel();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public double getHigherX() {
        return higherX;
    }

    public void setHigherX(double higherX) {
        this.higherX = higherX;
    }

    public DownTrend getMainTrend() {
        return mainTrend;
    }

    public void setMainTrend(DownTrend mainTrend) {
        this.mainTrend = mainTrend;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public DownTrend getUpperTrend() {
        return upperTrend;
    }

    public void setUpperTrend(DownTrend upperTrend) {
        this.upperTrend = upperTrend;
    }

    public double getUpperY() {
        return upperY;
    }

    public void setUpperY(double upperY) {
        this.upperY = upperY;
    }

   
    
}


