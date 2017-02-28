package com.kalvi_000.tapbutton;

/**
 * Created by kalvi_000 on 1/19/2017.
 */

public class Counter {
    private int mCount;

    public Counter(){
        mCount=0;
    }

    public void addCount(int countBy){
        mCount += countBy;
    }

    public void subCount(boolean negative, int countBy){
        if(mCount >0) {
            mCount -= countBy;
        }else{
            if(negative){
                mCount -= countBy;
            }else {
                mCount = mCount;
            }
        }
    }

    public Integer getCount(){
        return mCount;
    }
}
