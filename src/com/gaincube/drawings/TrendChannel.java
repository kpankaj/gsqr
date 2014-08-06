/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.drawings;

/**
 *
 * @author Pankaj
 */
public class TrendChannel {

    private float slope;
    private boolean active;
    private Trend mainTrend;
    private Trend upperTrend;
    private Trend firstTrend;
    private Trend secondTrend;
    private Trend thirdTrend;
    private Trend lowerTrend;
    private float lowerY;
    private float upperY;

    public TrendChannel(float slope,Trend mainTrend,float lowerY, float upperY)
    {
        this.slope = slope;
        this.mainTrend = mainTrend;
        this.lowerY = lowerY;
        this.upperY = upperY;
    }
    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Trend getFirstTrend() {
        return firstTrend;
    }

    public void setFirstTrend(Trend firstTrend) {
        this.firstTrend = firstTrend;
    }

    public Trend getLowerTrend() {
        return lowerTrend;
    }

    public void setLowerTrend(Trend lowerTrend) {
        this.lowerTrend = lowerTrend;
    }

    public float getLowerY() {
        return lowerY;
    }

    public void setLowerY(float lowerY) {
        this.lowerY = lowerY;
    }

    public Trend getMainTrend() {
        return mainTrend;
    }

    public void setMainTrend(Trend mainTrend) {
        this.mainTrend = mainTrend;
    }

    public Trend getSecondTrend() {
        return secondTrend;
    }

    public void setSecondTrend(Trend secondTrend) {
        this.secondTrend = secondTrend;
    }

    public float getSlope() {
        return slope;
    }

    public void setSlope(float slope) {
        this.slope = slope;
    }

    public Trend getThirdTrend() {
        return thirdTrend;
    }

    public void setThirdTrend(Trend thirdTrend) {
        this.thirdTrend = thirdTrend;
    }

    public Trend getUpperTrend() {
        return upperTrend;
    }

    public void setUpperTrend(Trend upperTrend) {
        this.upperTrend = upperTrend;
    }

    public float getUpperY() {
        return upperY;
    }

    public void setUpperY(float upperY) {
        this.upperY = upperY;
    }

}
