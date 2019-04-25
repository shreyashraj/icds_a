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

import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.adapters.HomeVisitAdapter;
import com.stayabode.net.response.ReportedIssuesResponse;
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
import utils.Status;
import utils.ViewUtils;

/**
 * Created by VarunBhalla on 15/12/16.
 */

public class HomeVisitActivity extends BaseActivity  {

    private static final int STATIC_INTEGER_VALUE = 67;
    private RecyclerView mReportedIssuesRecycler;
    private CoordinatorLayout mCoordinatorLayout;
    private String errorType = "";
    public List<ReportedIssuesResponse.IssuesCategories> mIssueCategoriesList=new ArrayList<>();
    private HomeVisitAdapter homeVisitAdapter;

    ArrayList<ListGuideResponse> homeVisitList;

    HashMap<Integer,String> mListGuideMap;
    private HashMap<Integer, String> mListGuideMapSubmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_visit);
        MixPanelTracker.trackEvent(MixPanelTracker.REPORTED_ISSUES_SCREEN);

        setActionBar();
        mReportedIssuesRecycler = (RecyclerView) findViewById(R.id.recycler_reported_issues);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        mListGuideMap=Constants.listGuideMap;

        Intent intent = getIntent();
        mListGuideMapSubmissions = (HashMap<Integer, String>) intent.getSerializableExtra("listGuideMapSubmissions");


        homeVisitList=new ArrayList<>();


        for (Map.Entry<Integer, String> entry : mListGuideMapSubmissions.entrySet()) {
            System.out.println("Guide map value" + entry.getKey() + "/" + entry.getValue());

            ListGuideResponse homeVisitObj = new ListGuideResponse();


            if(mListGuideMap.get(entry.getKey())!=null){
                homeVisitObj.setQuestionId(entry.getKey()+"");
                homeVisitObj.setSelection(entry.getValue());
                homeVisitObj.setTitle(mListGuideMap.get(entry.getKey()));

                homeVisitList.add(homeVisitObj);
            }

        }


        homeVisitAdapter = new HomeVisitAdapter(this,homeVisitList);
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