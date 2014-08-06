package com.gaincube.dao;

import java.util.ArrayList;
import java.util.Date;

import com.gaincube.datamodel.StockPriceDataModel;
import com.gaincube.util.DBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ChartDataDAO
{
	ArrayList<Double> closePrices = new ArrayList<Double>();
	
	ArrayList<StockPriceDataModel> stockPrices = new ArrayList<StockPriceDataModel>();

	public ArrayList<Double> getClosePrices() {
		return closePrices;
	}


	public void setClosePrices(ArrayList<Double> closePrices) {
		this.closePrices = closePrices;
	}


	public ArrayList<StockPriceDataModel> getStockPrices() {
		return stockPrices;
	}


	public void setStockPrices(ArrayList<StockPriceDataModel> stockPrices) {
		this.stockPrices = stockPrices;
	}


	public void setPriceForRange(Date startDate,Date endDate)
    {
    	try
        {
            DBConnection dbCon = new DBConnection();
            
            DBCollection col= dbCon.getDBConnection();
            
            System.out.println(col.getCount());
      	  	
      	  	BasicDBObject query = new BasicDBObject(); 
      	  	
      	    DBCursor curs = col.find(query);
      	    
    	    curs =  col.find(new BasicDBObject().append("Date", new BasicDBObject("$gt", startDate).append("$lt", endDate)))
    			   .sort((new BasicDBObject("_id",1)));//.limit(range);
    	    		
      	  	
          	StockPriceDataModel stockPriceModel = null;
      	  	
      	  	while(curs.hasNext()) {
      	  		BasicDBObject obj = (BasicDBObject) curs.next();
      	  		
      	  		if(obj!=null)
      	  		{
      	  			if(obj.getString("Close")!=null)
      	  			{
      	  				try
      	  				{
      	  					stockPriceModel = new StockPriceDataModel(); 
      	  					stockPriceModel.setClose(Double.parseDouble(obj.getString("Close")));
      	  					stockPriceModel.setOpen(Double.parseDouble(obj.getString("Open")));
      	  					stockPriceModel.setLow(Double.parseDouble(obj.getString("Low")));
      	  					stockPriceModel.setHigh(Double.parseDouble(obj.getString("High")));
      	  					
      	  					closePrices.add(Double.parseDouble(obj.getString("Close")));
      	  					
      	  					stockPrices.add(stockPriceModel);
      	  				}catch(NumberFormatException ne)
      	  				{
      	  					ne.printStackTrace();
      	  				}
      	  						
      	  			}
      	  		}
      	  	}
      	  	
        }
        catch( Exception e )
        {
            System.err.println( e );
        }
        finally
        {
           
        }
    	
    }

    
   /* public ArrayList getClosePrices(int range)
    {
    	ArrayList closePrices = new ArrayList();
        
    	try
        {
        	Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2008-19-16T10:05:21.968"); 
            
      	  	Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2010-5-10T10:05:21.968"); 
      	  	
            DBConnection dbCon = new DBConnection();
            
            DBCollection col= dbCon.getDBConnection();
        	
            System.out.println(col.getCount());
      	  	
      	  	BasicDBObject query = new BasicDBObject(); 
      	  	
      	  	BasicDBObject fields = new BasicDBObject("Close",true).append("_id",false);

      	  	DBCursor curs = col.find(query);
      	  
      	  	curs =  col.find(new BasicDBObject().append("Date", new BasicDBObject("$gt", startDate).append("$lt", endDate)))
   			   .sort((new BasicDBObject("_id",1)));//.limit(range);
      	  	
      	  	curs = col.find(query, fields).sort(new BasicDBObject("_id",1)).limit(range);
      	  	
            //double[] closePrices = new double[range];

      	  	int i=0;
      	  	
      	  	while(curs.hasNext()) 
      	  	{
      	  		BasicDBObject obj = (BasicDBObject) curs.next();
      	  		
      	  		if(obj!=null)
      	  		{
      	  			if(obj.getString("Close")!=null)
      	  			{
      	  				try
      	  				{
      	  					//closePrices[i] = Double.parseDouble(obj.getString("Close"));
      	  					//System.out.println("i= "+i+" "+Double.parseDouble(obj.getString("Close")));
      	  					closePrices.add(Double.parseDouble(obj.getString("Close")));
      	  					
      	  					i++;
      	  				}catch(NumberFormatException ne)
      	  				{
      	  					ne.printStackTrace();
      	  				}
      	  						
      	  			}
      	  		}
      	  	}
      	  	
           return closePrices;

        }
        catch( Exception e )
        {
            System.err.println( e );
        }
        finally
        {
           
        }
        return null;
    }*/

    
    public static void main(String a[])
    {
    
       //ChartDataDAO md = new ChartDataDAO();
        //md.getConnection();
       // md.getStockPricesForRange(20);
    
    }
    
}