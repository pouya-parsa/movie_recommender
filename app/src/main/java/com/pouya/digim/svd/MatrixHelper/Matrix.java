package com.pouya.digim.svd.MatrixHelper;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Matrix {
    static RealMatrix DecomposeMatrixU;
    static RealMatrix DecomposeMatrixVTranpose;


    public static RealMatrix removeRow(RealMatrix matrix, int rowToDelete) {

        RealMatrix newMatrix = MatrixUtils.createRealMatrix(matrix.getRowDimension(), matrix.getColumnDimension());

        int rowCounter = 0;

        for (int row = 0; row < newMatrix.getRowDimension(); row++) {
            for (int column = 0; column < newMatrix.getColumnDimension(); column++) {
                if (row != rowToDelete) {
                    newMatrix.setRow(rowCounter, matrix.getRow(row));
                    rowCounter++;
                }
            }
        }
        return newMatrix;

    }


    public static RealMatrix decomposeMatrices(RealMatrix U, RealMatrix VTranspose, int Numbers) {
        double[][] DecomposeOfU;
        double[][] DecomposeOfVTranpose;
        DecomposeOfU = new double[U.getRowDimension()][Numbers];
        DecomposeOfVTranpose = new double[Numbers][VTranspose.getColumnDimension()];
        for (int i = 0; i < Numbers; i++) {
            for (int j = 0; j < VTranspose.getColumnDimension(); j++) {
                DecomposeOfVTranpose[i][j] = VTranspose.getEntry(i, j);
            }
        }
        for (int i = 0; i < Numbers; i++) {
            for (int j = 0; j < U.getRowDimension(); j++) {
                DecomposeOfU[j][i] = U.getEntry(j, i);
            }
        }

        DecomposeMatrixU = MatrixUtils.createRealMatrix(DecomposeOfU);
        DecomposeMatrixVTranpose = MatrixUtils.createRealMatrix(DecomposeOfVTranpose);

        return DecomposeMatrixVTranpose;
    }

    //Expand matrix by one row
    public static RealMatrix Expand(RealMatrix matrix, double[] newUser) {
        RealMatrix newMatrix = MatrixUtils.createRealMatrix(matrix.getRowDimension() + 1, matrix.getColumnDimension());

        newMatrix.setSubMatrix(matrix.getData(), 0, 0);
        newMatrix.setRow(matrix.getRowDimension(), newUser);

        return newMatrix;
    }


    //input matrix is not special but it returns column number
    public static int MaximumCell(RealMatrix matrix) {

        double maximum = matrix.getEntry(0, 0);
        int index = 0;

        for (int row = 0; row < matrix.getRowDimension(); row++) {
            for (int column = 0; column < matrix.getColumnDimension(); column++) {
                maximum = maximum < matrix.getEntry(row, column) ? matrix.getEntry(row, column) : maximum;
                index = maximum < matrix.getEntry(row, column) ? column : index;
            }
        }
        return index;
    }
}
