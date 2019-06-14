package com.pouya.digim.addedByMohh;

import android.widget.RatingBar;

import com.pouya.digim.R;

public class Rate {
    public static float Rated( RatingBar rB){
        rB.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
                                               {
                                                   @Override
                                                   public void onRatingChanged(RatingBar ratingBar,
                                                                               float rating,
                                                                               boolean fromUser) {
                                                       ratingBar.setRating(rating);
                                                   }
                                               }
        );
        return rB.getRating();
    }
}
