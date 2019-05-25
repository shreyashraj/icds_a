package utils;

import java.util.HashMap;

/**
 * Created by VarunBhalla on 13/10/16.
 */
public class Constants {


    public static final HashMap<Integer, String> listGuideMap = new HashMap<Integer, String>() {{
        put(112, "अगले महीने सम्भावित प्रसव");
        put(116, "अगले महीने सम्भावित प्रसव");
        put(118, "पिछले माह मे मातृ जटिलता ");
        put(121, "पिछले माह मे मातृ जटिलता ");
        put(133, "पिछले माह मे प्रसव वाली महिला");
        put(145, "कमजोर नवजात");
        put(153, "अगले महीने 6 माह पूर्ण करने वाले बच्चे (बच्चे जो अभी 5 महीने के है) ");
        put(162, "10 माह का बच्चा");
        put(163, "पिछले माह मे मातृ मृत्यु");
        put(164, "पिछले माह मे हुए 0 से 5 बर्ष के बच्चों की मृत्यु");
        put(169, "पिछले तीन माह से लगातार नारंगी रंग मे आने वाला बच्चा जो बीमार भी है ");
    }};

    public static final HashMap<Integer, String> listViewTitle = new HashMap<Integer,String >(){{
        put(116, "अगले महीने सम्भावित प्रसव");
        put(133, "पिछले माह मे जीवित जन्म");
        put(153, "5 माह का बच्चा");
        put(162, "10 माह का बच्चा");
        put(163, "पिछले माह मे मातृ मृत्यु");
        put(164, "पिछले माह मे बाल मृत्यु");
        put(145, "कमजोर नवजात");
        put(118, "पिछले माह मे मातृ जटिलता");
        put(121, "पिछले माह मे मातृ जटिलता");
        put(169, "लागातार तीन माह से नारंगी रंग मे आने के साथ साथ जो बच्चा बीमार भी हो");
    }};


    public static final String INTERNET_ERROR = "Please recheck your internet connection";

    public static final String CANCEL_ISSUE_DIALOG_LABEL = "Are you sure you would like to cancel this issue?";
    public static final String REOPEN_ISSUE_DIALOG_LABEL = "Are you sure you want to reopen the issue?";

    public static final String DIALOG_NEGATIVE_BUTTON_TEXT = "रद्द करें" +
            "\n" +
            "\n";
    public static final String GENERIC_ERROR = "Uh-oh, something went wrong. Please retry in sometime";

    public static String CANCEL_ISSUE_POSITIVE_BUTTON = "रद्द करें";
    public static String ADD_ANSWER_POSITIVE_BUTTON = "जोड़ें";


    public static final String RADIOPOSH3 = "#POSH-3#";
    public static final String RADIOSPSP4 = "#SPSP-4#";
    public static final String RADIOGPP5 = "#GPP-5#";
    public static final String RADIOTVHSN = "#TVHSN#";

    public static final String MB5 = "#5MB";
    public static final String MB10 = "#10MB";
    public static final String LM = "#LM#";
    public static final String NM = "#NM#";

    public static final String VALIDATION_CHECK = "CHECK";
    public static final String VALIDATION_GOTO = "GOTO";
    public static final String VALIDATION_LIST = "LIST";
    public static final String VALIDATION_LT = "LT";
    public static final String VALIDATION_MAX = "MAX";
    public static final String VALIDATION_EQ = "EQ";

}
