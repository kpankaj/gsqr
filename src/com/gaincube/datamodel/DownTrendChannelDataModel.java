/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.gaincube.drawings.DownTrendChannel;
import com.gaincube.drawings.UpTrendChannel;
import com.gaincube.util.GCConstants;
import com.gaincube.util.GCDownTrendComparator;

/**
 *
 * @author Pankaj
 */
public class DownTrendChannelDataModel {

    private ArrayList<DownTrendChannel> trendChannels;

    public DownTrendChannelDataModel()
    {
    	trendChannels = new ArrayList<DownTrendChannel>();
    }

    
 	public ArrayList<DownTrendChannel> getDownTrendChannels(int trendCount)
    {
        if(trendCount>trendChannels.size())
            trendCount=trendChannels.size();

        ArrayList<DownTrendChannel> maxPtTrendChannels = new ArrayList<DownTrendChannel>();
        
        Collections.sort(trendChannels,new GCDownTrendComparator());
        
        int k =0;
      
        while((maxPtTrendChannels.size()<=GCConstants.NoOfTrendChannelToShow)&&(k<trendChannels.size()))
        {
        	DownTrendChannel upt = trendChannels.get(k);
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
    
 	 public ArrayList<DownTrendChannel> getTrendChannelsBySlope(int trendCount)
     {
         ArrayList<DownTrendChannel> lowestSlopeTrends = new ArrayList<DownTrendChannel>();

         double matchingCts[] = new double[trendChannels.size()];

         for(int i=0;i<trendChannels.size();i++)
         {
             matchingCts[i] = ((DownTrendChannel)(trendChannels.get(i))).getSlope();
         }

         Arrays.sort(matchingCts);

         for(int k=0;k<trendCount;k++)
         {
         	double mSlope = matchingCts[trendChannels.size()-1-k];

             for(int i=0;i<trendChannels.size();i++)
             {
             	double matchingSlope = ((DownTrendChannel)(trendChannels.get(i))).getSlope();
             	
                 if(matchingSlope==mSlope)
                      lowestSlopeTrends.add(((trendChannels.get(i))));
             }
         }

         return lowestSlopeTrends;
         //getMPtsCountForTrendChannel
     }

 	 
   

    public ArrayList<DownTrendChannel> getTrendChannelsBySlope(ArrayList<DownTrendChannel> trends, int trendCountLow,int trendCount)
    {
        ArrayList<DownTrendChannel> lowestSlopeTrends = new ArrayList<DownTrendChannel>();

        double matchingCts[] = new double[trends.size()];

        for(int i=0;i<trends.size();i++)
        {
            matchingCts[i] = ((DownTrendChannel)(trends.get(i))).getSlope();
        }

        Arrays.sort(matchingCts);

        for(int k=trendCountLow;k<trendCount;k++)
        {
        	double mSlope = matchingCts[k];

            System.err.println("mSlope"+mSlope);

            for(int i=0;i<trends.size();i++)
            {
            	double matchingSlope = ((DownTrendChannel)(trends.get(i))).getSlope();

                if(matchingSlope==mSlope)
                     lowestSlopeTrends.add(((DownTrendChannel)(trends.get(i))));
            }
        }

        return lowestSlopeTrends;
    }


    public void addTrendChannel(DownTrendChannel trendChannel)
    {
        if(trendChannel.isActive())
        {
            this.trendChannels.add(trendChannel);
        }
    }

    public void removeTrendChannel(DownTrendChannel trendChannel)
    {
        this.trendChannels.add(trendChannel);
    }
}



/*
public ArrayList<DownTrendChannel> getTrendChannels(int trendCount)
{
    if(trendCount>trendChannels.size())
        trendCount=trendChannels.size();

    //System.err.println("trendCount "+trendCount);

    ArrayList<DownTrendChannel> maxPtTrends = new ArrayList<DownTrendChannel>();

    int matchingCts[] = new int[trendChannels.size()];

    for(int i=0;i<trendChannels.size();i++)
    {
        matchingCts[i] = ((DownTrendChannel)(trendChannels.get(i))).getMPtsCountForTrendChannel();
    }

    Arrays.sort(matchingCts);

    for(int k=0;k<trendCount;k++)
    {
        int mPt = matchingCts[trendChannels.size()-1-k];

        //System.err.println("mPt "+mPt);

        for(int i=0;i<trendChannels.size();i++)
        {
            int matchingPt = ((DownTrendChannel)(trendChannels.get(i))).getMPtsCountForTrendChannel();

            //System.err.println("matchingPt "+matchingPt);

            if(matchingPt==mPt)
            {
                 maxPtTrends.add(((DownTrendChannel)(trendChannels.get(i))));

                 if(maxPtTrends.size()==trendCount)
                 {
                     k=trendCount;

                     break;
                 }
            }
        }
    }

    return maxPtTrends;
}


public ArrayList<DownTrendChannel> getTrendChannelsBySlope(int trendCount)
{
    ArrayList<DownTrendChannel> lowestSlopeTrends = new ArrayList<DownTrendChannel>();

    double matchingCts[] = new double[trendChannels.size()];

    for(int i=0;i<trendChannels.size();i++)
    {
        matchingCts[i] = ((DownTrendChannel)(trendChannels.get(i))).getSlope();
    }

    Arrays.sort(matchingCts);

    for(int k=0;k<trendCount;k++)
    {
    	double mSlope = matchingCts[k];

        for(int i=0;i<trendChannels.size();i++)
        {
        	double matchingSlope = ((DownTrendChannel)(trendChannels.get(i))).getSlope();

            if(matchingSlope==mSlope)
                 lowestSlopeTrends.add(((DownTrendChannel)(trendChannels.get(i))));
        }
    }

    return lowestSlopeTrends;
}*/
