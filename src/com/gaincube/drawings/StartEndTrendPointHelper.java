
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.gaincube.util.GCDownTrendReversalComparator;
import com.gaincube.util.GCUpTrendReversalComparator;


/**
 *
 * @author Pankaj
 */
public class StartEndTrendPointHelper {

	
		

    public static ArrayList<HashMap<String, Double>> getTrendReversalPoints(double chartData[][][])
    {
    	//double [][][] trendRevPoints = new double[4000][1][2];

        double [][][] trendRevPoints = new double[4000][1][2];
        ArrayList<HashMap<String, Double>> tRevPoints = new ArrayList<HashMap<String, Double>>();

        
    	//ArrayList<double[][]> trendRevPoints = new ArrayList<double[][]>(); 
    	
    	 //System.out.println("l="+chartData.length);
        for(int k=2;k<chartData.length-2;k++)
        {
            if((chartData[k][0][1]<chartData[k-1][0][1])
                    && (chartData[k][0][1]<chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]<chartData[k+1][0][1])
                    && (chartData[k][0][1]<chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
            	//System.out.println("j="+j+" k="+k);
                //trendRevPoints[j] = chartData[k];
                //trendRevPoints.add(chartData[k]);
                
                
            	HashMap<String,Double> rPoint = new HashMap<String,Double>();
            	//downTrendPoints[j] = chartData[k];
            	trendRevPoints[k][0][0] = chartData[k][0][0];
            	trendRevPoints[k][0][1] = chartData[k][0][1];
            	
            	rPoint.put("X", chartData[k][0][0]);
            	rPoint.put("Y", chartData[k][0][1]);
            	tRevPoints.add(rPoint);
                
                
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1]);
            }
            //down
            if((chartData[k][0][1]>chartData[k-1][0][1])
                    && (chartData[k][0][1]>chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]>chartData[k+1][0][1])
                    && (chartData[k][0][1]>chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
                //trendRevPoints[j] = chartData[k];
            	
            	HashMap<String,Double> rPoint = new HashMap<String,Double>();
            	//downTrendPoints[j] = chartData[k];
            	trendRevPoints[k][0][0] = chartData[k][0][0];
            	trendRevPoints[k][0][1] = chartData[k][0][1];
            	
            	rPoint.put("X", chartData[k][0][0]);
            	rPoint.put("Y", chartData[k][0][1]);
            	tRevPoints.add(rPoint);
            	
                //trendRevPoints.add(chartData[k]);
               
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1],Color.red,"");
            }
        }
        
        /*for(int k=chartData.length-100;k<chartData.length-2;k++)
        {
            if((chartData[k][0][1]<chartData[k-1][0][1])
                    && (chartData[k][0][1]<chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]<chartData[k+1][0][1])
                    && (chartData[k][0][1]<chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
                trendRevPoints[j] = chartData[k];
                j++;
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1]);
            }
            //down
            if((chartData[k][0][1]>chartData[k-1][0][1])
                    && (chartData[k][0][1]>chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]>chartData[k+1][0][1])
                    && (chartData[k][0][1]>chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
                trendRevPoints[j] = chartData[k];
                j++;
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1],Color.red,"");
            }


        }*/
        
        return tRevPoints;
    }


  

    public static ArrayList<HashMap<String, Double>> getHighTrendReversalPoints(double chartData[][][])
    {
    	double [][][] upTrendPoints = new double[2000][1][2];
    	ArrayList<HashMap<String, Double>> tDUpRevPoints = new ArrayList<HashMap<String, Double>>();
    	
        int j=0;
        for(int k=2;k<chartData.length-2;k++)
        {

            //Up
            if((chartData[k][0][1]<chartData[k-1][0][1])
                    && (chartData[k][0][1]<chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]<chartData[k+1][0][1])
                    && (chartData[k][0][1]<chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
            	HashMap<String,Double> rPoint = new HashMap<String,Double>();
            	upTrendPoints[j][0][0] = chartData[k][0][0];
            	upTrendPoints[j][0][1] = chartData[k][0][1];
            	
            	rPoint.put("X", chartData[k][0][0]);
            	rPoint.put("Y", chartData[k][0][1]);
            	tDUpRevPoints.add(rPoint);
                j++;
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1],Color.MAGENTA,"");
            }
        }
        return getSortedDownTrendReversalPoints(tDUpRevPoints);
    }

    public static ArrayList<HashMap<String, Double>> getLowTrendReversalPoints(double chartData[][][])
    {
        double [][][] downTrendPoints = new double[4000][1][2];
        
        ArrayList<HashMap<String, Double>> tDUpRevPoints = new ArrayList<HashMap<String, Double>>();

        int j=0;
        for(int k=2;k<chartData.length-2;k++)
        {
            //Up
        	
            if((chartData[k][0][1]>chartData[k-1][0][1])
                    && (chartData[k][0][1]>chartData[k-2][0][1])
                    //&& (chartData[k][0][1]>chartData[k-3][0][1])
                    //&& (chartData[k][0][1]>chartData[k-4][0][1])
                    //&& (chartData[k][0][1]>chartData[k-5][0][1])
                    && (chartData[k][0][1]>chartData[k+1][0][1])
                    && (chartData[k][0][1]>chartData[k+2][0][1])
                    //&& (chartData[k][0][1]>chartData[k+3][0][1])
                    //&& (chartData[k][0][1]>chartData[k+4][0][1])
                    //&& (chartData[k][0][1]>chartData[k+5][0][1])
                    )
            {
            	HashMap<String,Double> rPoint = new HashMap<String,Double>();
            	//downTrendPoints[j] = chartData[k];
            	downTrendPoints[j][0][0] = chartData[k][0][0];
            	downTrendPoints[j][0][1] = chartData[k][0][1];
            	
            	rPoint.put("X", chartData[k][0][0]);
            	rPoint.put("Y", chartData[k][0][1]);
            	tDUpRevPoints.add(rPoint);
                //System.err.println("J="+j+" X = "+downTrendPoints[j][0][0] + " ;Y = "+downTrendPoints[j][0][1]);
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1],Color.BLUE,"");
                j++;
            }
        }
        
        return getSortedTrendReversalPoints(tDUpRevPoints);
        //return tDUpRevPoints;
    }


    public static double[][][] getNextTrendPoint(double xPos,double yPos,  ArrayList<HashMap<String, Double>> downTrendPoints)
    {
	     double yNextPos;
	     //double prevHighYPos = 0;
	     double[][][] nextPoint = new double[4000][1][2];
	     boolean trendFlag = false;

	     //System.err.println(" xPos="+downTrendPoints[(int)xPos][0][0]+" yPos="+downTrendPoints[(int)xPos][0][1]+" yPos="+yPos);

	     int j=0;
	     for(int k=((int)xPos)+1;k<downTrendPoints.size();k++)
	     {
	    	 //System.err.println("\nk="+k);
	    	 yNextPos = downTrendPoints.get(k).get("Y").doubleValue();
	    	 //prevHighYPos = downTrendPoints[k][0][1];
	    	 //System.err.println(" xPos="+downTrendPoints[k][0][0]+" prevHighYPos= "+prevHighYPos+" yNextPos="+yNextPos+" yPos="+yPos);//&&
	    	 
	    	 if((yPos>yNextPos)&&((yPos-yNextPos)>10))//&&(prevHighYPos<=yNextPos))
	    	 {
		    	 nextPoint[j][0][0] = downTrendPoints.get(k).get("X").doubleValue();
		    	 nextPoint[j][0][1] = downTrendPoints.get(k).get("Y").doubleValue();
		    	 //prevHighYPos = yNextPos;
		    	 //System.err.println("Changed prevHighYPos=");
		    	 trendFlag = true;
		    	 j++;
	    	 }
	    	 
	    	 if((trendFlag)&&((yPos<yNextPos)))
	    	 {
	    		//System.err.println("breaking out");
	    		break;
	    	 }

             if(yNextPos==0)
                 break;
	     }
         return nextPoint;
    }


    public static double[][][] getNextDownTrendPoint(double xPos,double yPos, double [][][] downTrendPoints)
    {
	     double yNextPos;
	     //double prevHighYPos = 0;
	     double[][][] nextPoint = new double[50][1][2];
	     boolean trendFlag = false;

	     //System.err.println(" xPos="+downTrendPoints[(int)xPos][0][0]+" yPos="+downTrendPoints[(int)xPos][0][1]+" yPos="+yPos);

	     int j=0;
	     for(int k=((int)xPos)+1;k<downTrendPoints.length;k++)
	     {
	    	 //System.err.println("\nk="+k);

	    	 yNextPos = downTrendPoints[k][0][1];
	    	 //prevHighYPos = downTrendPoints[k][0][1];
	    	 //System.err.println(" xPos="+downTrendPoints[k][0][0]+" prevHighYPos= "+prevHighYPos+" yNextPos="+yNextPos+" yPos="+yPos);
                        //&&
	    	 if((yPos<yNextPos)&&((yNextPos-yPos)>10))//&&(prevHighYPos<=yNextPos))
		 {
		    	 nextPoint[j][0][0] = downTrendPoints[k][0][0];
		    	 nextPoint[j][0][1] = downTrendPoints[k][0][1];
		 	//prevHighYPos = yNextPos;
		 	//System.err.println("Changed prevHighYPos="+prevHighYPos);
		 	trendFlag = true;
		  	j++;
                        break;
		 }
	    	 if((trendFlag)&&((yPos>yNextPos)))
	    	 {
	    		System.err.println("breaking out");
	    		break;
	    	 }
		     if(yNextPos==0)
		    	 break;
	     }
         return nextPoint;
    }


    public static double[][][] getNextLowPoint(int xPos,double chartData[][][])
    {
	     xPos = xPos/4;
	     double yPos;
	     double prevHighYPos = 0.0f;
	     double[][][] lowPoint = new double[1][1][2];

	     //System.err.println(" xPos= "+xPos);

	     for(int k=xPos;k<chartData.length;k++)
	     {
	    	 yPos = chartData[k][0][1];

	    	 if(yPos>prevHighYPos)
	    	 {
	    		 lowPoint[0] = chartData[k];
	    		 prevHighYPos = yPos;
	    	 }
	     }

	     return lowPoint;
    }


    public static double[][][] getNextUpPoint(int xPos,double chartData[][][])
    {
	     xPos = xPos/4;
	     double yPos;
	     double prevHighYPos = 5000f;
	     double[][][] upPoint = new double[1][1][2];

	     //System.err.println(" xPos= "+xPos);

	     for(int k=xPos;k<chartData.length;k++)
	     {
	    	 yPos = chartData[k][0][1];

	    	 if(yPos<prevHighYPos)
	    	 {
	    		 upPoint[0] = chartData[k];
	    		 prevHighYPos = yPos;
	    	 }
	     }

	     return upPoint;
    }

    /*private static void printRectPoint(Graphics2D g,double x, double y,Color c,String text)
    {
        g.setColor(c);
        Shape Rectangle2D = new Rectangle2D.Double (x+1,y,6,6);
        g.draw(Rectangle2D);
        //g.drawString(text+" X="+x+1+";Y="+y,x-5,y+10);
        //g.drawString(""+y,x-5,y+5);
    }*/
    
    
    public static ArrayList<HashMap<String, Double>> getSortedTrendReversalPoints( ArrayList<HashMap<String, Double>> tRevPoints)
    {
    	Collections.sort(tRevPoints,new GCUpTrendReversalComparator());

    	HashMap<String,Double> lPoint =  tRevPoints.get(0);
    	
    	ArrayList<HashMap<String, Double>> cDUpRevPoints = changeTRevPoints(tRevPoints,lPoint);
    	
    	Collections.sort(cDUpRevPoints,new GCUpTrendReversalComparator());
    	
    	return cDUpRevPoints;
    }
    
    
    public static ArrayList<HashMap<String, Double>> getSortedDownTrendReversalPoints( ArrayList<HashMap<String, Double>> tRevPoints)
    {
    	
    	Collections.sort(tRevPoints,new GCDownTrendReversalComparator());

    	HashMap<String,Double> lPoint =  tRevPoints.get(0);
    	
    	ArrayList<HashMap<String, Double>> cDUpRevPoints = changeTRevPoints(tRevPoints,lPoint);
    	
    	Collections.sort(cDUpRevPoints,new GCDownTrendReversalComparator());
    	
    	return cDUpRevPoints;
    }
    
    
    public static ArrayList<HashMap<String, Double>> changeTRevPoints(ArrayList<HashMap<String, Double>> tRevPoints,HashMap<String,Double> lPoint)
    {
    	ArrayList<HashMap<String, Double>> cDUpRevPoints =  new ArrayList<HashMap<String, Double>>();
    	
    	cDUpRevPoints.add(lPoint);
    	
    	for(int k=0;k<tRevPoints.size();k++)
    	{
    		//if(lPoint.get("X") < tRevPoints.get(k).get("X") )
    		//{
    			cDUpRevPoints.add(tRevPoints.get(k));
    		//}
    		
    	}
    	
    	return cDUpRevPoints;
    }
    
    
    
    public static double[][][] getTrendReversalPoints(int x,double chartData[][][])
    {
    	double [][][] trendRevPoints = new double[200][1][2];

        int j=0;
        for(int k=2;k<chartData.length-2;k++)
        {
            if((chartData[k][0][1]<chartData[k-1][0][1])
                    && (chartData[k][0][1]<chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]<chartData[k+1][0][1])
                    && (chartData[k][0][1]<chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
                trendRevPoints[j] = chartData[k];
                j++;
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1]);
            }
            //down
            if((chartData[k][0][1]>chartData[k-1][0][1])
                    && (chartData[k][0][1]>chartData[k-2][0][1])
                    //&& (chartData[k][0][1]<chartData[k-3][0][1])
                    //&& (chartData[k][0][1]<chartData[k-4][0][1])
                    //&& (chartData[k][0][1]<chartData[k-5][0][1])
                    && (chartData[k][0][1]>chartData[k+1][0][1])
                    && (chartData[k][0][1]>chartData[k+2][0][1])
                    //&& (chartData[k][0][1]<chartData[k+3][0][1])
                    //&& (chartData[k][0][1]<chartData[k+4][0][1])
                    //&& (chartData[k][0][1]<chartData[k+5][0][1])
                    )
            {
                trendRevPoints[j] = chartData[k];
                j++;
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1],Color.red,"");
                //printRectPoint(g,chartData[k][0][0],chartData[k][0][1]);
            }


        }
        return trendRevPoints;
    }
    

}
