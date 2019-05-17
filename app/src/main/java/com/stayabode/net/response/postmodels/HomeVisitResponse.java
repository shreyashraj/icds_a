package com.stayabode.net.response.postmodels;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class HomeVisitResponse {

    private ArrayList<HomeVisitObject> mHomeVisitList ;


    public ArrayList<HomeVisitObject> getmHomeVisitList() {
        return mHomeVisitList;
    }

    public void setmHomeVisitList(ArrayList<HomeVisitObject> mHomeVisitList) {
        this.mHomeVisitList = mHomeVisitList;
    }
}

