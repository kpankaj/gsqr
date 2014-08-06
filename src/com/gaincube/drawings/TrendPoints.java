package com.gaincube.drawings;

import java.util.HashMap;

public class TrendPoints {

	private HashMap<String,Double> points =null;
	
	public TrendPoints()
	{
		points = new HashMap<String,Double>();
	}
	
	public double getY(String x) {
		try
		{
			return points.get(x);
		} 
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public void add(String x, double y)
	{
		points.put(x, y);
	}
	
	
	
}
