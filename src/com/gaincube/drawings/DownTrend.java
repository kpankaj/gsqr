
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import com.gaincube.util.GCConstants;

/**
 *
 * @author Pankaj
 */
public class DownTrend extends Trend {


    private boolean drawFlag = false;

    private TrendPoints points;


    public DownTrend(double x, double y, double slope,double currY,int ySize, int xSize,ArrayList<HashMap<String, Double>> trendReversalPoints,Color c)
    {
    	super(x,y,slope,currY,ySize,xSize,0,0,trendReversalPoints);
        
        double y2 = y + (slope*GCConstants.xOffset);
        
        int tFlag =   createDownTrend(x,y,x+GCConstants.xOffset,y2,ySize,xSize,true);
        
        if(tFlag==1)
        {
        	findMatchingPoints();
        	setTrendProperties();
        }
        
    }

    public DownTrend(double x1, double y1, double x2, double y2,double currY,int ySize, int xSize,ArrayList<HashMap<String, Double>> trendReversalPoints)
    {
        super(x1, y1, x2, y2,currY,ySize, xSize,0,0,trendReversalPoints);
        
        int tFlag = createDownTrend(x1,y1,x2,y2,ySize,xSize,true);
        
        if(tFlag==1)
        {
        	findMatchingPoints();
        	setTrendProperties();
        }
        
    }

    private int createDownTrend(double x1, double y1, double x2, double y2,int ySize, int xSize,boolean slopeFlag)
    {
    	double currX = x2;
    	double currY = y2;
        double prevX = x1;
        double prevY = y1;
        double[][] trendPoint = new double[1][2];

        this.slope = (currY-prevY)/(currX-prevX);

        if((slope>0)&&(slope>.3))
        {
        	points.add(""+currX, currY);

            yList =  new ArrayList<double[][]>();
            trendPoint[0][0] = x1;
            trendPoint[0][1] = y1;

            yList.add(trendPoint);

            while((currX<xSize)&&(currY<ySize)&&(currY>0))
            {
                trendPoint = new double[1][2];
                trendPoint[0][0] = currX;
                trendPoint[0][1] = currY;
                yList.add(trendPoint);

                prevX = currX;
                currX = currX+ GCConstants.xOffset;

                prevY = currY;
                currY = prevY + (slope*(currX-prevX));

                this.endX = prevX;
                this.endY = prevY;
                this.projectedValue = prevY;
            }

            this.yPointsCount = yList.size();

            if(slope>0)
                this.direction = 0;
            else
                this.direction = 1;
          
            return 1;

        }
        else
        {
        	return 0;
        }
    }


    public void drawTrend(Graphics2D g,Color c,boolean active)
    {
        if(active)
        	this.setActive(active);

        if(this.isActive())
        {
            //System.err.println("sX ="+xPos+"; sY ="+yPos+" endX = "+endX +" endY"+endY);
            //System.err.println("slope - "+slope);
            Line2D trendLine = new Line2D.Double (xPos,yPos, endX,endY);
            g.setColor(Color.RED);
            g.draw(trendLine);
            g.drawString(""+this.getMatchPointsCount(),new Double(xPos-5).floatValue(),new Double(yPos-5).floatValue());
            //g.drawString("Y="+endY,endX-10,endY);
            //g.drawString("X="+xPos+"  Y="+yPos,xPos-10,yPos+5);
            //g.drawString("slope="+slope,endX,endY);
        }
    }


    private void findMatchingPoints()
    {
        matchingYList = new ArrayList<double[][]>();

        for(int k=0;k<yPointsCount-1;k++)
        {
        	double[][] trendPoint =  (double[][])yList.get(k);

            for(int i=0;i<trendReversalPoints.size();i++)
            {
            	if((trendReversalPoints.get(i).get("X") - trendPoint[0][0])<5 )
                {
            		 if(((trendPoint[0][1]-trendReversalPoints.get(i).get("Y"))>-.2)&&
                			(((trendPoint[0][1]-trendReversalPoints.get(i).get("Y"))<.2)))
                     {
                		//System.out.println("diff="+(trendPoint[0][1]-trendReversalPoints.get(i)[0][1]));
                        //System.err.println("  Diff - "+(trendPoint[0][1]-trendReversalPoints[i][0][1]));
                        matchingYList.add(trendPoint);
                        matchPointsCount++;
                     }
                }
            	
            }
        }
    }

    private void setTrendProperties()
    {
        if(this.currentY < this.projectedValue)
            supportFlag = true;
        else
            resistanceFlag = true;

        if(this.matchPointsCount>GCConstants.NoOfMatchingPtsForDown)
            this.setActive(true);
        else
            this.setActive(false);
    }

    
    public boolean isDrawFlag() {
		return drawFlag;
	}

	public void setDrawFlag(boolean drawFlag) {
		this.drawFlag = drawFlag;
	}
    

}


