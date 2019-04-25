package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Arpit on 2/23/17.
 */

public class PostIssueResponse {
    @SerializedName("description")
    String description;
    @SerializedName("category")
    String category;
    @SerializedName("sub_category")
    String subcategory;
    @SerializedName("user_id")
    String userId;
    @SerializedName("image_urls")
    ArrayList<ImageUrls> issueImageUrls=new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<ImageUrls> getIssueImageUrls() {
        return issueImageUrls;
    }

    public void setIssueImageUrls(ArrayList<ImageUrls> issueImageUrls) {
        this.issueImageUrls = issueImageUrls;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public PostIssueResponse(String description, String category,String subcategory, String userId, ArrayList<ImageUrls> issueImageUrls) {
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.userId = userId;
        this.issueImageUrls = issueImageUrls;
    }

    public static class ImageUrls{

        private String imageUrl;

        public ImageUrls(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
