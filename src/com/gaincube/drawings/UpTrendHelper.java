


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import com.gaincube.datamodel.UpTrendChannelDataModel;
import com.gaincube.datamodel.UpTrendDataModel;
import com.gaincube.util.GCConstants;

/**
 *
 * @author Pankaj
 */
public class UpTrendHelper
{
    double chartData[][][] =  null;
    int ySize = 0;
    int xSize = 0;
    float xOffset = 0;
    float yOffset = 0;
    
    UpTrendDataModel trendDataModel = null;
    UpTrendChannelDataModel trendChannelDataModel = null;
    ArrayList<double[][]> trendRevPts = null;
    ArrayList<HashMap<String, Double>> lowTrendPoints = null;
    double[][][] upTrendPoints = null;
    
    //ArrayList<UpTrend> trends = new ArrayList<UpTrend>();
    
    public UpTrendHelper(int xSize,int ySize,double chartData[][][],float xOffset,float yOffset)
    {
        this.chartData = chartData;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xOffset =  xOffset;
        this.yOffset =  yOffset;
        
        trendDataModel = new UpTrendDataModel();
        trendChannelDataModel = new UpTrendChannelDataModel();
        //trendRevPts = StartEndTrendPointHelper.getTrendReversalPoints(chartData);
        lowTrendPoints =StartEndTrendPointHelper.getTrendReversalPoints(chartData);
        //upTrendPoints =TrendPointHelper.getUpTrendReversalPoints(chartData);

    }
    
    
    
    public UpTrendChannelDataModel determineTrends() throws Exception
    {
        double nextPoint[][][] = new double[1][1][2];
        
        for(int s=0;s<100;s++)
        {//lowTrendPoints.size()
	        for(int p=1;p<lowTrendPoints.size();p++)
	        {
	            if(p>GCConstants.UpTrendHNo)
	            	break;
	            
	            if((s+p) >= lowTrendPoints.size())
	            	break;
	            
	
	            nextPoint[0][0][0] = lowTrendPoints.get(s+p).get("X").doubleValue();
		    	nextPoint[0][0][1] = lowTrendPoints.get(s+p).get("Y").doubleValue();
	        	
	        	//System.err.println("PrevX = "+lowTrendPoints.get(s).get("X").doubleValue() + " ;PrevY = "+lowTrendPoints.get(s).get("Y").doubleValue());
	        	//System.err.println("TrendX = "+nextPoint[0][0][0] + " ;TrendY = "+nextPoint[0][0][1]);
	
	        	createTrend(lowTrendPoints.get(s).get("X").doubleValue(),
	        				lowTrendPoints.get(s).get("Y").doubleValue(),
	        				nextPoint[0][0][0],nextPoint[0][0][1],
	        				lowTrendPoints.get(s).get("Y").doubleValue(),ySize,xSize,lowTrendPoints,
	        				lowTrendPoints.get(s).get("Y").doubleValue(),
	        				lowTrendPoints.get(s).get("X").doubleValue(),
	        				lowTrendPoints.get(s).get("Y").doubleValue()+GCConstants.TrendChannelRange);
	        	
	        }
        }
        return trendChannelDataModel;
    }



    private void createTrend(double x1, double y1, double x2, double y2,double currY,
            int ySize, int xSize,ArrayList<HashMap<String, Double>> lowTrendPoints,
            double lowerY,double upperX,double upperY)
    {
    	
    	//System.out.println("X1="+x1 + " y1="+y1 +" X2="+x2 + " y2="+y2 + " currY="+currY);
    	
        UpTrend trend = new UpTrend(x1,y1,x2,y2,currY,ySize,xSize,this.xOffset,this.yOffset, lowTrendPoints);
        
    	if(trend.getMatchPointsCount()>GCConstants.NoOfMatchingPtsForUp)
        {
        	trendDataModel.addTrend(trend);
        	//System.out.println("lowerY="+lowerY + " upperX="+upperX +" upperY="+upperY);
        	createTrendChannel(trend,lowerY,upperX,upperY);
        }
    }

    
    public void drawTrends(Graphics2D g)
    {
    	ArrayList<UpTrend> trends = trendDataModel.getTrends();
    	
    	for(int i=0;i<trends.size();i++)
    	  trends.get(i).drawTrend(g, Color.MAGENTA,true,0);
    }

    private void createTrendChannel(UpTrend mainTrend,double lowerY, double upperX, double upperY)
    {

        UpTrendChannel trendChannel = new UpTrendChannel(mainTrend,lowerY,upperX,upperY,GCConstants.NoOfUpTrendChannelPart,mainTrend.getxOffset(),mainTrend.getyOffset());
        System.err.println("t m "+trendChannel.getNoOfMatchingPtsForChannel());
        
        if(trendChannel.getNoOfMatchingPtsForChannel()>GCConstants.NoOfMatchingPtsForUpChannel)
        	trendChannelDataModel.addTrendChannel(trendChannel);
    }

        
    public void printTrendRevPoints(Graphics2D g)
    {
    	 for(int p=GCConstants.UpTrendLNo;p<lowTrendPoints.size();p++)
         {
        	 //printRectPoint(g,lowTrendPoints.get(p).get("X")-5,lowTrendPoints.get(p).get("Y")+5,Color.ORANGE,"p");
        	 //printRectPoint(g,lowTrendPoints.get(p).get("X")-5,lowTrendPoints.get(p).get("Y")+5,Color.ORANGE,"p");

        	 
        	 //printRectPoint(g,nextPoint[0][0][0]-7,nextPoint[0][0][1]+7,Color.GREEN,"n");
        	 //printRectPoint(g,lowPoint[0][0][0],lowPoint[0][0][1],Color.CYAN,"l");
         }
    	
    	//printRectPoint(g,lowTrendPoints[p][0][0]-5,lowTrendPoints[p][0][1]+5,Color.ORANGE,"p");
    	//printRectPoint(g,nextPoint[0][0][0]-7,nextPoint[0][0][1]+7,Color.GREEN,"n");
    	//printRectPoint(g,lowPoint[0][0][0],lowPoint[0][0][1],Color.CYAN,"l");
    	
    	
    	//printRectPoint(g,nextPoint[0][0][0],nextPoint[0][0][1],Color.green,"p");


    }
    
    
    
}






/*
public UpTrendChannelDataModel determineTrends() throws Exception
{
    double nextPoint[][][] = new double[1][1][2];
    double currentY = chartData[chartData.length-1][0][1];

    for(int p=GCConstants.UpTrendLNo;p<lowTrendPoints.size()-1;p++)
    {
        if(p>GCConstants.UpTrendHNo)
        	break;

  
    	nextPoint = StartEndTrendPointHelper.getNextTrendPoint(p,lowTrendPoints.get(p).get("Y").doubleValue(),lowTrendPoints);
    	double lowPoint[][][] = StartEndTrendPointHelper.getNextLowPoint(GCConstants.iOrigin,chartData);

    	System.err.println("PrevX = "+lowTrendPoints.get(p).get("X").doubleValue() + " ;PrevY = "+lowTrendPoints.get(p).get("Y").doubleValue());
    	System.err.println("TrendX = "+nextPoint[0][0][0] + " ;TrendY = "+nextPoint[0][0][1]);

    	for(int np=0; np< nextPoint.length;np++)
    	{
    		if(nextPoint[np][0][0]==0)
    			break;

    		createTrend(lowTrendPoints.get(p).get("X").doubleValue(),lowTrendPoints.get(p).get("Y").doubleValue(),nextPoint[np][0][0],nextPoint[np][0][1],
    				currentY,ySize,xSize,trendRevPts,lowTrendPoints.get(p).get("Y").doubleValue(),lowPoint[0][0][0],lowPoint[0][0][1]);
    		
    		//System.err.println(np);
    	}
    }
/*
    nextPoint = new double[1][1][2];

    for(int p=GCConstants.UpTrendLNo;p<upTrendPoints.length-1;p++)
    {
    	if(p>GCConstants.UpTrendHNo)
    		break;

    	if(upTrendPoints[p][0][1]==0)
    	{
    		break;
    	}

    	nextPoint = StartEndTrendPointHelper.getNextTrendPoint(p,upTrendPoints[p][0][1],upTrendPoints);
    	double lowPoint[][][] = StartEndTrendPointHelper.getNextLowPoint(GCConstants.iOrigin,chartData);
    	//System.err.println("PrevX = "+lowTrendPoints[p][0][0] + " ;PrevY = "+lowTrendPoints[p][0][1]);
    	//System.err.println("TrendUpX = "+nextPoint[0][0][0] + " ;TrendUpX = "+nextPoint[0][0][1]);

    	for(int np=0; np< nextPoint.length;np++)
    	{
    		if(nextPoint[np][0][0]==0)
    			break;

    		createTrend(upTrendPoints[p][0][0],upTrendPoints[p][0][1],nextPoint[np][0][0],nextPoint[np][0][1],
        			currentY,ySize,xSize,trendRevPts,lowTrendPoints[p][0][1],lowPoint[0][0][0],lowPoint[0][0][1]);
    	}
    }
////////*
    return trendChannelDataModel;
}
*/
