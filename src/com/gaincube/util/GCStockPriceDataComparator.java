package com.gaincube.util;

import java.util.Comparator;
import java.util.HashMap;

import com.gaincube.datamodel.StockPriceDataModel;

public class GCStockPriceDataComparator implements Comparator<StockPriceDataModel> 
{
    @Override
    public int compare (StockPriceDataModel o1, StockPriceDataModel o2) 
    {
    	if(o2.getClose() > o1.getClose())
    		return 1;
    	else 
    		return -1;
    }
}
