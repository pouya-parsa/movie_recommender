package com.pouya.digim.svd.libs;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

public class SVD {
    public static SingularValueDecomposition calculate (RealMatrix matrix) {
        SingularValueDecomposition SVD = new SingularValueDecomposition(matrix);
        return SVD;
    }
}
