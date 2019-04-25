package utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.stayabode.R;

/**
 * Created by VarunBhalla on 22/11/16.
 */

public class ViewUtils {
    private static boolean snackbarVisible=false;

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


    public static void showSnackBar(Context context, CoordinatorLayout c, String msg) {

        Snackbar snackbar = Snackbar.make(c, msg, Snackbar.LENGTH_SHORT);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        textView.setTextColor(ContextCompat.getColor(context,R.color.white));

        if(!snackbarVisible){
            snackbar.show();
        }

        snackbar.addCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                 snackbarVisible = false;
            }

            @Override
            public void onShown(Snackbar snackbar) {
                snackbarVisible=true;
            }
        });


    }

    public static void showSnackBarAndFinish(final Context context, CoordinatorLayout c, String msg) {

        Snackbar snackbar = Snackbar.make(c, msg, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        textView.setTextColor(ContextCompat.getColor(context,R.color.white));

        if(!snackbarVisible){
            snackbar.show();
        }

        snackbar.addCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                ((Activity) context).finish();
                snackbarVisible = false;
            }

            @Override
            public void onShown(Snackbar snackbar) {
                snackbarVisible=true;
            }
        });


    }

}
