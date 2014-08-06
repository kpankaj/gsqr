

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;

import com.gaincube.datamodel.DownTrendChannelDataModel;
import com.gaincube.datamodel.DownTrendDataModel;
import com.gaincube.util.GCConstants;

/**
 *
 * @author Pankaj
 */
public class DownTrendHelper {

    double chartData[][][] =  null;
    int ySize = 0;
    int xSize = 0;
    float xOffset = 0;
    float yOffset = 0;
    
    DownTrendDataModel downTrendDataModel = null;
    DownTrendChannelDataModel downTrendChannelDataModel = null;
    
    ArrayList<double[][]> trendRevPts = null;
    ArrayList<HashMap<String, Double>> highTrendPoints = null;

    public DownTrendHelper(int xSize,int ySize,double chartData[][][],float xOffset,float yOffset)
    {
        this.chartData = chartData;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xOffset =  xOffset;
        this.yOffset =  yOffset;
        
        downTrendChannelDataModel = new DownTrendChannelDataModel();
        downTrendDataModel = new  DownTrendDataModel();
        
       // trendRevPts = StartEndTrendPointHelper.getTrendReversalPoints(chartData);
        highTrendPoints =StartEndTrendPointHelper.getHighTrendReversalPoints(chartData);
        
    }
    
    
    public DownTrendChannelDataModel determineDownTrends() throws Exception
    {
        double nextPoint[][][] = new double[1][1][2];
        
        for(int s=0;s<100;s++)
        {
	        for(int p=1;p<highTrendPoints.size();p++)
	        {
	            if(p>GCConstants.DownTrendHNo)
	            	break;
	            
	            if((s+p) >= highTrendPoints.size())
	            	break;
	            
	        	//System.out.println("sp="+(s+p)+",size="+highTrendPoints.size());
	        	
	            nextPoint[0][0][0] = highTrendPoints.get(s+p).get("X").doubleValue();
	            
		    	nextPoint[0][0][1] = highTrendPoints.get(s+p).get("Y").doubleValue();
	        	
	        	//System.err.println("PrevX = "+dUpTrendPoints.get(s).get("X").doubleValue() + " ;PrevY = "+dUpTrendPoints.get(s).get("Y").doubleValue());
	        	//System.err.println("TrendX = "+nextPoint[0][0][0] + " ;TrendY = "+nextPoint[0][0][1]);
	
		    	createDownTrend(highTrendPoints.get(s).get("X").doubleValue(),
	        				highTrendPoints.get(s).get("Y").doubleValue(),
	        				nextPoint[0][0][0],nextPoint[0][0][1],
	        				highTrendPoints.get(s).get("Y").doubleValue(),ySize,xSize,highTrendPoints,
	        				highTrendPoints.get(s).get("Y").doubleValue(),
	        				highTrendPoints.get(s).get("X").doubleValue(),
	        				highTrendPoints.get(s).get("Y").doubleValue()+GCConstants.TrendChannelRange);
	        	
	        }
        }
        return downTrendChannelDataModel;
    }
    
    
    private void createDownTrend(double x1, double y1, double x2, double y2,double currY,
            int ySize, int xSize,ArrayList<HashMap<String, Double>> dUpTrendPoints,
            double lowerY,double upperX,double upperY)
    {
    	
    	System.err.println("X1="+x1 + " y1="+y1 +" X2="+x2 + " y2="+y2 + " currY="+currY);
    	
       	
    	if(x1 < x2)
    	{
	    	DownTrend downTrend  = new DownTrend(x1,y1,x2,y2,currY,ySize,xSize,highTrendPoints);
	        
	    	if(downTrend.getMatchPointsCount()>GCConstants.NoOfMatchingPtsForDown)
	        {
	            System.err.println("down matching =  "+downTrend.getMatchPointsCount());
	            
	    		downTrendDataModel.addTrend(downTrend);

	    		createDownTrendChannel(downTrend,lowerY,upperX,upperY);
	        }
    	}
    }

    
    public void drawTrends(Graphics2D g)
    {
    	ArrayList<DownTrend> trends = downTrendDataModel.getTrends();
    	
    	for(int i=0;i<trends.size();i++)
    	  trends.get(i).drawTrend(g, Color.RED,true);
    }

    private void createDownTrendChannel(DownTrend mainTrend,double lowerY, double upperX, double upperY)
    {
        DownTrendChannel downTrendDataModel = new DownTrendChannel(mainTrend,lowerY,upperX,upperY,GCConstants.NoOfUpTrendChannelPart);
        
        System.err.println("t m "+downTrendDataModel.getNoOfMatchingPtsForChannel());
        
        if(downTrendDataModel.getNoOfMatchingPtsForChannel()>GCConstants.NoOfMatchingPtsForDownChannel)
        	downTrendChannelDataModel.addTrendChannel(downTrendDataModel);
    }
    
    
    public void printTrendRevPoints(Graphics2D g)
    {
    	 for(int p=GCConstants.UpTrendLNo;p<highTrendPoints.size();p++)
         {
        	 //printRectPoint(g,highTrendPoints.get(p).get("X")-5,highTrendPoints.get(p).get("Y")+5,Color.ORANGE,"p");
        	// printRectPoint(g,highTrendPoints.get(p).get("X")-5,highTrendPoints.get(p).get("Y")+5,Color.ORANGE,"p");
         }
    	
    	//printRectPoint(g,dUpTrendPoints[p][0][0]-5,dUpTrendPoints[p][0][1]+5,Color.ORANGE,"p");
    	//printRectPoint(g,nextPoint[0][0][0]-7,nextPoint[0][0][1]+7,Color.GREEN,"n");
    	//printRectPoint(g,lowPoint[0][0][0],lowPoint[0][0][1],Color.CYAN,"l");
    	
    	
    	//printRectPoint(g,nextPoint[0][0][0],nextPoint[0][0][1],Color.green,"p");


    }
    
    private void printRectPoint(Graphics2D g,double x, double y,Color c,String text)
    {
        g.setColor(c);
        Shape Rectangle2D = new java.awt.geom.Rectangle2D.Double (x+1,y,6,6);
        g.draw(Rectangle2D);
        //g.drawString(text+" X="+x+1+";Y="+y,x-5,y+10);
        //g.drawString(""+y,x-5,y+5);
    }

}

