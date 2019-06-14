package com.pouya.digim.svd;

import android.util.Log;

import com.pouya.digim.svd.MatrixHelper.Estimator;
import com.pouya.digim.svd.MatrixHelper.Matrix;
import com.pouya.digim.svd.SVDHelpers.CutPointCalculate;
import com.pouya.digim.svd.libs.SVD;
import com.pouya.digim.svd.libs.SimilarUsers;
import com.pouya.digim.svd.libs.fillGoalUser;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import smile.clustering.SpectralClustering;

import static com.pouya.digim.svd.libs.SVD.*;

public class SvdHandler {
    public static double[] SvdHandler(double[][] UsersData,double[][]UsergoalData, int goalUserIndex){

//        double [][] data = new double[][] {
//                {3, 3, 3, 3, 4, 4, 3, 4, 6, 4},
//                {3, 3, 3, 3, 4, 4, 3, 4, 6, 4},
//                {3, 3, 3, 3, 4, 4, 3, 4, 6, 4},
//                {3, 3, 3, 3, 4, 4, 3, 4, 6, 4},
//                {10, 10, 10, 10,10, 10,10, 10,10, 10},
//                {10, 10, 10, 10,10, 10,10, 10,10, 10},
//                {10, 10, 10, 10,10, 10,10, 10,10, 10}
//        };

        RealMatrix goalUser = MatrixUtils.createRealMatrix(UsergoalData);


        double [][] usersData ;
        usersData=UsersData;


        RealMatrix dataMatrix = MatrixUtils.createRealMatrix(usersData);


        int cutPoints = CutPointCalculate.CutPointCalculator(calculate(Matrix.removeRow(dataMatrix, goalUserIndex)).getS());


        SpectralClustering spectralClustering = new SpectralClustering(UsersData, cutPoints, 1);
        RealMatrix VTransposedDecomposed = Matrix.decomposeMatrices(
                calculate(dataMatrix).getU(),
                calculate(dataMatrix).getV().transpose(),
                cutPoints);

        RealMatrix likelihood = Estimator.guess(goalUser, VTransposedDecomposed.transpose());

        int goalUserCluster = Matrix.MaximumCell(likelihood);

        int[] clusterLabels = spectralClustering.getClusterLabel();


        RealMatrix SimilarUsersMatrix = SimilarUsers.get(
                dataMatrix,
                clusterLabels,
                goalUserCluster,
                spectralClustering.getClusterSize()[goalUserCluster],
                goalUser.getColumnDimension());


        for(int i = 0; i < goalUser.getColumnDimension(); i++) {
            if(goalUser.getEntry(0, i) == 0) {
                goalUser = fillGoalUser.set(SimilarUsersMatrix, goalUser, i);
            }
        }

        //Log important information

        Log.d("!goalUser", goalUser.toString());
        Log.d("!most", String.valueOf(Matrix.MaximumCell(likelihood)));
        Log.d("!clustering", spectralClustering.toString());

        Log.d("!cut point", String.valueOf(cutPoints));
        return goalUser.getData()[0];
    }
    }
