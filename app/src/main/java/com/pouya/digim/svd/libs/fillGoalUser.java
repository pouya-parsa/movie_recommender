package com.pouya.digim.svd.libs;

import org.apache.commons.math3.linear.RealMatrix;

public class fillGoalUser {
    public static RealMatrix set(RealMatrix similarUsers, RealMatrix goalUser, int column) {
        double sum = 0;
        for(int i = 0; i < similarUsers.getRowDimension(); i++) {
            sum += similarUsers.getRow(i)[column];
        }

        goalUser.setEntry(0, column, sum / similarUsers.getRowDimension());

        return goalUser;
    }
}
