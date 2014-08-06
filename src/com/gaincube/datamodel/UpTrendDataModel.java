/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.datamodel;

import com.gaincube.drawings.UpTrend;
import java.util.ArrayList;

/**
 *
 * @author Pankaj
 */
public class UpTrendDataModel {

    private ArrayList<UpTrend> trends;

    public UpTrendDataModel()
    {
        trends = new ArrayList<UpTrend>();
    }

    public ArrayList<UpTrend> getTrends() {
        return trends;
    }

    public void setTrends(ArrayList<UpTrend> trends) {
        this.trends = trends;
    }

    public void addTrend(UpTrend trend)
    {
        if(trend.isActive())
        {
            this.trends.add(trend);
            //System.err.println("Added Trend = "+ trend.getSlope());
        }
    }

    public void removeTrend(UpTrend trendToBeDeleted)
    {
        this.trends.remove(trendToBeDeleted);
    }

    public void drawChannels()
    {

    }
}


