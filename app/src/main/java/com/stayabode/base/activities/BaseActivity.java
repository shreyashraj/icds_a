package com.stayabode.base.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;
import com.stayabode.R;
import com.stayabode.base.presenters.BasePresenter;
import com.stayabode.base.views.BaseView;
import com.stayabode.features.login.activities.AdminLoginActivity;



import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import utils.Constants;
import utils.MixPanelTracker;
import utils.SharedPrefManager;

/**
 * Created by VarunBhalla on 20/10/16.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {

    public RelativeLayout mLayout;
    public FrameLayout mContentHolder;
    public Toolbar mToolbar;
    public TextView mToolbarTitle;
    public CircleImageView mProfilePic;
    public View mErrorView;
    public View mLoadingView;

    public String[] mHouseRules;
    public Button mBtnError;
    public TextView mTvHeadingError;
    public TextView mTvDescriptionError;
    public ImageView mImgError;
    public View mErrorViewLayout;
    public TextView mErrorHeadingTv;
    public TextView mErrorDescriptionTv;
    public TextView mErrorBtn;
    public ImageView mErrorImg;
    private TextView mToolbarButton;
    private BasePresenter mBasePresenter;
    private ImageView mContractExtensionImage;
    private View mDividerView;
    private RelativeLayout mContactManager;
    private CircleImageView mManagerImageView;
    private TextView mManagerNameTv;
    private TextView mManagerDesignationTv;
    private LottieAnimationView animationView;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setContentView(int layoutResID) {

        mLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.base_activity, null);

        mContentHolder = (FrameLayout) mLayout.findViewById(R.id.base_activity_content_holder);
        mToolbar = (Toolbar) mLayout.findViewById(R.id.base_toolbar);
        mLoadingView = mLayout.findViewById(R.id.loading_view);

        mErrorView = mLayout.findViewById(R.id.error_view);
        mDividerView=  mLayout.findViewById(R.id.divider_horizontal);
        animationView =mLayout.findViewById(R.id.animation_view);

        mContactManager=(RelativeLayout) mLayout.findViewById(R.id.layout_contact_manager);
        mManagerImageView=(CircleImageView)mLayout.findViewById(R.id.image_manager);
        mManagerNameTv=(TextView)mLayout.findViewById(R.id.tv_manager_name);
        mManagerDesignationTv=(TextView)mLayout.findViewById(R.id.tv_manager_designation);

        mBtnError = (Button) mLayout.findViewById(R.id.button_act_on_error);
        mTvHeadingError = (TextView) mLayout.findViewById(R.id.text_heading_error);
        mTvDescriptionError = (TextView) mLayout.findViewById(R.id.text_description);
        mImgError = (ImageView) mLayout.findViewById(R.id.img_error);

        mToolbarTitle = (TextView) mLayout.findViewById(R.id.toolbar_title);
        mProfilePic = (CircleImageView) mLayout.findViewById(R.id.toolbar_image_profile);
        mToolbarButton = (TextView) mLayout.findViewById(R.id.toolbar_btn);
        mContractExtensionImage = (ImageView) mLayout.findViewById(R.id.img_status);


        String mCommunityManagerImageUrl = SharedPrefManager.getInstance().getCommunityManagerImageUrl();
        String mCommunityManagerName = SharedPrefManager.getInstance().getCommunityManagerName();

        mManagerNameTv.setText(mCommunityManagerName);

        if(null != mCommunityManagerImageUrl && mCommunityManagerImageUrl.length()>0) {
            Picasso.with(this).load(mCommunityManagerImageUrl).
                    placeholder(R.mipmap.ic_profile_placeholder).
                    into(mManagerImageView);
        }else{
            mManagerImageView.setImageDrawable(ContextCompat.getDrawable(this,R.mipmap.ic_profile_placeholder));
        }



        getLayoutInflater().inflate(layoutResID, mContentHolder, true);
        super.setContentView(mLayout);

//        if (null == SharedPrefManager.getInstance().getAccessToken()) {
//            logoutUser();
//        }



        mBtnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorBtnClick();
            }
        });

        mContactManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContactManagerClicked();
            }
        });


        mBasePresenter = new BasePresenter();
        mBasePresenter.setView(this);

    }

    protected  void onContactManagerClicked() {

    }


    protected void onErrorBtnClick() {
        hideErrorView();
        showLoadingView();
    }

    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    protected void showPleaseWaitLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
    }


    protected void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
    }

    protected void showNoInternetView() {
        mErrorView.setVisibility(View.VISIBLE);
        mTvHeadingError.setText(getResources().getString(R.string.label_no_internet));
        mTvDescriptionError.setText(getResources().getString(R.string.label_no_internet_desc));
        mBtnError.setText(getResources().getString(R.string.label_retry_internet));
        mImgError.setImageResource(R.mipmap.ic_no_internet);
    }

    protected void showGenericErrorView() {
        mErrorView.setVisibility(View.VISIBLE);
        mTvHeadingError.setText(getResources().getString(R.string.label_no_internet));
        mTvDescriptionError.setText(getResources().getString(R.string.label_no_internet_desc));
        mBtnError.setText(getResources().getString(R.string.label_retry_internet));
        mImgError.setImageResource(R.mipmap.ic_generic_error);
        animationView.playAnimation();


    }


    protected void hideErrorView() {
        mErrorView.setVisibility(View.GONE);
    }

    protected void showNoVisitorsView() {

    }

    protected void showNoEventsView() {

    }

    protected void showNoIssuesView() {

    }

    protected void showNoNotificationsView() {

    }

    protected void showNoPaymentHistoryView() {

    }

    protected void showNoActivePaymentHistoryView() {

    }

    protected void setActionBar() {
        mProfilePic.setVisibility(View.INVISIBLE);
        mContractExtensionImage.setVisibility(View.INVISIBLE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    protected void disableActionBar() {
        mProfilePic.setVisibility(View.INVISIBLE);
        mContractExtensionImage.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();


    }

    protected void setActionBarWithButtonText(String title) {
        mProfilePic.setVisibility(View.INVISIBLE);
        mContractExtensionImage.setVisibility(View.INVISIBLE);
        mToolbarButton.setVisibility(View.VISIBLE);
        mToolbarButton.setText(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    protected void setActionBarWithProfile() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
        mToolbarTitle.setAllCaps(true);
    }

    public void setToolbarTitleSmall(String title) {
        mToolbarTitle.setText(title);
    }

    public void loadToolbarProfileImage(Context context) {
        String profileImgUrl = SharedPrefManager.getInstance().getProfileImageUri();

        if(null != profileImgUrl && profileImgUrl.length()>0) {
            Picasso.with(context).load(profileImgUrl).
                    placeholder(R.mipmap.ic_profile_placeholder).
                    into(mProfilePic);
        }else{
            mProfilePic.setImageDrawable(ContextCompat.getDrawable(this,R.mipmap.ic_profile_placeholder));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public View getmErrorViewLayout() {
        return mErrorView;
    }

    public View getmDividerViewLayout() {
        return mDividerView;
    }

    public TextView getmErrorHeadingTv() {
        return mTvHeadingError;
    }

    public TextView getmErrorDescriptionTv() {
        return mTvDescriptionError;
    }

    public TextView getmErrorBtn() {
        return mBtnError;
    }

    public ImageView getmErrorImg() {
        return mImgError;
    }

    public RelativeLayout geContactManagerLayout() {
        return mContactManager;
    }

    public void disableBackArrow() {
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }


    public TextView getToolbarButton() {
        return mToolbarButton;
    }

    public void refreshToken() {
        mBasePresenter.refreshToken();
    }

    public void logoutUser() {
        MixPanelTracker.trackEvent(MixPanelTracker.TOKEN_FAILED_LOGOUT);
        String fcmId = SharedPrefManager.getInstance().getFCMToken();
        SharedPrefManager.getInstance().clearPreferences();
        SharedPrefManager.getInstance().setFCMToken(fcmId);
        Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onTokenRefreshed(String accessToken, String refreshToken) {
        hideLoadingView();
        SharedPrefManager.getInstance().setAccessToken(accessToken);
        SharedPrefManager.getInstance().setRefreshToken(refreshToken);
    }
    @Override
    public void onTokenRequestFailed(int code, String message) {
        logoutUser();
    }
}
