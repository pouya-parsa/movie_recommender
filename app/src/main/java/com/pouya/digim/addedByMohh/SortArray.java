package com.pouya.digim.addedByMohh;

import com.pouya.digim.MovieModel;

import java.util.List;

public class SortArray {
    public static List<MovieModel> Sorting(List<MovieModel> movies){
        MovieModel change;
        for (int i = 1; i <movies.size() ; i++) {
            for (int j = 0; j <i ; j++) {
                if(movies.get(j+1).getRate()>movies.get(j).getRate()){
                    change=movies.get(j);
                    movies.set(j,movies.get(j+1));
                    movies.set(j+1,change);
                }
            }
        }
        return  movies;
    }
}
