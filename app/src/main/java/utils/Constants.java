package utils;

import java.util.HashMap;

/**
 * Created by VarunBhalla on 13/10/16.
 */
public class Constants {
    public static final String[] NAV_DRAWER_LABELS_WITH_EXTENSION = {"Visitors", "Contract Extension", "House principles", "Notifications", "Feedback", "Call community manager", "Logout"};
    public static final String[] NAV_DRAWER_LABELS_WITHOUT_EXTENSION = {"Visitors", "House principles", "Notifications", "Feedback", "Call community manager", "Logout"};

    public static final String[] ARRAY_HOUSE_RULES =
            {"The only drug we don’t mind at Abode is love. Nothing else.",
                    "Cleanliness is next to common sense. And dustbins are not showpieces. Just saying.",
                    "When in community zones - You come. You see. You pick. And you return. (Kleptomaniacs, go away.) ",
                    "Talking of co-living zones, leave them like you had found them. Clean and kickass! ",
                    "A family that eats (talks, inspires and grows) together, stays together. Up for it? ",
                    "FUR is our favourite ‘F’ word after, well, Friends. Your four-legged fur balls are welcome. ",
                    "Never miss an event hosted by Abode Insiders. FOMO might kill you from within otherwise. ",
                    "We’ll mend broken hearts, not broken furniture. So, take good care of your room. ",
                    "If you plan to bad-mouth Deepika Padukone, please pack your bags before that. ",
                    "Make sure you make friends, memories and some bad decisions at Abode. After all, YOLO, right? "};


    public static final HashMap<Integer, String> listGuideMap = new HashMap<Integer, String>() {{
        put(112, "अगले महीने सम्भावित प्रसव");
        put(114, "पिछले माह मे मातृ जटिलता ");
        put(129, "पिछले माह मे प्रसव वाली महिला");
        put(141, "कमजोर नवजात");
        put(149, "अगले महीने 6 माह पूर्ण करने वाले बच्चे (बच्चे जो अभी 5 महीने के है) ");
        put(158, "10  माह का बच्चा");
        put(159, "पिछले माह मे मातृ मृत्यु");
        put(160, "पिछले माह मे हुए 0 से 5 बर्ष के बच्चों की मृत्यु");
        put(165, "पिछले तीन माह से लगातार नारंगी रंग मे आने वाला बच्चा जो बीमार भी है ");
    }};


    public static final String INTERNET_ERROR = "Please recheck your internet connection";
    public static final String REQUEST_PHONE_NUMBER_CHANGE = "Update my registered mobile number";

    public static final String NEW_VISITOR = "newVisitor";
    public static final String EDIT_VISITOR = "editVisitor";
    public static final String NAME_VALIDATION_ERROR = "Please enter your visitor’s name";
    public static final String PHONE_VALIDATION_ERROR = "Please enter a valid mobile number";
    public static final String CHECK_IN_VALIDATION_ERROR = "Please enter the date of arrival";
    public static final String CHECK_OUT_VALIDATION_ERROR = "Please enter the date of leaving";

    public static final String PLEASE_WAIT = "Please Wait ...";
    public static final String CANCEL_ISSUE_DIALOG_LABEL = "Are you sure you would like to cancel this issue?";
    public static final String REOPEN_ISSUE_DIALOG_LABEL = "Are you sure you want to reopen the issue?";
    public static final String DELETE_VISITOR_DIALOG_LABEL = "Are you sure you want to remove this visitor?";


    public static final String OPEN_ISSUE_STATUS = "Open";
    public static final String IN_PROGRESS_ISSUE_STATUS = "In Progress";
    public static final String RESOLVED_ISSUE_STATUS = "Resolved";
    public static final String CANCELLED_ISSUE_STATUS = "Cancelled";

    public static final String LOGOUT_DIALOG_LABEL = "Are you sure you want to logout?";
    public static final String DIALOG_NEGATIVE_BUTTON_TEXT = "रद्द करें" +
            "\n" +
            "\n";
    public static final String MORNING = "1";
    public static final String AFTERNOON = "2";
    public static final String EVENING = "3";
    public static final String REOPEN_ISSUE_VALIDATION_ERROR = "Enter comments before reopening the issue";
    public static final String GENERIC_ERROR = "Uh-oh, something went wrong. Please retry in sometime";
    public static final String PHONE_REGISTRATION_ERROR = "Please enter your registered mobile number or contact us to update your details.";
    public static final String PREFERENCES_ERROR = "Please enter your preferences.";
    public static final String NEW_ISSUE_SUCCESS = "Thank you for letting us know, we will update you on the status shortly.";


    public static String CANCEL_ISSUE_POSITIVE_BUTTON = "रद्द करें";
    public static String ADD_ANSWER_POSITIVE_BUTTON = "जोड़ें";
    public static String DELETE_VISITOR_POSITIVE_BUTTON = "Delete";

    public static int TOKEN_EXPIRED_ERROR_CODE = 401;
    public static int TOKEN_REFRESH_FAILED_ERROR_CODE = 406;

    public static final String CLICK_ACTION_FULL_BROADCAST = "1";
    public static final String CLICK_ACTION_VISITORS = "2";
    public static final String CLICK_ACTION_RENTAL_DETAILS = "3";
    public static final String CLICK_ACTION_ISSUES = "4";
    public static final String CLICK_ACTION_GENERAL = "5";
    public static final String CLICK_ACTION_NEIGHBOUR_DETAIL = "7";
    public static final String CLICK_ACTION_ISSUE_RESOLVED = "8";
    public static final String CLICK_ACTION_EVENTS = "9";
    public static final String CLICK_ACTION_CONTRACT_EXTENSION = "6";


    //issues
    public static final String SNACKBAR_ISSUE_RAISED_SUCCESS = "Thank you for letting us know. We’ll update you on the status shortly.";
    public static final String SNACKBAR_ISSUE_CANCELLED_SUCCESS = "Your reported issue has been cancelled";
    public static final String SNACKBAR_ISSUE_REOPENED_SUCCESS = "Your issue has been reopened. We’ll update you on the status shortly.";

    //visitor
    public static final String SNACKBAR_EDIT_VISITOR_SUCCESS = "Your visitor details have been updated";
    public static final String SNACKBAR_REGISTER_VISITOR_SUCCESS = "Your visitor has been registered";
    public static final String SNACKBAR_VISITOR_DELETED_SUCCESS = "Your visitor registration has been cancelled";

    //rentals
    public static final String SNACKBAR_RENTAL_RECEIPTS_REQUESTED = "Your request has been noted. We’ll email you the receipts shortly.";
    public static final String NO_RECEIPT_HEADING = "NO RECEIPTS FOUND";
    public static final String NO_RECEIPT_SUB_HEADING = "If you’ve made a payment, contact your community manager.";
    public static final String NO_RECEIPT_BUTTON_LABEL = "Call community manager";

    //phone and otp
    public static final String SNACKBAR_OTP_SUCCESS = "Your OTP has been sent to your phone";
    public static final String SNACKBAR_ENTER_CORRECT_OTP = "Please enter the correct OTP";


    //payment history
    public static final String SNACKBAR_OLDER_THAN_HISTORY_SUCCESS = "Your request has been noted. We’ll email you the receipts shortly.";
    public static final String SNACKBAR_RSVP_FULL = "This event is full";


}
