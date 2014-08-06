package com.gaincube.util;

import java.util.Comparator;

import com.gaincube.drawings.UpTrendChannel;

public class GCUpTrendComparator implements Comparator<UpTrendChannel> 
{
    @Override
    public int compare (UpTrendChannel o1, UpTrendChannel o2) 
    {
    	if(o2.getMPtsCountForTrendChannel() > o1.getMPtsCountForTrendChannel())
    		return 1;
    	else 
    		return -1;
    }
}
