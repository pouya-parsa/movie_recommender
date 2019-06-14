package com.pouya.digim.svd.MatrixHelper;

import org.apache.commons.math3.linear.RealMatrix;

public class Estimator {
    public static RealMatrix guess(RealMatrix Choosed, RealMatrix V){
        return Choosed.multiply(V);
    }
}
