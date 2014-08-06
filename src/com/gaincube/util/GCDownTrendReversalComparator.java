package com.gaincube.util;

import java.util.Comparator;
import java.util.HashMap;

public class GCDownTrendReversalComparator implements Comparator<HashMap<String, Double>> 
{
    @Override
    public int compare (HashMap<String, Double> o1, HashMap<String, Double> o2) 
    {
    	if(o1.get("Y") > o2.get("Y"))
    		return 1;
    	else 
    		return -1;
    }
}
