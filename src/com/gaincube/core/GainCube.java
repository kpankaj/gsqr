/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.core;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.gaincube.dao.ChartDataDAO;
import com.gaincube.datamodel.ChartDataModel;
import com.gaincube.datamodel.DownTrendChannelDataModel;
import com.gaincube.datamodel.StockPriceDataModel;
import com.gaincube.datamodel.UpTrendChannelDataModel;
import com.gaincube.drawings.DownTrendChannel;
import com.gaincube.drawings.DownTrendHelper;
import com.gaincube.drawings.UpTrendChannel;
import com.gaincube.drawings.UpTrendHelper;
import com.gaincube.util.GCConstants;

/**
 * @author Pankaj
 */
public class GainCube extends Applet implements MouseListener
{
    public static final long serialVersionUID = 878789787L;
    ChartDataDAO dataTool = new ChartDataDAO();
    ChartDataModel chartDataModel = null;
    ChartDataModel trendChartDataModel = null;

    double chartData[][][] = null;
    double trendChartData[][][] = null;
    
    boolean initFlag = false;
    
    int xSize = 1350;
    int ySize = 630;
    
    float xOffset = 0;
    float yOffset = 0;

    int width = 1450;
    int height = 750;
    
    double minX = 0;
    double minY = 0;
    		
    double maxX = 0;
    double maxY = 0;
    

    UpTrendChannelDataModel trendChannelDataModel = null;
    DownTrendChannelDataModel downTrendChannelDataModel = null;
    
    UpTrendHelper upTrendHelper = null;
    DownTrendHelper downTrendHelper = null;
    
    ArrayList<UpTrendChannel> upTrends = null;
    ArrayList<UpTrendChannel> upTrendsByMaxPts = null;
    
    ArrayList<DownTrendChannel> downTrendsByMaxPts = null;
    ArrayList<DownTrendChannel> downTrends = null;
    

    public void init()
    {
      	try
    	{
      		addMouseListener(this);
      		
      		Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2003-12-16T10:05:21.968"); 
       	  	Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2007-12-10T10:05:21.968"); 
          	chartDataModel = new ChartDataModel(startDate,endDate);
          	
      		Date sDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2003-12-16T10:05:21.968"); 
      		Date eDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2005-06-10T10:05:21.968"); 
      		trendChartDataModel = new ChartDataModel(sDate,eDate);

          	
	        this.setSize(width,height);
	        
	        createLineChart();
	        
	        upTrendHelper = new UpTrendHelper(xSize,ySize,chartData,GCConstants.xOffset,yOffset);
	        trendChannelDataModel = upTrendHelper.determineTrends();
	        upTrendsByMaxPts = trendChannelDataModel.getTrendChannels(GCConstants.NoOfUpTrendChannels);
            upTrends = trendChannelDataModel.getTrendChannelsBySlope(upTrendsByMaxPts,GCConstants.NoOfUpTrendsBySlopeLow,GCConstants.NoOfUpTrendsBySlope);

	        //downTrendHelper = new DownTrendHelper(xSize,ySize,chartData,xOffset,yOffset);
	        //downTrendChannelDataModel = downTrendHelper.determineDownTrends();
            //downTrendsByMaxPts = downTrendChannelDataModel.getDownTrendChannels(GCConstants.NoOfDownTrendChannels);
            //downTrends = downTrendChannelDataModel.getTrendChannelsBySlope(downTrendsByMaxPts,GCConstants.NoOfDownTrendsBySlopeLow,GCConstants.NoOfDownTrendsBySlope);

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    //@SuppressWarnings("unchecked")
	public void paint (Graphics g)
    {
    	try
    	{
	        setBackground( Color.black );
	        Graphics2D g2D;
	        g2D = (Graphics2D) g;

	        drawLine(g2D);


	        if(GCConstants.drawUpTrend)
	        {
	        	//upTrendHelper.drawTrends(g2D);
	        	
	        	//UpTrendStudy upTStudy = new UpTrendStudy(upTrendsByMaxPts);
	 	        //System.err.println("************************************************************");
	 	        //System.out.println("Support = "+upTStudy.getSupport());
	 	        //System.out.println("Target = "+upTStudy.getTarget());
	 	       // System.out.println("Stop = "+upTStudy.getStopPrice());
	 	        //System.err.println("************************************************************");
	 	        
	 	        
	 	        //printRectPoint(g2D,1021.6381, upTStudy.getSupport(),Color.GREEN,"");
	 	        //printRectPoint(g2D,1021.6381, upTStudy.getTarget(),Color.RED,"");
	        	
	 	        
	            for(int i=0;i<upTrendsByMaxPts.size();i++)
	            {
	               ((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(minX,minY,g2D);
	               ((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,maxY,g2D);
	               
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY),g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)*3/4,g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)/2,g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)/4,g2D);
	               ((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawValidTrends(maxX,maxY,minY,g2D);
	               
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)/5,g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)/6,g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)/7,g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,(minY-maxY)/8,g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawDisplacedTrends(maxX,maxY,g2D);
	            	//((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawTrendChannel(g2D);
	               //((UpTrendChannel)(upTrendsByMaxPts.get(i))).drawTrendChannelByMaxPts(g2D);
	            }
	        }

	        if(GCConstants.drawUpTrendBySlope)
	        {
	            for(int i=0;i<upTrends.size();i++)
	            {
	               ((UpTrendChannel)(upTrends.get(i))).drawTrendChannel(g2D);
	            }
	        }

	        if(GCConstants.drawDownTrend)
	        {
		        //downTrendHelper.printTrendRevPoints(g2D);

	        	//downTrendHelper.drawTrends(g2D);
	        	
	            for(int i=0;i<downTrendsByMaxPts.size();i++)
	            {
	            	 ((DownTrendChannel)(downTrendsByMaxPts.get(i))).drawTrendChannel(g2D);
	            }
	        }

	        if(GCConstants.drawDownTrendBySlope)
	        {
	        	
	            for(int i=0;i<downTrends.size();i++)
	            {
	               ((DownTrendChannel)(downTrends.get(i))).drawTrendChannel(g2D);
	            }
	        }
	        
	       
	        
	        
	        
	        
	        
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	       
    }

    private void drawLine(Graphics2D g)
    {
        Line2D closeLine = null;
        for(int k=0;k< chartData.length-1;k++)
        {
            double x1 = chartData[k][0][0];
            double y1 = chartData[k][0][1];

            double x2 = chartData[k+1][0][0];
            double y2 = chartData[k+1][0][1];

            if(y2>y1)
            {
                g.setColor(Color.RED);
            }
            else
            {
                g.setColor(Color.GREEN);
            }

           // System.out.println("x1= "+x1+" y1= "+y1+" x2= "+x2+" y2= "+y2);
            closeLine = new Line2D.Double (x1,y1,x2,y2);
            g.draw(closeLine);
         }
    }
    

    private void createLineChart()
    {
        double close;
        
        //double closeY = 0;
        
        double closeRange = 0;

        ArrayList<StockPriceDataModel> stockPrices = chartDataModel.getStockPrices();

        double lowVal = chartDataModel.getLowPrice();
        
        double highVal = chartDataModel.getHighPrice();

        double adJustedHigh = (Math.ceil(highVal));

        double adJustedLow = (Math.floor(lowVal));

        int range = (int)(adJustedHigh - adJustedLow);

        float ySize = height - 2*GCConstants.yOffSet;
        
        float xSize = width - 2* GCConstants.xOffset;

        float xRange = xSize/stockPrices.size();
        
        double yRange =  Math.floor(ySize/range);
        		
        GCConstants.xOffset = xRange ;
        
        GCConstants.yOffSet = (float)yRange ;
        
        this.yOffset = (float)yRange;
        
        chartData =  new double[stockPrices.size()][1][2];

        for(int j=0;j< stockPrices.size();j++)
        {
            close = ((StockPriceDataModel)(stockPrices.get(j))).getClose();
            
            closeRange = (double)(close - adJustedLow);
            
            chartData[j][0][0] = xRange+(j * xRange);

            chartData[j][0][1] = ySize - ((yRange *  closeRange)-GCConstants.yOffSet) ;// * ((ySize-GCConstants.yOffSet)/range));
            
            if(Math.ceil(close)==adJustedHigh)
            {
            	maxX = chartData[j][0][0];
            	maxY =  chartData[j][0][1];
            }

            if(Math.floor(close)==adJustedLow)
            {
            	minX = chartData[j][0][0];
            	minY =  chartData[j][0][1];
            }


           // System.out.println("closeRange= "+closeRange+" x1= "+ chartData[j][0][0]+" y1= "+ chartData[j][0][1]);
        }
        return;
    }
    
    
    
   
    
    

    private void printRectPoint(Graphics2D g,double x, double y,Color c,String text)
    {
        g.setColor(c);
        Shape Rectangle2D = new java.awt.geom.Rectangle2D.Double (x+1,y,6,6);
        g.draw(Rectangle2D);
        //g.drawString(text+" X="+x+1+";Y="+y,x-5,y+10);
        //g.drawString(""+y,x-5,y+5);
    }
    
    //public void paint ( java.awt.Graphics  gr ) 
    //{ 
    //  gr.drawString ( strpaint , 10 , 20 ); 
    //} 
    
    public void mouseClicked(MouseEvent e) {
        String clickDesc;
        if (e.getClickCount() == 2)
            clickDesc = "double";
        else
            clickDesc = "single";

        System.out.println("Mouse was " + clickDesc + "-clicked at location (" +
            e.getX() + ", " + e.getY() + ")");
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
   
}


/*
 *  Fix XY issue -
 *   Get X,Y for any given date.
 *   
 *  
 *  Draw All channel from Lowest and highest point - Determine lowest price for all channel
 *  Draw All channel from highest point - Determine highest price for all channel
 *  Repeat everything for downchannel
 *  
 *  
 *  
 *  Draw best possible trends in determined channels - arrange in desc order
 * */


















