/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pankaj
 */
public class Trend {

	 protected double xPos;
	 protected double yPos;
	 protected double endX;
	 protected double endY;

	 protected double slope;
	 ArrayList<HashMap<String, Double>> trendReversalPoints;
	    
	 protected int yPointsCount;
	 protected ArrayList<double[][]> yList;

	 protected int matchPointsCount = 0;
	 protected ArrayList<double[][]> matchingYList;

	 protected int direction;
	 protected boolean resistanceFlag;
	 protected boolean supportFlag;
	 protected boolean active;

	 protected double projectedValue;
	 protected double weightage;
	 protected double currentY;
	    
	 protected int ySize;
	 protected int xSize;
	 protected Color trendColor;
	
	 protected float xOffset;
	 protected float yOffset;

	 protected TrendPoints points;

	    
	 public Trend()
	 {
		
	 }
	 
	 public Trend(double x, double y, double slope,double currY,int ySize, int xSize,float xOffset,float yOffset,ArrayList<HashMap<String, Double>> trendReversalPoints)
	 {
		this.xPos = x;
	    this.yPos = y;
	    this.slope = slope;
	    this.ySize = ySize;
	    this.xSize = xSize;
	    this.trendReversalPoints = trendReversalPoints;
	    this.currentY = currY;
	    this.xOffset = xOffset;
	    this.yOffset = yOffset;
	    this.points = new TrendPoints();

	 }
	
	
	public Trend(double x1, double y1, double x2, double y2,double currY,int ySize, int xSize,float xOffset,float yOffset,ArrayList<HashMap<String, Double>> trendReversalPoints)
	{
		this.xPos = x1;
	    this.yPos = y1;
	    this.ySize = ySize;
	    this.xSize = xSize;
	    this.xOffset = xOffset;
	    this.yOffset = yOffset;
	    this.points = new TrendPoints();
	    this.currentY = currY;
	    this.trendReversalPoints = trendReversalPoints;
    }
	 
	 
	public double getXPos() {
		return xPos;
	}
	
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	public double getEndX() {
		return endX;
	}
	public void setEndX(double endX) {
		this.endX = endX;
	}
	public double getEndY() {
		return endY;
	}
	public void setEndY(double endY) {
		this.endY = endY;
	}
	public double getSlope() {
		return slope;
	}
	public void setSlope(double slope) {
		this.slope = slope;
	}
	public ArrayList<HashMap<String, Double>> getTrendReversalPoints() {
		return trendReversalPoints;
	}
	public void setTrendReversalPoints(
			ArrayList<HashMap<String, Double>> trendReversalPoints) {
		this.trendReversalPoints = trendReversalPoints;
	}
	public int getyPointsCount() {
		return yPointsCount;
	}
	public void setyPointsCount(int yPointsCount) {
		this.yPointsCount = yPointsCount;
	}
	public ArrayList<double[][]> getyList() {
		return yList;
	}
	public void setyList(ArrayList<double[][]> yList) {
		this.yList = yList;
	}
	public int getMatchPointsCount() {
		return matchPointsCount;
	}
	public void setMatchPointsCount(int matchPointsCount) {
		this.matchPointsCount = matchPointsCount;
	}
	public ArrayList<double[][]> getMatchingYList() {
		return matchingYList;
	}
	public void setMatchingYList(ArrayList<double[][]> matchingYList) {
		this.matchingYList = matchingYList;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isResistanceFlag() {
		return resistanceFlag;
	}
	public void setResistanceFlag(boolean resistanceFlag) {
		this.resistanceFlag = resistanceFlag;
	}
	public boolean isSupportFlag() {
		return supportFlag;
	}
	public void setSupportFlag(boolean supportFlag) {
		this.supportFlag = supportFlag;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public double getProjectedValue() {
		return projectedValue;
	}
	public void setProjectedValue(double projectedValue) {
		this.projectedValue = projectedValue;
	}
	public double getWeightage() {
		return weightage;
	}
	public void setWeightage(double weightage) {
		this.weightage = weightage;
	}
	public double getCurrentY() {
		return currentY;
	}
	public void setCurrentY(double currentY) {
		this.currentY = currentY;
	}
	public int getYSize() {
		return ySize;
	}
	public void setYSize(int ySize) {
		this.ySize = ySize;
	}
	public int getXSize() {
		return xSize;
	}
	public void setXSize(int xSize) {
		this.xSize = xSize;
	}
	public Color getTrendColor() {
		return trendColor;
	}
	public void setTrendColor(Color trendColor) {
		this.trendColor = trendColor;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	public TrendPoints getPoints() {
		return points;
	}

	public void setPoints(TrendPoints points) {
		this.points = points;
	}

	 
   
}
