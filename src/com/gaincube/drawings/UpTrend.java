


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
public class UpTrend extends Trend {

    private boolean drawFlag = false;
    
    
    public UpTrend()
    {
    	super();
    }
    
    public UpTrend(double x, double y, double slope,double currY,int ySize, int xSize,float xOffset,float yOffset,ArrayList<HashMap<String, Double>> trendReversalPoints)
    {
        super(x,y,slope,currY,ySize,xSize,xOffset,yOffset,trendReversalPoints);
        
        createTrendBySlope(slope,x,y,x,y,ySize,xSize);

        findMatchingPoints();
        
        //System.err.println("x="+x+",y="+y+",matchPointsCount="+matchPointsCount);
        
        setTrendProperties();
        
    }

    public UpTrend(double x1, double y1, double x2, double y2,double currY,int ySize, int xSize,float xOffset,float yOffset,ArrayList<HashMap<String, Double>> trendReversalPoints)
    {
        super(x1, y1, x2, y2,currY,ySize, xSize,xOffset,yOffset,trendReversalPoints);

        int tFlag = createTrend(x1,y1,x2,y2,ySize,xSize);
        
        if(tFlag==1)
        {
        	findMatchingPoints();
        	setTrendProperties();
        }
    }

    
    public UpTrend(double slope,double x1, double y1, double x2, double y2,double currY,int ySize, int xSize,float xOffset,float yOffset,ArrayList<HashMap<String, Double>> trendReversalPoints)
    {
        super(x1, y1, x2, y2,currY,ySize, xSize,xOffset,yOffset,trendReversalPoints);

        createTrendBySlope(slope,x1,y1,x2,y2,ySize,xSize);
        findMatchingPoints();
        setTrendProperties();
    }
    
    private int createTrend(double x1, double y1, double x2, double y2,int ySize, int xSize)
    {
    	double currX = x2;
    	double currY = y2;
        double prevX = x1;
        double prevY = y1;
        double[][] trendPoint = new double[1][2];

        this.slope = (currY-prevY)/(currX-prevX);
        
        //System.err.println("slope - "+slope);


        if((slope<0)&&(slope>-1)&&(slope<-.01))
        {

            yList =  new ArrayList<double[][]>();
            trendPoint[0][0] = x1;
            trendPoint[0][1] = y1;

            yList.add(trendPoint);

            while((currX<=xSize)&&(currY>0))
            {
            	points.add(""+new Double(currX).floatValue(), new Double(currY).floatValue());
            	
                trendPoint = new double[1][2];
                trendPoint[0][0] = currX;
                trendPoint[0][1] = currY;
                yList.add(trendPoint);

                prevX = currX;
                currX = currX+ xOffset;

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

    
    
    private void createTrendBySlope(double slope,double x1, double y1, double x2, double y2,int ySize, int xSize)
    {
    	double currX = x2;
    	double currY = y2;
        double prevX = x1;
        double prevY = y1;
        double[][] trendPoint = new double[1][2];


        yList =  new ArrayList<double[][]>();
        trendPoint[0][0] = x1;
        trendPoint[0][1] = y1;

        yList.add(trendPoint);

        while((currX<=xSize)&&(currY>0))
        {
        	points.add(""+new Double(currX).floatValue(), new Double(currY).floatValue());
            	
            trendPoint = new double[1][2];
            trendPoint[0][0] = currX;
            trendPoint[0][1] = currY;
            yList.add(trendPoint);

            prevX = currX;
            currX = currX+ xOffset;

            prevY = currY;
            currY = prevY + (slope*(currX-prevX));
            
            this.endX = prevX;
            this.endY = prevY;

         }

        currX = x2;
    	currY = y2;
        prevX = x1;
        prevY = y1;
        
        while((currX>1)&&(currY<ySize))
        {
        	points.add(""+new Double(currX).floatValue(), new Double(currY).floatValue());
            	
            trendPoint = new double[1][2];
            trendPoint[0][0] = currX;
            trendPoint[0][1] = currY;
            yList.add(trendPoint);

            prevX = currX;
            currX = currX- xOffset;

            prevY = currY;
            currY = prevY + (slope*(currX-prevX));

            this.xPos = prevX;
            this.yPos = prevY;
                
         }
        
         this.yPointsCount = yList.size();

         if(slope>0)
        	 this.direction = 0;
         else
        	 this.direction = 1;
    }

    
  
    
    public void drawTrend(Graphics2D g,Color c,boolean active,int no)
    {
        if(active)
            this.setActive(active);
        else if(this.getMatchPointsCount()>0)
            this.setActive(true);


        if(this.isActive())
        {
            //System.err.println("sX ="+xPos+"; sY ="+yPos+" endX = "+endX +" endY"+endY);
            //System.err.println("slope - "+slope);
            Line2D trendLine = new Line2D.Double (xPos,yPos, endX,endY);
            g.setColor(c);
            g.draw(trendLine);
            //g.drawString(""+no,new Double(xPos+200).floatValue(),new Double(yPos+3).floatValue());
            g.drawString(""+this.getMatchPointsCount(),new Double(xPos+300).floatValue(),new Double(yPos+3).floatValue());
            //g.drawString("X="+xPos+"  Y="+yPos,xPos-10,yPos+5);
            //g.drawString("slope="+slope,endX,endY);
        }
    }

    protected void findMatchingPoints()
    {
        matchingYList = new ArrayList<double[][]>();

        for(int k=0;k<yPointsCount-1;k++)
        {
            double[][] trendPoint =  (double[][])yList.get(k);
            
            boolean bFlag = false;
            
            for(int i=0;i<trendReversalPoints.size();i++)
            {
            	double xDiff = trendReversalPoints.get(i).get("X") - trendPoint[0][0];
            	
            	if(xDiff<0)
            		xDiff = -xDiff;
            	
            	if(xDiff<2)
            		bFlag = true;
            	
            	if((bFlag==true)&&(xDiff>10))
            		break;
            		
                if(xDiff >=0 && (xDiff)<GCConstants.XMatchingRange )
                {
            		
                	if(((trendPoint[0][1]-trendReversalPoints.get(i).get("Y"))>-GCConstants.yOffSet)&&
                			(((trendPoint[0][1]-trendReversalPoints.get(i).get("Y"))<GCConstants.yOffSet)))
                     {
                		//System.out.println("X diff="+(trendReversalPoints.get(i).get("X") - trendPoint[0][0]));
                		//System.out.println("Y diff="+(trendPoint[0][1]-trendReversalPoints.get(i).get("Y")));
                        matchingYList.add(trendPoint);
                        matchPointsCount++;
                     }
                }
             }
            
        }
        
        
        if(matchPointsCount>GCConstants.NoOfMatchingPtsForUp)
        {
            System.out.println("--------------------------------------------------------"+matchPointsCount);

	       //System.out.println("--------------------------------------------------------");
	       //System.out.println("yPointsCount - "+yPointsCount +" trendReversalPoints size - " +trendReversalPoints.size());
	       //System.out.println("slope="+this.slope +" mpoints=" + matchPointsCount);  
	       //System.out.println("--------------------------------------------------------");
        }

    }

    protected void setTrendProperties()
    {
        if(this.currentY < this.projectedValue)
            supportFlag = true;
        else
            resistanceFlag = true;

        if(this.matchPointsCount>GCConstants.NoOfMatchingPtsForUp)
            this.setActive(true);
        else
            this.setActive(false);

        this.setActive(true);

    }

   
	public boolean isDrawFlag() {
		return drawFlag;
	}

	public void setDrawFlag(boolean drawFlag) {
		this.drawFlag = drawFlag;
	}

	
	

}


