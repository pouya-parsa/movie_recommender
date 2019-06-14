package com.pouya.digim.svd.libs;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class SimilarUsers {
    public static RealMatrix get(RealMatrix dataMatrix, int[] clusterLabels , int goalUserClusterNumber, int similarClusterSize, int userSize) {


        int similarUserCounter = 0;
        RealMatrix similarUsers = MatrixUtils.createRealMatrix(similarClusterSize, userSize);

        for(int user = 0; user < clusterLabels.length; user++) {
            if(goalUserClusterNumber == clusterLabels[user]) {
                similarUsers.setRowMatrix(similarUserCounter, dataMatrix.getRowMatrix(user));
                similarUserCounter++;
            }
        }

        return similarUsers;
    }
}
