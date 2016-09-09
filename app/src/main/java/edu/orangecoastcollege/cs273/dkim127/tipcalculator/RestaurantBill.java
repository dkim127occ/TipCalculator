package edu.orangecoastcollege.cs273.dkim127.tipcalculator;

/**
 * Created by casque on 9/8/16.
 */
public class RestaurantBill
{
    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public RestaurantBill()
    {
        mAmount = 0;
        mTipPercent = 0;
        mTipAmount = 0;
        mTotalAmount = 0;
    }

    public RestaurantBill(double amount, double percent)
    {
        mAmount = amount;
        mTipPercent = percent;
        recalculateAmounts();
    }

    public double getAmount()
    {
        return mAmount;
    }
    public double getTipPercent()
    {
        return mTipPercent;
    }
    public double getTipAmount()
    {
        return mTipAmount;
    }
    public double getTotalAmount()
    {
        return mTotalAmount;
    }

    public void setAmount(double amount)
    {
        mAmount = amount;
        recalculateAmounts();
    }
    public void setTipPercent(double percent)
    {
        mTipPercent = percent;
        recalculateAmounts();
    }

    private void recalculateAmounts()
    {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount = mAmount + mTipAmount;
    }

}
