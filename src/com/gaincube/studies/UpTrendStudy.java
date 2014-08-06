package com.gaincube.studies;

import java.util.ArrayList;

import com.gaincube.drawings.TrendPoints;
import com.gaincube.drawings.UpTrend;
import com.gaincube.drawings.UpTrendChannel;

public class UpTrendStudy extends GCStudy 
{
    private ArrayList<UpTrend> activeTrends = new ArrayList<UpTrend>();
    private Double supportPrice;
    private Double targetPrice;

	
	public UpTrendStudy(ArrayList<UpTrendChannel> upTrendsByMaxPts)
	{
		for(int i=0;i<upTrendsByMaxPts.size();i++)
        {
			//activeTrends.addAll(((UpTrendChannel)(upTrendsByMaxPts.get(i))).getActiveTrends());
        }
	}
	
	public Double getTarget()
	{
		Double trendPrice = 0.0;
		
		for(int i=0;i<this.activeTrends.size();i++)
	    {
			
			UpTrend upTrend = (UpTrend)(this.activeTrends.get(i));
			
			TrendPoints points = upTrend.getPoints();
			
			trendPrice = points.getY("1021.6381");
			
			if( (i==0) || ((trendPrice!=-1) && (targetPrice < trendPrice))) 
				targetPrice = trendPrice;
	    }
		return targetPrice;
		
	}
	
	public Double getSupport()
	{
		Double trendPrice = 0.0;
		
		for(int i=0;i<this.activeTrends.size();i++)
	    {
			UpTrend upTrend = (UpTrend)(this.activeTrends.get(i));
			
			TrendPoints points = upTrend.getPoints();
			
			trendPrice = points.getY("1021.6381");
			
			if( (i==0) || ((trendPrice!=-1) && (supportPrice > trendPrice)) )
				supportPrice = trendPrice;
	    }
		return supportPrice;
	}
	
	public Double getStopPrice()
	{
		return supportPrice - (supportPrice * 5/100);
	}
	
}
