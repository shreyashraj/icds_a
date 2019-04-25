package com.stayabode.custom.transformers;

/**
 * Created by Arpit on 18-01-2017.
 */

import android.graphics.Color;
import android.view.View;



public class ColorTransformer extends BasePageTransformer {


    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;
        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void transformPage(final View page, final int pageIndex, final float position) {


    }


}