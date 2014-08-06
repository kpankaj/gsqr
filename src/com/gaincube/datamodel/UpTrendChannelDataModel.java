/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.gaincube.drawings.UpTrendChannel;
import com.gaincube.util.GCConstants;
import com.gaincube.util.GCUpTrendComparator;

/**
 *
 * @author Pankaj
 */
public class UpTrendChannelDataModel
{
	private ArrayList<UpTrendChannel> trendChannels;

	public UpTrendChannelDataModel()
    {
        trendChannels = new ArrayList<UpTrendChannel>();
    }

	public ArrayList<UpTrendChannel> getTrendChannels(int trendCount)
    {
        if(trendCount>trendChannels.size())
            trendCount=trendChannels.size();

        ArrayList<UpTrendChannel> maxPtTrendChannels = new ArrayList<UpTrendChannel>();
        
        Collections.sort(trendChannels,new GCUpTrendComparator());
        
        int k =0;
      
        while((maxPtTrendChannels.size()<=GCConstants.NoOfTrendChannelToShow)&&(k<trendChannels.size()))
        {
        	UpTrendChannel upt = trendChannels.get(k);
        	double sl=  upt.getSlope();
        	
        	boolean sFlag = false;
        	
        	for(int i=0;i<maxPtTrendChannels.size();i++)
        	{
        		if(((sl - maxPtTrendChannels.get(i).getSlope())>-.1) && 
        				((sl - maxPtTrendChannels.get(i).getSlope())<.1))
        		{
        			sFlag = true;
        			break;
        		}
        	}
        	
        	if(sFlag == false)
        		maxPtTrendChannels.add(upt);
        	
        	k++;
        }
               
        return maxPtTrendChannels;
        //getMPtsCountForTrendChannel
    }

    public ArrayList<UpTrendChannel> getTrendChannelsBySlope(int trendCount)
    {
        ArrayList<UpTrendChannel> lowestSlopeTrends = new ArrayList<UpTrendChannel>();

        double matchingCts[] = new double[trendChannels.size()];

        for(int i=0;i<trendChannels.size();i++)
        {
            matchingCts[i] = ((UpTrendChannel)(trendChannels.get(i))).getSlope();
        }

        Arrays.sort(matchingCts);

        for(int k=0;k<trendCount;k++)
        {
        	double mSlope = matchingCts[trendChannels.size()-1-k];

            for(int i=0;i<trendChannels.size();i++)
            {
            	double matchingSlope = ((UpTrendChannel)(trendChannels.get(i))).getSlope();
            	
                if(matchingSlope==mSlope)
                     lowestSlopeTrends.add(((trendChannels.get(i))));
            }
        }

        return lowestSlopeTrends;
        //getMPtsCountForTrendChannel
    }

    public ArrayList<UpTrendChannel> getTrendChannelsBySlope(ArrayList<UpTrendChannel> trends, int trendCountLow,int trendCount)
    {
        ArrayList<UpTrendChannel> lowestSlopeTrends = new ArrayList<UpTrendChannel>();

        if(trendCount>trends.size())
        	trendCount = trends.size();

        double matchingCts[] = new double[trends.size()];

        for(int i=0;i<trends.size();i++)
        {
            matchingCts[i] = ((trends.get(i))).getSlope();
        }

        Arrays.sort(matchingCts);

        for(int k=trendCountLow;k<trendCount;k++)
        {
        	double mSlope = matchingCts[trends.size()-1-k];

            //System.err.println("mSlope"+mSlope);
            for(int i=0;i<trends.size();i++)
            {
            	double matchingSlope = ((UpTrendChannel)(trends.get(i))).getSlope();
                if(matchingSlope==mSlope)
                     lowestSlopeTrends.add(((UpTrendChannel)(trends.get(i))));
            }
        }

        return lowestSlopeTrends;
        //getMPtsCountForTrendChannel
    }

    public void setTrendChannels(ArrayList<UpTrendChannel> trendChannels) {
        this.trendChannels = trendChannels;
    }

    public void addTrendChannel(UpTrendChannel trendChannel)
    {
        if(trendChannel.isActive())
        {
            this.trendChannels.add(trendChannel);
            //System.err.println("Added TrendChannel = "+ trendChannel.getSlope());
        }
    }

    public void removeTrendChannel(UpTrendChannel trendChannel)
    {
        this.trendChannels.add(trendChannel);
    }
}

