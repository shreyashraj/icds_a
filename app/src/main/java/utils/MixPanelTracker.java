package utils;


import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.stayabode.app.AbodeApplication;
import org.json.JSONObject;
import java.util.Map;


/**
 * Created by VarunBhalla on 05/10/16.
 */
public class MixPanelTracker {

    //super properties
    public static final String SUPER_NAME = "$name";
    public static final String NAME = "Name";
    public static final String GENDER = "Gender";
    public static final String COMMUNITY = "Community";

    //dashboard
    public static final String CLICKED_CONTACT_US = "Contact Us Clicked";
    public static final String CLICKED_LOGOUT = "Logout Clicked";
    public static final String DASHBOARD_SCREEN = "Dashboard Screen";

    //house principles
    public static final String HOUSE_PRINCIPLES_SCREEN = "House Principles Screen";

    //profile
    public static final String PROFILE_SCREEN = "Profile Screen";
    public static final String FB_PROFILE_VISIBILITY_CHANGED = "Facebook Profile Visibility Changed";
    public static final String PROFILE_VISIBILITY = "Profile Visibility";

    //general feedback
    public static final String GENERAL_FEEDBACK_SCREEN = "General Feedback Screen";
    public static final String GENERAL_FEEDBACK_SUBMITTED = "General Feedback Submitted";

    //issue feedback
    public static final String ISSUE_FEEDBACK_SCREEN = "Issue Feedback Screen";

    public static final String SUBMITTED_FEEDBACK = "Issue Feedback Submitted";
    public static final String FEEDBACK_LENGTH = "Feedback Length";
    public static final String RATING = "Rating";

    //full screen broadcast
    public static final String BROADCAST_SCREEN = "Broadcast Screen";

    //Registered Visitors
    public static final String REGISTERED_VISITORS_SCREEN = "Registered Visitors Screen";
    public static final String VISITOR_DELETED = "Visitor deleted";
    public static final String VISITOR_EDITED = "Visitor edited";

    //new Visitors
    public static final String NEW_VISITORS_SCREEN = "Register a Visitor Screen";
    public static final String NEW_VISITORS_DAYS = "Number of days";
    public static final String NEW_VISITORS_GENDER= "Gender";
    public static final String NEW_VISITORS_REGISTERED = "New Visitor Registered";

    //reported issues
    public static final String REPORTED_ISSUES_SCREEN = "Reported Issues Screen";
    public static final String ISSUES_DELETED = "Issues Deleted";
    public static final String ISSUES_REOPENED = "Issues Reopened";

    //new issue
    public static final String NEW_ISSUES_SCREEN = "Report An Issue Screen";

    public static final String ISSUE_CATEGORY = "Issue Category";
    public static final String ISSUE_SUB_CATEGORY = "Issue SubCategory";
    public static final String ISSUE_IMAGES_COUNT = "Issue Images Count";
    public static final String ISSUE_DESCRIPTION_LENGTH = "Issue Description Length";
    public static final String ISSUE_CREATED = "Issue Created";

    //Issue Images activity
    public static final String ISSUES_IMAGES_SCREEN = "Issue Images Screen";

    //rental details
    public static final String RENTALS_DETAILS_SCREEN = "Rental Details Screen";
    public static final String RENTALS_SHOW_MORE = "Rental Show More";

    //request rentals
    public static final String REQUEST_RENTALS_SCREEN = "Request Rentals Screen";
    public static final String RENTAL_RECEIPT_REQUESTED = "Rental Receipt Requested";


    //neighbours
    public static final String NEIGHBOURS_SCREEN = "Neighbours Screen";

    //neighbours detail
    public static final String NEIGHBOURS_DETAIL_SCREEN = "Neighbours Detail Screen";
    public static final String TOKEN_FAILED_LOGOUT = "Logout token failed";

    //login
    public static final String EMAIL = "Email id";
    public static final String CHECK_OUR_HOMES_CLICKED = "Ckeck Our Homes Clicked";

    //otp
    public static final String OTP_VERIFIED = "Otp Verified";
    public static final String OTP_GENERATED = "Otp Generated";
    public static final String OTP_GENERATION_FAILED = "Otp Generation Failed";
    public static final String OTP_VERIFICATION_FAILED = "Otp Verification failed";

    //phone
    public static final String PHONE_VERIFICATION_SCREEN = "Phone verification screen";
    public static final String REQUEST_OTP_CLICKED = "Otp requested";
    public static final String CONTACT_US_CLICKED = "Contact Us Clicked";
    public static final String USER_ID = "UserId";
    public static final String EVENT_CONTRACT_EXTENSION = "Contract Extension screen";
    public static final String EVENT_CONTRACT_EXTENSION_STATUS = "Contract Extension Status screen";

    private static String alias;

    //notification
    public static final String CLICKED_NOTIFICATION = "Notification Clicked";

    //events
    public static final String EVENTS_SCREEN = "Events Screen";
    public static final String EVENT_DETAIL_SCREEN = "Event Detail Screen";
    public static final String EVENT_PREFERENCES_SCREEN = "Event preferences screen";


    private static MixpanelAPI getMixPanel() {
        return AbodeApplication.getMixpanel();
    }

    public static void trackEvent(String value) {
        getMixPanel().track(value);
    }

    public static void setSuperProperties(JSONObject superProperties) {
       getMixPanel().registerSuperProperties(superProperties);
    }

    public static void setIdentity(String userid) {
       getMixPanel().getPeople().identify(userid);
    }

    public static void setUserIdentity(String userid) {
        getMixPanel().identify(userid);    }

    public static void trackEventWithParameters(String key, JSONObject value) {
       getMixPanel().track(key,value);
    }

    public static void trackMapEvent(String key,Map value) {
       getMixPanel().trackMap(key,value);
    }

    public static void startEventTime(String key) {
       getMixPanel().timeEvent(key);
    }

    public static void stopEventTime(String key) {
       getMixPanel().track(key);
    }

    public static void setIdentityProperties(String key,String value) {
        getMixPanel().getPeople().set(key, value);
    }

    public static void setAlias(String id,String fbId) {
        getMixPanel().alias(id,fbId);
    }

}
