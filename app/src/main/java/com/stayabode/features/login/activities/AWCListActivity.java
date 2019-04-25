package com.stayabode.features.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.facebook.share.Share;
import com.google.gson.Gson;
import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.presenters.AWCListPresenter;
import com.stayabode.features.login.views.AWCListView;
import com.stayabode.net.response.getmodels.AdminLoginResponse;
import com.stayabode.net.response.getmodels.QuestionsResponse;

import java.util.ArrayList;

import utils.IntentKeys;
import utils.MixPanelTracker;
import utils.NetUtils;
import utils.SharedPrefManager;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public class AWCListActivity extends BaseActivity implements AWCListView {

    private AWCListPresenter mAWCListPresenter;
    private CoordinatorLayout mCoordinatorLayout;



    private AutoCompleteTextView mAutocompleteAWC;
    private AdminLoginResponse adminLogin;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awc_list);

        setActionBar();
        disableBackArrow();

        MixPanelTracker.trackEvent(MixPanelTracker.PHONE_VERIFICATION_SCREEN);


        mAutocompleteAWC = (AutoCompleteTextView) findViewById(R.id.ac_awc);
        mBtnSubmit = (Button) findViewById(R.id.button_submit);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);


        Intent adminLoginResponse = getIntent();
        adminLogin = (AdminLoginResponse) adminLoginResponse.getParcelableExtra(IntentKeys.INTENT_LOGINRESP);


        final ArrayList AWCList =new ArrayList<>();


        for(int i=0;i<adminLogin.getData().size();i++){
            AWCList.add(adminLogin.getData().get(i));
        }

        final String []AWCArray = new String[AWCList.size()];
        AWCList.toArray(AWCArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, AWCArray);
        mAutocompleteAWC.setThreshold(1); //will start working from first character
        mAutocompleteAWC.setAdapter(adapter);

        mAWCListPresenter = new AWCListPresenter();
        mAWCListPresenter.setView(this);

        mBtnSubmit.setEnabled(true);


        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = mAutocompleteAWC.getText().toString().trim();

                if(AWCList.contains(userInput) && userInput.length()>0) {
                    showLoadingView();

                    if (NetUtils.isInternetConnected(AWCListActivity.this)) {
                        String token = SharedPrefManager.getInstance().getAccessToken();
                        mAWCListPresenter.getQuestions(token);
                    } else {
                        // showNoInternetView();
                    }
                }else{
                    ViewUtils.showSnackBar(AWCListActivity.this, mCoordinatorLayout, "कृपया सही आंगनवाड़ी कोड दर्ज करें");
                }
            }
        });
    }




    @Override
    public void onLoginSuccessful(QuestionsResponse questionsResponse) {



        final Gson g = new Gson();
        final String mQuestionsResponseString = g.toJson(questionsResponse);

        SharedPrefManager.getInstance().setQuestionsResponse(mQuestionsResponseString);



        hideLoadingView();

        Intent i = new Intent(AWCListActivity.this, QuestionsActivity.class);
        i.putExtra(IntentKeys.INTENT_QUESTIONSRESP, (Parcelable) questionsResponse);
        startActivity(i);


    }

    @Override
    public void onRequestFailed(int errorCode, String message) {
        hideLoadingView();
        ViewUtils.showSnackBar(AWCListActivity.this, mCoordinatorLayout, message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle(getResources().getString(R.string.label_login));
    }

}
