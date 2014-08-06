package com.gaincube.util;

import java.util.Comparator;

import com.gaincube.drawings.UpTrend;

public class GCUpTMatchingPtComparator implements Comparator<UpTrend> 
{
    @Override
    public int compare (UpTrend o1, UpTrend o2) 
    {
    	if(o2.getMatchPointsCount() > o1.getMatchPointsCount())
    		return 1;
    	else if (o2.getMatchPointsCount() == o1.getMatchPointsCount())
    		return 0;
    	else 
    		return -1;
    }
}
