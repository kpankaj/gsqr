package com.gaincube.drawings;

import java.util.ArrayList;

public class TrendExtendor 
{
	private Trend trend= null;
	private double xPos; 
	
	public TrendExtendor(Trend trend,double x)
	{
		this.trend = trend;
		xPos = x;
	}

	public Trend getTrendForXPosition()
	{
		
		return trend;
	}

	
	
	
	
}
