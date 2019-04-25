package com.stayabode.features.login.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.presenters.LoginAfterCheckoutPresenter;
import com.stayabode.features.login.views.LoginAfterCheckoutView;

import de.hdodenhof.circleimageview.CircleImageView;
import utils.NetUtils;
import utils.SharedPrefManager;
import utils.StringUtils;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 13/12/16.
 */
public class LoginAfterCheckoutActivity extends BaseActivity implements LoginAfterCheckoutView {

    private LoginAfterCheckoutPresenter mLoginAfterCheckoutPresenter;
    private CircleImageView mProfilePic;
    private String mCheckoutDate;
    private EditText mEditYourExperience;
    private Button mButtonGetRentalReciepts;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView mProfileName;
    private SharedPrefManager mInstance;
    private TextView mWelcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_after_checkout);

        setActionBar();

        mInstance = SharedPrefManager.getInstance();


        mProfilePic = (CircleImageView) findViewById(R.id.profile_pic);

        mEditYourExperience = (EditText) findViewById(R.id.edit_your_experience);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mButtonGetRentalReciepts = (Button) findViewById(R.id.button_get_rental_recipts);
        mWelcomeMessage = (TextView) findViewById(R.id.text_welcome_msg);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        mProfileName = (TextView) findViewById(R.id.text_profile_name);

        mButtonGetRentalReciepts.setEnabled(false);

        mCheckoutDate = SharedPrefManager.getInstance().getCheckOut();
        mWelcomeMessage.setText("You moved out on " + StringUtils.getAbbreviatedPrettyDate(mCheckoutDate, true)
                + ". We hope your stay was lovely and you return in the future.");

        String profileImgUrl = SharedPrefManager.getInstance().getProfileImageUri();
        Picasso.with(this).load(profileImgUrl).
                placeholder(R.mipmap.ic_profile_placeholder).
                into(mProfilePic);


        mProfileName.setText(mInstance.getFirstName());


        mLoginAfterCheckoutPresenter = new LoginAfterCheckoutPresenter();
        mLoginAfterCheckoutPresenter.setView(LoginAfterCheckoutActivity.this);


        mEditYourExperience.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        mButtonGetRentalReciepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                if (NetUtils.isInternetConnected(LoginAfterCheckoutActivity.this)) {
                    String email = mEditYourExperience.getText().toString().trim();
                    mLoginAfterCheckoutPresenter.sendFeedback(mEditYourExperience.getText().toString().trim());
                } else {
                    showNoInternetView();
                }
            }
        });

    }

    private void validateInput(CharSequence s) {
        if (s.length() == 0) {
            mButtonGetRentalReciepts.setEnabled(false);
        } else {
            mButtonGetRentalReciepts.setEnabled(true);
        }
    }

    @Override
    protected void onErrorBtnClick() {
        super.onErrorBtnClick();
        mLoginAfterCheckoutPresenter.sendFeedback(mEditYourExperience.getText().toString().trim());
    }

    @Override
    public void onRequestFailed(int code, String message) {
        hideLoadingView();
        ViewUtils.showSnackBar(LoginAfterCheckoutActivity.this, mCoordinatorLayout, message);
    }

    @Override
    public void onFeedbackSent(String message) {
        hideLoadingView();
        mButtonGetRentalReciepts.setEnabled(false);
        ViewUtils.showSnackBarAndFinish(LoginAfterCheckoutActivity.this, mCoordinatorLayout, message);
    }
}
