/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.gaincube.dao.ChartDataDAO;

/**
 *
 * @author Pankaj
 */
public class ChartDataModel {

	ArrayList<StockPriceDataModel> stockPrices;
	ArrayList<Double> closePrices = null;
    Object[] sortedClosePrices = null;

    public ChartDataModel(Date startDate,Date endDate)
    {
        ChartDataDAO chartDataDAO = new ChartDataDAO();
        
        chartDataDAO.setPriceForRange(startDate,endDate);
        
        stockPrices = chartDataDAO.getStockPrices();
        
        closePrices = chartDataDAO.getClosePrices();

        sortedClosePrices = closePrices.toArray();
        
        Arrays.sort(sortedClosePrices);
    }

    public ArrayList<StockPriceDataModel> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(ArrayList<StockPriceDataModel> stockPrices) {
        this.stockPrices = stockPrices;
    }

    public double getLowPrice()
    {
    	return (double)sortedClosePrices[0];
    }

    public double getHighPrice()
    {
    	return (double)sortedClosePrices[sortedClosePrices.length-1];
    }
    
    public double getXForADay()
    {
    	return 1000;
    }

}



