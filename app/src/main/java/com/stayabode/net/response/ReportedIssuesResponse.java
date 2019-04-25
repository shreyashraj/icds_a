package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VarunBhalla on 15/12/16.
 */
public class ReportedIssuesResponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("issues")
        private List<IssueDetails> issuesList;

        @SerializedName("issue_category")
        private List<IssuesCategories> issueCategories;

        public List<IssuesCategories> getIssueCategories() {
            return issueCategories;
        }

        public void setIssueCategories(List<IssuesCategories> issueCategories) {
            this.issueCategories = issueCategories;
        }

        public List<IssueDetails> getIssuesList() {
            return issuesList;
        }

        public void setIssuesList(List<IssueDetails> issuesList) {
            this.issuesList = issuesList;
        }
    }


    public static class IssuesCategories implements Parcelable{

        @SerializedName("category")
        private String category;

        @SerializedName("sub_category")
        private ArrayList<SubCategory>  subCategoryList;


        protected IssuesCategories(Parcel in) {
            category = in.readString();
            subCategoryList=in.readArrayList(SubCategory.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(category);
            dest.writeList(subCategoryList);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<IssuesCategories> CREATOR = new Creator<IssuesCategories>() {
            @Override
            public IssuesCategories createFromParcel(Parcel in) {
                return new IssuesCategories(in);
            }

            @Override
            public IssuesCategories[] newArray(int size) {
                return new IssuesCategories[size];
            }
        };

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public ArrayList<SubCategory> getSubCategoryList() {
            return subCategoryList;
        }

        public void setSubCategoryList(ArrayList<SubCategory> issuesList) {
            this.subCategoryList = issuesList;
        }


    }


    public static class SubCategory implements Parcelable{

        @SerializedName("key")
        private String key;

        @SerializedName("value")
        private String value;

        protected SubCategory(Parcel in) {
            key = in.readString();
            value = in.readString();
        }

        public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
            @Override
            public SubCategory createFromParcel(Parcel in) {
                return new SubCategory(in);
            }

            @Override
            public SubCategory[] newArray(int size) {
                return new SubCategory[size];
            }
        };

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(key);
            parcel.writeString(value);
        }
    }


    public static class IssueDetails implements Parcelable {
        private String category;
        private String description;
        private String reported_at;
        private String status;
        private String issue_id;
        private String is_rated;
        private String sub_category;

        public IssueDetails(String category, String description, String reported_at, String status,String issue_id,String is_rated,String sub_category){
            this.category = category;
            this.description = description;
            this.reported_at = reported_at;
            this.status = status;
            this.issue_id = issue_id;
            this.is_rated = is_rated;
            this.sub_category = sub_category;
        }


        public String getSub_category() {
            return sub_category;
        }

        public void setSub_category(String sub_category) {
            this.sub_category = sub_category;
        }

        public String getIs_rated() {
            return is_rated;
        }

        public void setIs_rated(String is_rated) {
            this.is_rated = is_rated;
        }

        public String getIssueId() {
            return issue_id;
        }

        public void setIssueId(String issue_id) {
            this.issue_id = issue_id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getReported_at() {
            return reported_at;
        }

        public void setReported_at(String reported_at) {
            this.reported_at = reported_at;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(category);
            parcel.writeString(description);
            parcel.writeString(reported_at);
            parcel.writeString(status);
            parcel.writeString(issue_id);
            parcel.writeString(is_rated);
            parcel.writeString(sub_category);
        }

        private IssueDetails(Parcel in){
            this.category = in.readString();
            this.description = in.readString();
            this.reported_at = in.readString();
            this.status = in.readString();
            this.issue_id = in.readString();
            this.is_rated = in.readString();
            this.sub_category = in.readString();
        }

        public static final Parcelable.Creator<ReportedIssuesResponse.IssueDetails> CREATOR =
                new Parcelable.Creator<ReportedIssuesResponse.IssueDetails>() {

            @Override
            public ReportedIssuesResponse.IssueDetails createFromParcel(Parcel source) {
                return new ReportedIssuesResponse.IssueDetails(source);
            }

            @Override
            public ReportedIssuesResponse.IssueDetails[] newArray(int size) {
                return new ReportedIssuesResponse.IssueDetails[size];
            }
        };

    }
}
