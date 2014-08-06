package com.gaincube.util;

import java.util.Comparator;

import com.gaincube.drawings.DownTrendChannel;

public class GCDownTrendComparator implements Comparator<DownTrendChannel> 
{
    @Override
    public int compare (DownTrendChannel o1, DownTrendChannel o2) 
    {
    	if(o2.getMPtsCountForTrendChannel() > o1.getMPtsCountForTrendChannel())
    		return 1;
    	else 
    		return -1;
    }
}
