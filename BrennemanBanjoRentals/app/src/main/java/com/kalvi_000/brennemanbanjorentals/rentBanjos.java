package com.kalvi_000.brennemanbanjorentals;

/**
 * Created by kalvi_000 on 1/24/2017.
 */

public class rentBanjos {

    //constants
    static final double TAXRATE = 0.05;
    static final double BANJORATE = 9.0;
    static final double CASERATE = 1.0;
    static final double INSURANCERATE = 2.00;
    static final double MANDOLINRATE = 7.0;
    static final double VIOLIONRATE = 10.0;
    static final double UKULELERATE = 5.0;
    //variables
    private double mBanjoCost;
    private double mCaseCost;
    private double mTaxes;
    private double mCost;
    private double mTotal;
    private double mInsurance;
    private int instruments;
    private int cases;
    private boolean insuranceTaken;
    private double instrumentRate;

    public rentBanjos() {
        mBanjoCost = 0.0;
        mCaseCost = 0.0;
        mInsurance = 0.0;
        insuranceTaken = false;
        instruments = 0;
        cases = 0;
        instrumentRate = BANJORATE;
    }

    void updatePrice() {
        mCost = (instruments * instrumentRate) + (cases * CASERATE);
        if (insuranceTaken)
            mInsurance = instruments * INSURANCERATE;
        else
            mInsurance = 0.0;
        mTaxes = (mCost + mInsurance) * TAXRATE;
        mTotal = mCost + mTaxes + mInsurance;
    }

    void updateBanjos(int inputBanjos) {
        instruments = inputBanjos;
        updatePrice();
    }

    void updateCases(int inputCases) {
        cases = inputCases;
        updatePrice();
    }

    void updateInsurance(boolean taken) {
        insuranceTaken = taken;
        updatePrice();
    }
    void updateInstrumentSelected(int itemSelected){
        switch (itemSelected){
            case 0:
                instrumentRate =BANJORATE;
                break;
            case 1:
                instrumentRate = MANDOLINRATE;
                break;
            case 2:
                instrumentRate = VIOLIONRATE;
                break;
            case 3:
                instrumentRate = UKULELERATE;
                break;
        }
        updatePrice();
    }

    public double getTax() {
        return mTaxes;
    }

    public double getRental() {
        return mCost;
    }

    public double getTotal() {
        return mTotal;
    }

    public double getinsurance() {
        return mInsurance;
    }
}
