
package com.gaincube.datamodel;

import java.util.ArrayList;

import com.gaincube.drawings.TrendChannel;

/**
 *
 * @author Pankaj
 */
public class TrendChannelDataModel {

    private ArrayList<TrendChannel> trendChannels;

    public ArrayList<TrendChannel> getTrendChannels() {
        return trendChannels;
    }

    public void setTrendChannels(ArrayList<TrendChannel> trendChannels) {
        this.trendChannels = trendChannels;
    }

    public void addTrendChannel(TrendChannel trendChannel)
    {
        this.trendChannels.add(trendChannel);
    }

    public void removeTrendChannel(TrendChannel trendChannel)
    {
        this.trendChannels.add(trendChannel);
    }
}
