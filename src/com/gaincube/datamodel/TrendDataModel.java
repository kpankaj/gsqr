
package com.gaincube.datamodel;

import java.util.ArrayList;

import com.gaincube.drawings.Trend;

/**
 *
 * @author Pankaj
 */
public class TrendDataModel {

    private ArrayList<Trend> trends;

    public ArrayList<Trend> getTrends() {
        return trends;
    }

    public void setTrends(ArrayList<Trend> trends) {
        this.trends = trends;
    }

    public void addTrend(Trend trendToBeAdded)
    {
        this.trends.add(trendToBeAdded);
    }

    public void removeTrend(Trend trendToBeDeleted)
    {
        this.trends.remove(trendToBeDeleted);
    }

}
