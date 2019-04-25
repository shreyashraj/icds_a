package com.stayabode.net.response.getmodels;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;

public class QuestionObject implements Parcelable {

    private String q_id;
    private String g_name;
    private String q_name;
    private String q_type;
    private String q_hint;
    private String q_mandatory;
    private String q_validation;
    private List<OptionObject> options;
    private List<SubQuestionObject> subQuestions;

    public QuestionObject(String q_id, String g_name, String q_name, String q_type, String q_hint, String q_mandatory,
                          String q_validation, List<OptionObject> options, List<SubQuestionObject> subQuestions) {
        super();
        this.q_id = q_id;
        this.g_name = q_id;
        this.q_name = q_name;
        this.q_type = q_type;
        this.q_hint = q_hint;
        this.q_mandatory = q_mandatory;
        this.q_validation = q_validation;
        this.options = options;
        this.subQuestions = subQuestions;
    }

    protected QuestionObject(Parcel in) {

        q_id = in.readString();
        g_name = in.readString();
        q_name = in.readString();
        q_type = in.readString();
        q_hint = in.readString();
        q_mandatory = in.readString();
        q_validation = in.readString();

        options = in.readArrayList(OptionObject.class.getClassLoader());
        subQuestions = in.readArrayList(SubQuestionObject.class.getClassLoader());
    }

    public static final Creator<QuestionObject> CREATOR = new Creator<QuestionObject>() {
        @Override
        public QuestionObject createFromParcel(Parcel in) {
            return new QuestionObject(in);
        }

        @Override
        public QuestionObject[] newArray(int size) {
            return new QuestionObject[size];
        }
    };

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getQ_name() {
        return q_name;
    }

    public void setQ_name(String q_name) {
        this.q_name = q_name;
    }

    public String getQ_type() {
        return q_type;
    }

    public void setQ_type(String q_type) {
        this.q_type = q_type;
    }

    public String getQ_hint() {
        return q_hint;
    }

    public void setQ_hint(String q_hint) {
        this.q_hint = q_hint;
    }

    public String getQ_mandatory() {
        return q_mandatory;
    }

    public void setQ_mandatory(String q_mandatory) {
        this.q_mandatory = q_mandatory;
    }

    public String getQ_validation() {
        return q_validation;
    }

    public void setQ_validation(String q_validation) {
        this.q_validation = q_validation;
    }

    public List<OptionObject> getOptions() {
        return options;
    }

    public void setOptions(List<OptionObject> options) {
        this.options = options;
    }

    public List<SubQuestionObject> getSubQuestions() {
        return subQuestions;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public void setSubQuestions(List<SubQuestionObject> subQuestions) {
        this.subQuestions = subQuestions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(q_id);
        dest.writeString(g_name);
        dest.writeString(q_name);
        dest.writeString(q_type);
        dest.writeString(q_hint);
        dest.writeString(q_mandatory);
        dest.writeString(q_validation);
        dest.writeList(options);
        dest.writeList(subQuestions);
    }
}
