package com.stayabode.features.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.presenters.AWCListPresenter;
import com.stayabode.features.login.presenters.DashboardPresenter;
import com.stayabode.features.login.views.DashboardView;
import com.stayabode.net.response.getmodels.AdminLoginResponse;
import com.stayabode.net.response.getmodels.QuestionsResponse;

import utils.IntentKeys;
import utils.MixPanelTracker;
import utils.NetUtils;
import utils.SharedPrefManager;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public class DashboardActivity extends BaseActivity implements DashboardView {

    private DashboardPresenter mDashboardPresenter;

    private AutoCompleteTextView mAutocompleteAWC;
    private AdminLoginResponse adminLogin;

    private LinearLayout mLayoutGb;
    private LinearLayout mLayoutAp;
    private LinearLayout mLayoutLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setActionBar();
        disableBackArrow();

        MixPanelTracker.trackEvent(MixPanelTracker.PHONE_VERIFICATION_SCREEN);

        mLayoutGb=(LinearLayout)findViewById(R.id.layout_gb);
        mLayoutAp=(LinearLayout)findViewById(R.id.layout_ap);
        mLayoutLogout=(LinearLayout)findViewById(R.id.layout_logout);

        mDashboardPresenter = new DashboardPresenter();
        mDashboardPresenter.setView(this);;



        Intent adminLoginResponse = getIntent();
        adminLogin = (AdminLoginResponse) adminLoginResponse.getParcelableExtra(IntentKeys.INTENT_LOGINRESP);


        mLayoutAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AWCListActivity.class);
                i.putExtra(IntentKeys.INTENT_LOGINRESP, (Parcelable) adminLogin);
                startActivity(i);
            }
        });

        mLayoutGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, HomeVisitActivity.class);
                i.putExtra(IntentKeys.INTENT_LOGINRESP, (Parcelable) adminLogin);
                startActivity(i);
            }
        });

        mLayoutAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AWCListActivity.class);
                i.putExtra(IntentKeys.INTENT_LOGINRESP, (Parcelable) adminLogin);
                startActivity(i);
            }
        });


        mLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoadingView();

                if (NetUtils.isInternetConnected(DashboardActivity.this)) {
                    String token = SharedPrefManager.getInstance().getAccessToken();
                    mDashboardPresenter.logout(token);
                } else {
                    // showNoInternetView();
                }
            }

        });

    }




    @Override
    public void onLogoutSuccessful() {

        SharedPrefManager.getInstance().setIsLoggedIn(false);
        SharedPrefManager.getInstance().setLoginResponse(null);
        Intent i = new Intent(DashboardActivity.this, AdminLoginActivity.class);
        startActivity(i);
        finish();


    }

    @Override
    public void onRequestFailed(int errorCode, String message) {
        hideLoadingView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("home");
    }

}
