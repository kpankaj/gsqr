/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaincube.datamodel;

/**
 *
 * @author Pankaj
 */
public class StockPriceDataModel {

    private double open;
    private double high;
    private double close;
    private double low;

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

}


