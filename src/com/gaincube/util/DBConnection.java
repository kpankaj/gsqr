package com.gaincube.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
/**
 *
 * @author Pankaj
 */
public class DBConnection {

	public DBCollection getDBConnection()
	{
		DBCollection col = null;
		try{
		 
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "gcube" );
			col = db.getCollection("gcube");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
        return col;
	}
	
	
    public void insertData() {
    
    	BufferedReader br = null;
    	
        try {
        	DBCollection col = getDBConnection();
        	
            br = new BufferedReader(new FileReader("c:\\work\\spy.csv"));
            String line = br.readLine();
            while (line != null) {
            	String[] tokens = line.split(",");
            	BasicDBObject doc = getDBObject("SPY",tokens);
            	col.insert(doc);
                line = br.readLine();
                System.err.println(line);
            }
            br.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        finally {
        }
    	
    }
    
    
    private BasicDBObject getDBObject(String name,String[] tokens)
    {
    	BasicDBObject doc = null;
    	try
    	{
	    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    	Date date = formatter.parse(tokens[1]);
	    	
	    	doc = new BasicDBObject("name",name).
	                append("ID", tokens[0]).
	                append("Date", date).
	                append("Open", tokens[2]).
	                append("High", tokens[3]).
	                append("Low", tokens[4]).
	                append("Close", tokens[5]).
	                append("Volume", tokens[6]).
	                append("AdClose", tokens[7]);
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    			
    	return doc;
    }
    
    public static void main(String a[])
    {
    
        DBConnection md = new DBConnection();
        //md.getConnection();
        md.insertData();
    
    }
    

}



