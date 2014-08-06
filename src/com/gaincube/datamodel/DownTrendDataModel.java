/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.datamodel;

import java.util.ArrayList;

import com.gaincube.drawings.DownTrend;

/**
 *
 * @author Pankaj
 */
public class DownTrendDataModel {

    private ArrayList<DownTrend> downTrends;

    public DownTrendDataModel()
    {
        downTrends = new ArrayList<DownTrend>();
    }

    public ArrayList<DownTrend> getTrends() {
        return downTrends;
    }

    public void setTrends(ArrayList<DownTrend> trends) {
        this.downTrends = trends;
    }

    public void addTrend(DownTrend trend)
    {
        if(trend.isActive())
        {
            this.downTrends.add(trend);
            //System.err.println("Added Trend = "+ trend.getSlope());
        }
    }

    public void removeTrend(DownTrend trendToBeDeleted)
    {
        this.downTrends.remove(trendToBeDeleted);
    }

    public void drawChannels()
    {

    }
}

