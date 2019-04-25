package com.stayabode.custom.views;

/**
 * Created by Arpit on 2/1/17.
 */


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.Button;

import com.stayabode.custom.behaviour.MoveUpwardBehavior;

@CoordinatorLayout.DefaultBehavior(MoveUpwardBehavior.class)
public class MovableButton extends Button {
    public MovableButton(Context context) {
        super(context);
    }

    public MovableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}