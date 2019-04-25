package com.stayabode.features.login.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.presenters.AdminLoginPresenter;
import com.stayabode.features.login.views.AdminLoginView;
import com.stayabode.net.response.getmodels.AdminLoginResponse;

import java.util.concurrent.TimeUnit;

import utils.IntentKeys;
import utils.MixPanelTracker;
import utils.NetUtils;
import utils.SharedPrefManager;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public class AdminLoginActivity extends BaseActivity implements AdminLoginView {

    private AdminLoginPresenter mAdminLoginPresenter;
    private EditText mEtUserName;
    private Button mBtnLogin;
    private TextView mTvPhoneError;
    private View mViewDivider;
    private String mFbUserId;
    private String mUserName;
    private CoordinatorLayout mCoordinatorLayout;
    private String mFbAccessToken;
    private TextView mButtonBrowseProperties;
    private EditText mEtUserpassword;
    private RadioGroup mLoginRadioGroup;
    private String userName;
    private String password;
    private String selection;
    private LinearLayout mInputOtherlayout;
    private View mInputOtherDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        setActionBar();
        disableBackArrow();



        MixPanelTracker.trackEvent(MixPanelTracker.PHONE_VERIFICATION_SCREEN);

        mTvPhoneError = (TextView) findViewById(R.id.text_error);
        mEtUserName = (EditText) findViewById(R.id.edit_username);
        mEtUserpassword = (EditText) findViewById(R.id.edit_password);
        mBtnLogin = (Button) findViewById(R.id.button_login);
        mViewDivider = findViewById(R.id.divider_horizontal);
        mButtonBrowseProperties = (TextView) findViewById(R.id.button_ok);
        mLoginRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_login);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        mInputOtherlayout = (LinearLayout) findViewById(R.id.layout_inputother);
        mInputOtherDivider = (View) findViewById(R.id.divider_horizontal3);


        mAdminLoginPresenter = new AdminLoginPresenter();
        mAdminLoginPresenter.setView(this);

        ((RadioButton) mLoginRadioGroup.getChildAt(0)).setChecked(true);


        setDefaultSelectionValue();

        mLoginRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                selection = radioButton.getText().toString().trim();


                if (selection.equalsIgnoreCase(getResources().getString(R.string.label_login_option_other))) {
                    showOtherInputView();
                } else {
                    hideOtherInputView();
                }
                //  Toast.makeText(AdminLoginActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        mBtnLogin.setEnabled(true);
        mUserName = SharedPrefManager.getInstance().getFirstName();

        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changePhonePrefixColor(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });


        mEtUserpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changePhonePrefixColor(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();

                SharedPrefManager.getInstance().setAccessToken("");
                if (NetUtils.isInternetConnected(AdminLoginActivity.this)) {
                    // showLoadingView();

                    userName = mEtUserName.getText().toString().trim();
                    password = mEtUserpassword.getText().toString().trim();

                    mAdminLoginPresenter.loginAdmin(userName, password, selection);
                } else {
                    // showNoInternetView();
                }


//                Intent i = new Intent(AdminLoginActivity.this, QuestionsActivity.class);
//                startActivity(i);

//
//
//                if (validatePhoneNumber(mEtUserName.getText().toString())) {
//                    MixPanelTracker.trackEvent(MixPanelTracker.REQUEST_OTP_CLICKED);
//                    mAdminLoginPresenter = new AdminLoginPresenter();
//                    mAdminLoginPresenter.setView(AdminLoginActivity.this);
//
//                    if (NetUtils.isInternetConnected(AdminLoginActivity.this)) {
//                        showPleaseWaitLoadingView();
//                        mFbUserId = SharedPrefManager.getInstance().getFbUserId();
//                        mAdminLoginPresenter.loginAdmin(mEtUserName.getText().toString().trim(), mFbUserId);
//                    } else {
//                        ViewUtils.showSnackBar(AdminLoginActivity.this,
//                                mCoordinatorLayout, Constants.INTERNET_ERROR);
//                    }
//
//                }
            }
        });


    }

    private void setDefaultSelectionValue() {

        String radiovalue = ((RadioButton) findViewById(mLoginRadioGroup.getCheckedRadioButtonId())).getText().toString();

        selection = radiovalue;
    }

    private void hideOtherInputView() {
        mInputOtherlayout.setVisibility(View.INVISIBLE);
        mInputOtherDivider.setVisibility(View.INVISIBLE);

    }

    private void showOtherInputView() {
        mInputOtherlayout.setVisibility(View.VISIBLE);
        mInputOtherDivider.setVisibility(View.VISIBLE);
    }


    private void changePhonePrefixColor(CharSequence s) {

        if (mEtUserName.getText().toString().trim().length() > 0 && mEtUserpassword.getText().toString().length() > 0) {
            mBtnLogin.setEnabled(true);
        } else {
            mBtnLogin.setEnabled(false);
        }

    }


    @Override
    public void onLoginSuccessful(AdminLoginResponse adminLoginResponse) {
        String token=adminLoginResponse.getToken();
        SharedPrefManager.getInstance().setAccessToken(token);
        hideLoadingView();
        Intent i = new Intent(AdminLoginActivity.this, AWCListActivity.class);
        i.putExtra(IntentKeys.INTENT_LOGINRESP, (Parcelable) adminLoginResponse);
        startActivity(i);

    }

    @Override
    public void onRequestFailed(int errorCode, String message) {
        hideLoadingView();
        ViewUtils.showSnackBar(AdminLoginActivity.this, mCoordinatorLayout, message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle(getResources().getString(R.string.label_login));
    }

}
