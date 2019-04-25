package com.stayabode.features.login.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.stayabode.R;
import com.stayabode.custom.interpolator.MyBounceInterpolator;

import com.stayabode.features.login.presenters.LoginPresenter;
import com.stayabode.features.login.views.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import utils.Constants;
import utils.JsonKeys;
import utils.MixPanelTracker;
import utils.NetUtils;
import utils.SharedPrefManager;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 04/10/16.
 */

public class LoginActivity extends AppCompatActivity implements Animation.AnimationListener, LoginView {

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private LoginButton loginButton;
    private TextView mfbBtnText;
    private TextView mButtonOk;
    private Animation animFadeIn, animFadeOut;
    private RelativeLayout mFbLoginLayout;
    private Animation bounce;
    private ImageView mfbIcon;
    private TextView mRightArrow;
    private LinearLayout mFbIconTextLayout;
    private TransitionDrawable transition2, transition1;

    private String mUserEmail = "";
    private String mUserBirthday = "";
    public String mImageUri;
    public String mLastName;
    public String mFirstName;

    private String mFbAccessToken;
    private LoginPresenter mLoginPresenter;
    private CoordinatorLayout mCoordinatorLayout;
    private String mFbUserId="";
    private SharedPrefManager prefInstance;
    private boolean isLoggedIn;
    private boolean isPhoneVerified;
    private LinearLayout mProgressBar;
    private String checkOutDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefInstance = SharedPrefManager.getInstance();
        isLoggedIn = prefInstance.getIsLoggedIn();



        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        mfbBtnText = (TextView) findViewById(R.id.tv_fb);
        mFbLoginLayout = (RelativeLayout) findViewById(R.id.layout_button_fb);
        mFbIconTextLayout = (LinearLayout) findViewById(R.id.fb_icon_text_layout);
        mButtonOk = (TextView) findViewById(R.id.button_ok);
        mfbIcon = (ImageView) findViewById(R.id.fb_icon);
        mRightArrow = (TextView) findViewById(R.id.arrow_right);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mProgressBar = (LinearLayout) findViewById(R.id.progressbar);

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, callback);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stayAbodeWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://stayabode.com/"));
                startActivity(stayAbodeWeb);
            }
        });


        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        bounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);

        animFadeIn.setAnimationListener(this);
        animFadeOut.setAnimationListener(this);
        bounce.setAnimationListener(this);

        transition1 = (TransitionDrawable) mFbLoginLayout.getBackground();
        transition2 = (TransitionDrawable) mFbIconTextLayout.getBackground();


        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.09, 15);
        bounce.setInterpolator(interpolator);

        mFbLoginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFbLoginLayout.startAnimation(bounce);

                loginButton.setVisibility(View.VISIBLE);
                mfbIcon.setVisibility(View.VISIBLE);

                mRightArrow.setVisibility(View.GONE);
                mfbBtnText.setText(getResources().getString(R.string.login_with_facebook));

                transition1.startTransition(500);
                transition2.startTransition(500);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (null!=profileTracker){
            profileTracker.stopTracking();
        }
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        mProgressBar.setVisibility(View.VISIBLE);
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onTokenFetched(String accessToken, String refreshToken, Boolean isPhoneVerified, String userId) {

        mProgressBar.setVisibility(View.INVISIBLE);

        prefInstance.setRefreshToken(refreshToken);
        prefInstance.setAccessToken(accessToken);
        prefInstance.setFirstName(mFirstName);
        prefInstance.setLastName(mLastName);
        prefInstance.setFbUserId(mFbUserId);
        prefInstance.setUserId(userId);
        prefInstance.setUserBirthday(mUserBirthday);
        prefInstance.setProfileImageUri(mImageUri);

        if (isPhoneVerified) {
            prefInstance.setAccessTokenKey(JsonKeys.JSONKEY_ACCESS_TOKEN);
            prefInstance.setisPhoneVerified(true);
            prefInstance.setIsLoggedIn(true);
        } else {
            prefInstance.setAccessTokenKey(JsonKeys.JSONKEY_FB_ACCESS_TOKEN);
            prefInstance.setisPhoneVerified(true);
        }



        isLoggedIn = prefInstance.getIsLoggedIn();
        isPhoneVerified = prefInstance.getisPhoneVerified();
        checkOutDate = prefInstance.getCheckOut();



    }

    @Override
    public void onRequestFailed(int errorCode, String errorMessage) {
        ViewUtils.showSnackBar(LoginActivity.this, mCoordinatorLayout, errorMessage);
    }

    private void logOutFbIfLoggedIn() {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        }
    }

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(final LoginResult loginResult) {


            if (null == Profile.getCurrentProfile()) {

                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {

                        getUserDetails(newProfile, loginResult);
                        profileTracker.stopTracking();
                    }
                };
                profileTracker.startTracking();

            } else {
                Profile profile = Profile.getCurrentProfile();
                getUserDetails(profile, loginResult);
            }

        }

        @Override
        public void onCancel() {
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError(FacebookException e) {
            mProgressBar.setVisibility(View.INVISIBLE);
            ViewUtils.showSnackBar(LoginActivity.this,
                    mCoordinatorLayout, Constants.INTERNET_ERROR);
        }
    };

    private void getUserDetails(Profile profile, LoginResult loginResult) {
        mFirstName = profile.getFirstName();
        mLastName = profile.getLastName();
        mImageUri = profile.getProfilePictureUri(200, 200).toString();

        MixPanelTracker.setIdentityProperties(MixPanelTracker.SUPER_NAME, mFirstName+" "+mLastName);
        if (null == loginResult.getAccessToken().getToken()) {

            accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                    makeGraphApiRequest(newToken);
                    accessTokenTracker.stopTracking();
                }
            };

        } else {
            mFbAccessToken = loginResult.getAccessToken().getToken();
            makeGraphApiRequest(loginResult.getAccessToken());
        }
    }

    private void makeGraphApiRequest(AccessToken token) {

        GraphRequest request = GraphRequest.newMeRequest(
                token,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("LoginActivity", response.toString());

                        try {
                            mFbUserId = object.getString("id");
                            MixPanelTracker.setUserIdentity(mFbUserId);
                            MixPanelTracker.setIdentity(mFbUserId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            mUserEmail = object.getString("email");
                            MixPanelTracker.setIdentityProperties(MixPanelTracker.EMAIL, mUserEmail);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            mUserBirthday = object.getString("birthday"); // 01/31/1980 format
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        Timber.i(mUserEmail + " " + mUserBirthday);

                        logOutFbIfLoggedIn();

                        mLoginPresenter = new LoginPresenter();
                        mLoginPresenter.setView(LoginActivity.this);

                        SharedPrefManager.getInstance().setAccessToken("");
                        if (NetUtils.isInternetConnected(LoginActivity.this)) {
                            // showLoadingView();
                            mLoginPresenter.fetchToken(mFirstName, mUserEmail, mImageUri, mFbUserId, mFbAccessToken,mUserBirthday);
                        } else {
                            // showNoInternetView();
                        }


                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();

    }

}
