package com.pouya.digim.svd.SVDHelpers;

import android.util.Log;

import org.apache.commons.math3.linear.RealMatrix;

public class CutPointCalculate {
    public static int CutPointCalculator(RealMatrix Sigma) {
        double sum = 0;
        double SumArraySelected = 0;
        int NumberOfArraySelected;
        for (int i = 0; i < Sigma.getRowDimension(); i++) {
            sum += Math.pow(Sigma.getEntry(i, i), 2);
        }
        for (NumberOfArraySelected = 0; NumberOfArraySelected < Sigma.getRowDimension(); NumberOfArraySelected++) {
            SumArraySelected += Math.pow(Sigma.getEntry(NumberOfArraySelected, NumberOfArraySelected), 2);


            if (SumArraySelected / sum > 0.9) {
                Log.d("!percent", String.valueOf(SumArraySelected / sum));
                SumArraySelected -= Math.pow(Sigma.getEntry(NumberOfArraySelected, NumberOfArraySelected), 2);
                if (SumArraySelected / sum >= 0.8 && SumArraySelected / sum <= 0.9) {
                    Log.d("!percent", String.valueOf(SumArraySelected / sum));

                    NumberOfArraySelected--;
                    break;
                } else {
                    break;
                }
//                break;
            }
        }
        Log.d("!!", String.valueOf(NumberOfArraySelected + 1));
        return NumberOfArraySelected + 1;
    }

}
