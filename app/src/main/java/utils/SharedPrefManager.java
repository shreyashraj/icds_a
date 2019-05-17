package utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.stayabode.app.AbodeApplication;

import java.util.Set;

/**
 * Created by VarunBhalla on 05/10/16.
 */
public class SharedPrefManager {

    public static final String SHARED_PREF_NAME = "abode_user_prefs";
    public static final String PREF_ACCESS_TOKEN = "access_token";
    public static final String PREF_REFRESH_TOKEN = "refresh_token";
    public static final String PREF_TOKEN_TYPE = "token_type";
    public static final String PREF_USER_ID = "user_id";
    public static final String PREF_PHONE_NO = "phone_no";
    public static final String PREF_EMAIL_ID = "email_id";
    public static final String PREF_USER_NAME = "username";
    public static final String PREF_USER_ROLE = "role";
    public static final String PREF_HOUSE_RULE_POSITION = "house_rule_position";
    public static final String PREF_FIRST_NAME = "first_name";
    public static final String PREF_LAST_NAME = "last_name";
    public static final String PREF_CHECK_IN = "check_in";
    public static final String PREF_CHECK_OUT = "check_out";
    public static final String PREF_FB_USER_ID = "fb_user_id";
    public static final String PREF_USER_BIRTHDAY = "user_birthday";
    public static final String PREF_PROFILE_IMAGE_URI = "profile_image_uri";
    public static final String PREF_ACCESS_TOKEN_KEY = "access_token_key";
    public static final String PREF_IS_LOGGED_IN = "is_logged_in";
    public static final String PREF_IS_PHONE_VERIFIED = "is_phone_verified";
    public static final String PREF_ROOM_NUMBER= "room_number";
    public static final String PREF_FCM_TOKEN= "fcm_token";
    public static final String PREF_COMMUNITY_MANAGER_PHONE= "community_manager_phone";
    public static final String PREF_COMMUNITY= "community";
    public static final String PREF_READ_NOTIFICATIONS= "read_notifications";
    public static final String PREF_TEMP_UNREAD_NOTIFICATIONS= "temp_unread_notifications";
    private static final String PREF_COMMUNITY_MANAGER_IMAGE_URL = "community_manager_image_url";
    private static final String PREF_COMMUNITY_MANAGER_NAME = "community_manager_name";
    private static final String PREF_CONTRACT_EXTENSION_STATUS = "contract_extension_status";
    private static final String PREF_CONTRACT_EXTENSION_REQUESTED_DATE = "contract_extension_requested_date";
    private static final String PREF_QUESTIONS_RESPONSE = "question_response";
    private static final String PREF_LOGIN_RESPONSE = "login_response";

    private static final String PREF_HOME_VISIT_RESPONSE= "home_visit__response";


    public static volatile SharedPrefManager sInstance;
    public static volatile SharedPreferences sSharedPreferences;


    public static SharedPrefManager getInstance() {
        SharedPrefManager localInstance = sInstance;
        if (localInstance == null) {
            synchronized (SharedPrefManager.class) {
                localInstance = sInstance = new SharedPrefManager();
            }
        }
        return localInstance;
    }

    private SharedPrefManager() {
        sSharedPreferences = AbodeApplication.getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    public String getFbUserId() {
        return sSharedPreferences.getString(PREF_FB_USER_ID, null);
    }

    public String getUserBirthday() {
        return sSharedPreferences.getString(PREF_USER_BIRTHDAY, null);
    }

    public String getProfileImageUri() {
        return sSharedPreferences.getString(PREF_PROFILE_IMAGE_URI, null);
    }


    public void setFbUserId(String userId) {
        sSharedPreferences.edit().putString(PREF_FB_USER_ID, userId).commit();

    }

    public void setUserBirthday(String birthday) {
        sSharedPreferences.edit().putString(PREF_USER_BIRTHDAY, birthday).commit();

    }

    public void setProfileImageUri(String imageuri) {
        sSharedPreferences.edit().putString(PREF_PROFILE_IMAGE_URI, imageuri).commit();

    }

    public String getRoomNumber() {
        return sSharedPreferences.getString(PREF_ROOM_NUMBER, "");
    }

    public void setRoomNumber(String roomNumber) {
        sSharedPreferences.edit().putString(PREF_ROOM_NUMBER, roomNumber).commit();
    }


    public String getFCMToken() {
        return sSharedPreferences.getString(PREF_FCM_TOKEN, "");
    }

    public void setFCMToken(String fcmToken) {
        sSharedPreferences.edit().putString(PREF_FCM_TOKEN, fcmToken).commit();
    }

    public String getAccessTokenKey() {
        return sSharedPreferences.getString(PREF_ACCESS_TOKEN_KEY, JsonKeys.JSONKEY_FB_ACCESS_TOKEN);
    }

    public void setAccessTokenKey(String accessTokenKey) {
        sSharedPreferences.edit().putString(PREF_ACCESS_TOKEN_KEY, accessTokenKey).commit();
    }

    public boolean getIsLoggedIn() {
        return sSharedPreferences.getBoolean(PREF_IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        sSharedPreferences.edit().putBoolean(PREF_IS_LOGGED_IN, isLoggedIn).apply();
    }


    public String getAccessToken() {
        return sSharedPreferences.getString(PREF_ACCESS_TOKEN, null);
    }

    public String getRefreshToken() {
        return sSharedPreferences.getString(PREF_REFRESH_TOKEN, null);
    }

    public String getEmailId() {
        return sSharedPreferences.getString(PREF_EMAIL_ID, null);
    }

    public String getTokenType() {
        return sSharedPreferences.getString(PREF_TOKEN_TYPE, null);
    }

    public void setAccessToken(String accessToken) {
        sSharedPreferences.edit().putString(PREF_ACCESS_TOKEN, accessToken).commit();
    }

    public void setRefreshToken(String refreshToken) {
        sSharedPreferences.edit().putString(PREF_REFRESH_TOKEN, refreshToken).commit();
    }

    public void setTokenType(String tokenType) {
        sSharedPreferences.edit().putString(PREF_TOKEN_TYPE, tokenType).commit();
    }

    public void setEmailId(String emailId) {
        sSharedPreferences.edit().putString(PREF_EMAIL_ID, emailId).commit();
    }


    public void clearPreferences() {
        sSharedPreferences.edit().clear().commit();
    }

    public static void setHouseRulePosition(int position) {
        sSharedPreferences.edit().putInt(PREF_HOUSE_RULE_POSITION, position).commit();
    }

    public static int getHouseRulePosition() {
        return sSharedPreferences.getInt(PREF_HOUSE_RULE_POSITION, 0);
    }

    public void setUserId(String userId) {
        sSharedPreferences.edit().putString(PREF_USER_ID, userId).commit();
    }

    public String getUserId() {
        return sSharedPreferences.getString(PREF_USER_ID, null);
    }

    public void setPhoneNo(String phoneNo) {
        sSharedPreferences.edit().putString(PREF_PHONE_NO, phoneNo).commit();
    }

    public String getPhoneNo() {
        return sSharedPreferences.getString(PREF_PHONE_NO, null);
    }

    public void setUsername(String username) {
        sSharedPreferences.edit().putString(PREF_USER_NAME, username).commit();
    }

    public String getUsername() {
        return sSharedPreferences.getString(PREF_USER_NAME, null);
    }

    public String getRole() {
        return sSharedPreferences.getString(PREF_USER_ROLE, null);
    }

    public void setRole(String role) {
        sSharedPreferences.edit().putString(PREF_USER_ROLE, role).commit();
    }

    public String getFirstName() {
        return sSharedPreferences.getString(PREF_FIRST_NAME, null);
    }

    public String getLastName() {
        return sSharedPreferences.getString(PREF_LAST_NAME, null);
    }

    public String getCheckIn() {
        return sSharedPreferences.getString(PREF_CHECK_IN, null);
    }

    public String getCheckOut() {
        return sSharedPreferences.getString(PREF_CHECK_OUT, null);
    }


    public void setFirstName(String firstName) {
        sSharedPreferences.edit().putString(PREF_FIRST_NAME, firstName).commit();
    }

    public void setLastName(String lastName) {
        sSharedPreferences.edit().putString(PREF_LAST_NAME, lastName).commit();
    }

    public void setCheckIn(String checkIn) {

        sSharedPreferences.edit().putString(PREF_CHECK_IN, checkIn).commit();
    }

    public void setCheckOut(String checkOut) {
        sSharedPreferences.edit().putString(PREF_CHECK_OUT, checkOut).commit();
    }



    public void setisPhoneVerified(Boolean isPhoneVerified) {
        sSharedPreferences.edit().putBoolean(PREF_IS_PHONE_VERIFIED, isPhoneVerified).commit();
    }

    public Boolean getisPhoneVerified() {
        return sSharedPreferences.getBoolean(PREF_IS_PHONE_VERIFIED, false);
    }

    public void setCommunityManagerPhone(String communityManagerPhone) {
        sSharedPreferences.edit().putString(PREF_COMMUNITY_MANAGER_PHONE, communityManagerPhone).commit();
    }

    public String getCommunityManagerPhone() {
        return sSharedPreferences.getString(PREF_COMMUNITY_MANAGER_PHONE, null);
    }



    public void setCommunityManagerImageUrl(String communityManagerImageUrl) {
        sSharedPreferences.edit().putString(PREF_COMMUNITY_MANAGER_IMAGE_URL, communityManagerImageUrl).commit();
    }

    public String getCommunityManagerImageUrl() {
        return sSharedPreferences.getString(PREF_COMMUNITY_MANAGER_IMAGE_URL, null);
    }



    public void setCommunityManagerName(String communityManagerName) {
        sSharedPreferences.edit().putString(PREF_COMMUNITY_MANAGER_NAME, communityManagerName).commit();
    }

    public String getCommunityManagerName() {
        return sSharedPreferences.getString(PREF_COMMUNITY_MANAGER_NAME, null);
    }



    public void setContractExtensionStatus(String contractExtensionStatus) {
        sSharedPreferences.edit().putString(PREF_CONTRACT_EXTENSION_STATUS, contractExtensionStatus).commit();
    }

    public String getContractExtensionStatus() {
        return sSharedPreferences.getString(PREF_CONTRACT_EXTENSION_STATUS, null);
    }


    public void setContractExtensionRequestedDate(String contractExtensionRequestedDate) {
        sSharedPreferences.edit().putString(PREF_CONTRACT_EXTENSION_REQUESTED_DATE, contractExtensionRequestedDate).commit();
    }

    public String getContractExtensionRequestedDate() {
        return sSharedPreferences.getString(PREF_CONTRACT_EXTENSION_REQUESTED_DATE, null);
    }


    public void setCommunity(String community) {
        sSharedPreferences.edit().putString(PREF_COMMUNITY, community).commit();
    }

    public String getCommunity() {
        return sSharedPreferences.getString(PREF_COMMUNITY, null);
    }


    public void setReadNotifications(Set<String> readNotifications) {
        sSharedPreferences.edit().putStringSet(PREF_READ_NOTIFICATIONS, readNotifications).commit();
    }

    public Set<String> getReadNotifications() {
        return sSharedPreferences.getStringSet(PREF_READ_NOTIFICATIONS, null);
    }

    public void setTempUnReadNotifications(Set<String> readNotifications) {
        sSharedPreferences.edit().putStringSet(PREF_TEMP_UNREAD_NOTIFICATIONS, readNotifications).commit();
    }

    public Set<String> getTempUnReadNotifications() {
        return sSharedPreferences.getStringSet(PREF_TEMP_UNREAD_NOTIFICATIONS, null);
    }



    public void setQuestionsResponse(String questionsResponse) {
        sSharedPreferences.edit().putString(PREF_QUESTIONS_RESPONSE, questionsResponse).commit();
    }

    public String getQuestionsResponse() {
        return sSharedPreferences.getString(PREF_QUESTIONS_RESPONSE, null);
    }


    public void setLoginResponse(String questionsResponse) {
        sSharedPreferences.edit().putString(PREF_LOGIN_RESPONSE, questionsResponse).commit();
    }

    public String getLoginResponse() {
        return sSharedPreferences.getString(PREF_LOGIN_RESPONSE, null);
    }

    public void setHomeVisitList(String resp) {
        sSharedPreferences.edit().putString(PREF_HOME_VISIT_RESPONSE, resp).commit();
    }

    public String getHomeVisitList() {
        return sSharedPreferences.getString(PREF_HOME_VISIT_RESPONSE, null);
    }

}
