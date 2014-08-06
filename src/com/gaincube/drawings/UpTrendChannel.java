

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import com.gaincube.util.GCConstants;
import com.gaincube.util.GCUpTMatchingPtComparator;

/**
 *
 * @author Pankaj
 */
public class UpTrendChannel {

    private double slope;
    private boolean active;
    private UpTrend mainTrend;
    private UpTrend upperTrend;
    private double lowerY;
    private double upperX;
    private double upperY;
    private ArrayList<UpTrend> trends;
    
    private int noOfMatchingPtsForChannel;
   

    public UpTrendChannel(UpTrend mainTrend,double lowerY, double upperX,double upperY,int channelPartCount,float xOffset,float yOffset)
    {
        trends =  new ArrayList<UpTrend>();
        
        this.slope = mainTrend.getSlope();
        this.mainTrend = mainTrend;
        this.lowerY = lowerY;
        this.upperX = upperX;
        this.upperY = upperY;
        mainTrend.setDrawFlag(true);
        trends.add(mainTrend);
        
        createTrendChannel();
    }


    private void createTrendChannel()
    {
        upperTrend = mainTrend;

        UpTrend trend = null;

        int p=1;
      
        int j =0;
        
        	if(mainTrend.isActive())
        	{
              	while(true)
              	{
            	    if(p>GCConstants.NoOfUpTrendChannels)
            	    	break;
            	    //channelRange*p
                    trend= new UpTrend(upperX,(mainTrend.getYPos()-p*GCConstants.yOffSet),mainTrend.getSlope(), 
                    		mainTrend.getCurrentY(),mainTrend.getYSize(),mainTrend.getXSize(), 
                    		mainTrend.getyOffset(),mainTrend.getyOffset(), mainTrend.getTrendReversalPoints());

                    if((trend.getMatchPointsCount()>2))
                    {
                        if(mainTrend.getMatchPointsCount()!=trend.getMatchPointsCount())
                        {
                        	trends.add(trend);
                        }
                        j=0;
                    }
                    else
                    	j++;
                    
                    
                    if(j>50)
                    	break;
                    
                    p++;
                }

              	p=1;
              	j =0;
              	
                while(true)
                {
                	 if(p>GCConstants.NoOfDownTrendChannels)
             	    	break;
                	
                	 trend= new UpTrend(upperX,(mainTrend.getYPos()+p*GCConstants.yOffSet),mainTrend.getSlope(), 
                     		mainTrend.getCurrentY(),mainTrend.getYSize(),mainTrend.getXSize(), 
                     		mainTrend.getyOffset(),mainTrend.getyOffset(), mainTrend.getTrendReversalPoints());

                     if((trend.getMatchPointsCount()>2))
                     {
                    	 if(mainTrend.getMatchPointsCount()!=trend.getMatchPointsCount())
                         {
                         	trends.add(trend);
                         
                         }
                    	 j=0;
                     }
                     else
                     	j++;
                     
                     if(j>50)
                     	break;
                     
                     p++;
                   
                }
                setTrendChannelProperties();
                
                Collections.sort(trends,new GCUpTMatchingPtComparator());
               
                //if(noOfMatchingPtsForChannel>3)
                	//System.out.println("noOfMatchingPtsForChannel="+noOfMatchingPtsForChannel);

            }
    }

    public int setMPtsCountForTrendChannel()
    {
        int countMPt = 0;//mainTrend.getMatchPointsCount();

        for(int i=0;i<this.trends.size();i++)
        {
            UpTrend upTrend = (UpTrend)(this.trends.get(i));
            
            countMPt = countMPt + upTrend.getMatchPointsCount();
        }

        this.noOfMatchingPtsForChannel = countMPt;
        
        //System.err.println("noOfMatchingPtsForChannel="+noOfMatchingPtsForChannel);
        
        
        return noOfMatchingPtsForChannel;
    }
    
    public int getMPtsCountForTrendChannel()
    {
        return noOfMatchingPtsForChannel;
    }


    public void drawTrendChannel(Graphics2D g)
    {
        for(int i=0;i<trends.size();i++)
        {
        	UpTrend upTrend = ((UpTrend)(this.trends.get(i)));
        	
        	if(upTrend.isDrawFlag())
            {
	            if((i==(trends.size()-1)))
	                upTrend.drawTrend(g, Color.GREEN,true,0);
	            else
	                upTrend.drawTrend(g, Color.GREEN,false,0);
            }
        }
    }
    
    
    
    public void drawTrendChannelByMaxPts(Graphics2D g)
    {
        for(int i=0;i<trends.size();i++)
        {
        	
        	if(i>5)
        		break;
        	
        	UpTrend upTrend = ((UpTrend)(this.trends.get(i)));
        	
        	
        	upTrend.drawTrend(g, Color.GREEN,true,0);
        	
        	
        	//System.err.println("ProjectedValue = "+ upTrend.projectedValue);
        	
        	
//            UpTrend upTrend = ((UpTrend)(this.trends.get(i)));
//
//            if(upTrend.isDrawFlag())
//            {
//	            if((i==(trends.size()-1)))
//	                upTrend.drawTrend(g, Color.GREEN,true);
//	            else
//	                upTrend.drawTrend(g, Color.GREEN,true);
//            }
        }
    }
    
    
    
    public void drawDisplacedTrends(Double x, Double y,Graphics2D g)
    {
        for(int i=0;i<trends.size();i++)
        {
            UpTrend upTrend = ((UpTrend)(this.trends.get(i)));
            
            UpTrend newUpTrend = new UpTrend(upTrend.getSlope(), x,y,x,y,0.0,upTrend.getYSize(),
            		upTrend.getXSize(),upTrend.getxOffset(),upTrend.getyOffset(),upTrend.getTrendReversalPoints());
            
            
            //System.err.println("M Count = "+ newUpTrend.getMatchPointsCount());
            
            newUpTrend.drawTrend(g, Color.ORANGE, true,0);
            
            //System.err.println("ProjectedValue = "+ newUpTrend.projectedValue);
            
        }
    }
    
    
    public void drawValidTrends(Double x1, Double y1,Double y2,Graphics2D g)
    {
    	Collections.sort(trends,new GCUpTMatchingPtComparator());
    	
    	for(int i=0;i<trends.size();i++)
    	{
    		UpTrend t = trends.get(i);
    		
    		if(t.matchPointsCount>2)
    		{
    			t.drawTrend(g, Color.MAGENTA, true,this.noOfMatchingPtsForChannel);
    		}
    		
    		if(i>3)
    			break;

    	}
    }
    
    
    
    
    
    private void setTrendChannelProperties()
    {
    	active = true;
        setMPtsCountForTrendChannel();
    }

    public int getNoOfMatchingPtsForChannel() {
        return noOfMatchingPtsForChannel;
    }

    public void setNoOfMatchingPtsForChannel(int noOfMatchingPtsForChannel) {
        this.noOfMatchingPtsForChannel = noOfMatchingPtsForChannel;
    }

    public ArrayList<UpTrend> getTrends() {
        return trends;
    }

    public void setTrends(ArrayList<UpTrend> trends) {
        this.trends = trends;
    }

    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getLowerY() {
        return lowerY;
    }

    public void setLowerY(double lowerY) {
        this.lowerY = lowerY;
    }

    public UpTrend getMainTrend() {
        return mainTrend;
    }

    public void setMainTrend(UpTrend mainTrend) {
        this.mainTrend = mainTrend;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public UpTrend getUpperTrend() {
        return upperTrend;
    }

    public void setUpperTrend(UpTrend upperTrend) {
        this.upperTrend = upperTrend;
    }

    public double getUpperY() {
        return upperY;
    }

    public void setUpperY(double upperY) {
        this.upperY = upperY;
    }



    
    
}
