package com.stayabode.features.login.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.adapters.HomeVisitAdapter;
import com.stayabode.net.response.ReportedIssuesResponse;
import com.stayabode.net.response.postmodels.HomeVisitObject;
import com.stayabode.net.response.postmodels.HomeVisitResponse;
import com.stayabode.net.response.postmodels.ListGuideResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import utils.Constants;
import utils.IntentKeys;
import utils.JsonKeys;
import utils.MixPanelTracker;
import utils.NetUtils;
import utils.SharedPrefManager;
import utils.Status;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 15/12/16.
 */

public class HomeVisitActivity extends BaseActivity {

    private static final int STATIC_INTEGER_VALUE = 67;
    private RecyclerView mReportedIssuesRecycler;
    private CoordinatorLayout mCoordinatorLayout;
    private String errorType = "";
    public List<ReportedIssuesResponse.IssuesCategories> mIssueCategoriesList = new ArrayList<>();
    private HomeVisitAdapter homeVisitAdapter;

    ArrayList<ListGuideResponse> homeVisitList;

    HashMap<Integer, String> mListGuideMap;
    private ArrayList<HomeVisitObject> mListGuideMapSubmissions = new ArrayList<>();
    private TextView mEmptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_visit);
        MixPanelTracker.trackEvent(MixPanelTracker.REPORTED_ISSUES_SCREEN);

        setActionBar();
        mReportedIssuesRecycler = (RecyclerView) findViewById(R.id.recycler_reported_issues);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mEmptyList = (TextView) findViewById(R.id.tv_emptylist);

        mListGuideMap = Constants.listGuideMap;

        Intent intent = getIntent();


        String homeVisitResponseStr = SharedPrefManager.getInstance().getHomeVisitList();
        HomeVisitResponse homeVisitResponse = (new Gson()).fromJson(homeVisitResponseStr, HomeVisitResponse.class);
        if(homeVisitResponse != null) {
            mListGuideMapSubmissions = homeVisitResponse.getmHomeVisitList();
        }


        if (null != mListGuideMapSubmissions) {
            if (mListGuideMapSubmissions.size() > 0) {
                mEmptyList.setVisibility(View.GONE);
            } else {
                mEmptyList.setVisibility(View.VISIBLE);
            }

        } else {
            mEmptyList.setVisibility(View.VISIBLE);
        }

        homeVisitList = new ArrayList<>();


        for (int i = 0; i < mListGuideMapSubmissions.size(); i++) {


            ListGuideResponse homeVisitObj = new ListGuideResponse();

            homeVisitObj.setQuestionId(mListGuideMapSubmissions.get(i).getQid() + "");
            homeVisitObj.setSelection(mListGuideMapSubmissions.get(i).getBenificiaryname());
            homeVisitObj.setTitle(mListGuideMapSubmissions.get(i).getQname());

            homeVisitList.add(homeVisitObj);
        }


        homeVisitAdapter = new HomeVisitAdapter(this, homeVisitList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mReportedIssuesRecycler.setLayoutManager(linearLayoutManager);
        mReportedIssuesRecycler.setAdapter(homeVisitAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("गृह भ्रमण के लिये प्राथमिकता सूची");
    }

}