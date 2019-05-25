package com.stayabode.features.login.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.net.response.getmodels.OptionObject;
import com.stayabode.net.response.getmodels.SubQuestionObject;
import com.stayabode.net.response.postmodels.SubAnswersPostBaseResponse;
import com.stayabode.net.response.postmodels.SubAnswersPostResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import utils.IntentKeys;

import static utils.Constants.LM;
import static utils.Constants.MB10;
import static utils.Constants.MB5;
import static utils.Constants.NM;
import static utils.Constants.RADIOGPP5;
import static utils.Constants.RADIOPOSH3;
import static utils.Constants.RADIOSPSP4;
import static utils.Constants.RADIOTVHSN;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public class SubQuestionsActivity extends BaseActivity {


    private CoordinatorLayout mCoordinatorLayout;
    private LinearLayout mCheckboxsLayout;
    private LinearLayout mEditTextslayout;
    private Button mBtnNextQuestion;
    private TextView mGroupName;
    private TextView mQuestion;
    private int mCurrentGroup = 0;
    private int mCurrentQuestionIndex = 0;
    private RadioGroup mRadioGroupAnswers;
    private Button mBtnPreviousQuestion;
    private Integer nextQuestionId;
    private RadioGroup ll;
    private int mPreviousQuestion;

    HashMap<Integer, SubAnswersPostResponse> subQuestionSequence;
    private String currentAnswer;
    private SubAnswersPostResponse mCurrentApr;
    private ArrayList<SubQuestionObject> subQuestionResponse;
    private ArrayList<String> subQuestionInputs;

    HashMap<String, String> mQValidation = new HashMap<>();

    ArrayList<HashMap<Integer, SubAnswersPostResponse>> allUsersSubQuestionAnswersResponse = new ArrayList<>();

    private int subQuestionInputIndex = 0;
    private String mValidation;
    private LinearLayout mDatePickerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        setActionBar();
        disableBackArrow();
        disableActionBar();

        mGroupName = (TextView) findViewById(R.id.text_group);
        mQuestion = (TextView) findViewById(R.id.text_question);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mCheckboxsLayout = (LinearLayout) findViewById(R.id.checkboxes_answers);
        mDatePickerLayout = (LinearLayout) findViewById(R.id.datepicker_answers);
        mEditTextslayout = (LinearLayout) findViewById(R.id.edittexts_answers);
        mRadioGroupAnswers = (RadioGroup) findViewById(R.id.radiogroup_answers);
        mBtnNextQuestion = (Button) findViewById(R.id.button_next);
        mBtnPreviousQuestion = (Button) findViewById(R.id.button_previous);

        Intent subQuestionResponseIntent = getIntent();
        subQuestionResponse = subQuestionResponseIntent.getParcelableArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONSRESP);
        subQuestionInputs = subQuestionResponseIntent.getStringArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONS_INPUTS);
        mQValidation = (HashMap<String, String>) subQuestionResponseIntent.getSerializableExtra("mQValidation");



        mBtnPreviousQuestion.setEnabled(false);
        subQuestionSequence = new HashMap<>();

        showFirstQuestion();

        mBtnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentQuestionIndex--;
                mCurrentApr = subQuestionSequence.get(mCurrentQuestionIndex);
                showPreviousQuestion();

            }
        });

        mBtnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCurrentApr != null) {
//                    try {
//                        if (mCurrentApr.getValidation().contains("#")) {
//                            mQValidation.put(mCurrentApr.getValidation(), mCurrentApr.getAnswer());
//                        }
//                    } catch (Exception e) {
//
//                    }


                    subQuestionSequence.put(mCurrentQuestionIndex, mCurrentApr);
                    mCurrentQuestionIndex++;
                    showNextQuestion(mCurrentApr.getNextQuestionId());
                }

            }
        });
    }

    private void changePreviousButtonState(int mCurrentQuestionIndex) {
        if (mCurrentQuestionIndex == 0) {
            mBtnPreviousQuestion.setEnabled(false);
        } else {
            mBtnPreviousQuestion.setEnabled(true);
        }

    }

    private void showFirstQuestion() {
        mGroupName.setText(subQuestionResponse.get(0).getG_name());

        String question = subQuestionResponse.get(0).getSub_question();
        question = question.replace("REPLACE", subQuestionInputs.get(subQuestionInputIndex));

        mQuestion.setText(question);

        if (subQuestionResponse.get(0).getSub_type().equalsIgnoreCase("radio")) {
            showRadioView();
            addRadioButtons(
                    subQuestionResponse.get(0),
                    0,
                    null);
            mRadioGroupAnswers.setVisibility(View.VISIBLE);
        } else {
            showInputView();
            addEditText(subQuestionResponse.get(0),
                    0, null,subQuestionResponse.get(0).getSub_type());
        }
    }

    private void showNextQuestion(Integer nextQuestionId) {

        // try {
        if (nextQuestionId == -1) {

            if (subQuestionInputIndex == subQuestionInputs.size() - 1) {
                allUsersSubQuestionAnswersResponse.add(subQuestionSequence);

                SubAnswersPostBaseResponse sapbs = new SubAnswersPostBaseResponse();
                sapbs.setAllUsersSubQuestionAnswersResponse(allUsersSubQuestionAnswersResponse);


                final Gson g = new Gson();
                final String allUsersSubQuestionAnswersResponseString = g.toJson(sapbs);

                Intent resultIntent = new Intent();
                resultIntent.putExtra(IntentKeys.INTENT_SUB_QUESTIONS_ANSWERS_RESP, allUsersSubQuestionAnswersResponseString);
                setResult(Activity.RESULT_OK, resultIntent);


                finish();

            } else {

                allUsersSubQuestionAnswersResponse.add(subQuestionSequence);

                mCurrentQuestionIndex = 0;
                subQuestionInputIndex++;
                mBtnPreviousQuestion.setEnabled(false);
                subQuestionSequence = new HashMap<>();

                showFirstQuestion();
            }


        } else {

            mBtnPreviousQuestion.setEnabled(true);

            String questionType =subQuestionResponse.get(nextQuestionId).getSub_type();


            String qName = subQuestionResponse.get(nextQuestionId).getSub_question();

            if (qName.contains(RADIOPOSH3)) {

                String strReplace = resolveQuestion(RADIOPOSH3);
                qName = qName.replace(RADIOPOSH3, strReplace);
                subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            } else if (qName.contains(RADIOSPSP4)) {
                qName = qName.replace(RADIOSPSP4, resolveQuestion(RADIOSPSP4));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            } else if (qName.contains(RADIOGPP5)) {
                qName = qName.replace(RADIOGPP5, resolveQuestion(RADIOGPP5));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            } else if (qName.contains(RADIOTVHSN)) {
                qName = qName.replace(RADIOTVHSN, resolveQuestion(RADIOTVHSN));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);


            } else if (qName.contains(MB5)) {
                qName = qName.replace(MB5, resolveMonth(MB5));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            } else if (qName.contains(MB10)) {
                qName = qName.replace(MB10, resolveMonth(MB10));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            } else if (qName.contains(LM)) {
                qName = qName.replace(LM, resolveMonth(LM));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            } else if (qName.contains(NM)) {
                qName = qName.replace(NM, resolveMonth(NM));
               subQuestionResponse.get(nextQuestionId).setSub_question(qName);
            }



            mGroupName.setText(subQuestionResponse.get(nextQuestionId).getG_name());

            String question = subQuestionResponse.get(nextQuestionId).getSub_question();
            question = question.replace("REPLACE", subQuestionInputs.get(subQuestionInputIndex));
            mQuestion.setText(question);

            String answer;
            try {
                answer = subQuestionSequence.get(nextQuestionId).getAnswer();
            } catch (Exception e) {
                answer = null;
            }


            if (questionType.equalsIgnoreCase("radio")) {
                showRadioView();
                addRadioButtons(
                        subQuestionResponse.get(nextQuestionId),
                        nextQuestionId, answer);

                mRadioGroupAnswers.setVisibility(View.VISIBLE);
            } else if (questionType.equalsIgnoreCase("Input") ||
                    questionType.equalsIgnoreCase("Input_validation") ||
                    questionType.equalsIgnoreCase("Input_No")) {
                showInputView();
                addEditText(subQuestionResponse.get(nextQuestionId), nextQuestionId, answer,questionType);
            } else if (questionType.equalsIgnoreCase("Multiple_Input")) {
                showCheckBoxView();
            } else if (questionType.equalsIgnoreCase("date")) {
                showDatePickerView();
                addDatePickerView(subQuestionResponse.get(nextQuestionId), nextQuestionId, answer);
            }

            mCurrentApr = subQuestionSequence.get(mCurrentQuestionIndex);
        }

//        } catch (Exception e) {
//
//        }
    }


    private void showPreviousQuestion() {
        mGroupName.setText(subQuestionResponse.get(subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId()).getG_name());


        String question = subQuestionResponse.get(subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId()).getSub_question();
        question = question.replace("REPLACE", subQuestionInputs.get(subQuestionInputIndex));
        mQuestion.setText(question);

        String questionType = subQuestionResponse.get(subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId()).getSub_type();

        String answer;

        try {
            answer = subQuestionSequence.get(mCurrentQuestionIndex).getAnswer();
        } catch (Exception e) {
            answer = null;
        }

        if (questionType.equalsIgnoreCase("radio")) {
            showRadioView();
            addRadioButtons(
                    subQuestionResponse.get(subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId()),
                    subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId(),
                    subQuestionSequence.get(mCurrentQuestionIndex).getAnswer());
            mRadioGroupAnswers.setVisibility(View.VISIBLE);
        } else if (questionType.equalsIgnoreCase("Input") || questionType.equalsIgnoreCase("Input_validation")) {
            showInputView();
            addEditText(subQuestionResponse.get(subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId()),
                    subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId(),
                    answer,questionType);
        } else if (questionType.equalsIgnoreCase("date")) {
            showDatePickerView();
            addDatePickerView(subQuestionResponse.get(subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId()),
                    subQuestionSequence.get(mCurrentQuestionIndex).getQuestionId(),
                    answer);
        }
    }


    private void addCheckBoxes(int number) {
        for (int row = 0; row < 1; row++) {

            for (int i = 1; i <= number; i++) {
                CheckBox cb = new CheckBox(this);
                cb.setId(View.generateViewId());
                cb.setText("Radio " + cb.getId());
                mCheckboxsLayout.addView(cb);
            }
        }
    }

    private void addDatePickerView(SubQuestionObject qObject,
                                   final Integer currentQuestionId, final String answer) {

        final List<OptionObject> options = qObject.getOptions();


        LinearLayout mAnswerWrapper = new LinearLayout(this);
        ImageView mAnswerIcon = new ImageView(this);
        final TextView mAnswerTv = new TextView(this);


        mAnswerIcon.setImageDrawable(getResources().getDrawable(R.drawable.com_facebook_button_icon));
        mAnswerTv.setText(answer);
        mAnswerTv.setTextColor(ContextCompat.getColor(this, R.color.black_100));
        mAnswerTv.setTextSize(getResources().getDimension(R.dimen.text_size_xxxsmall));
        mAnswerTv.setPadding(8, 0, 0, 0);

        mDatePickerLayout.removeAllViews();

        mAnswerWrapper.setOrientation(LinearLayout.HORIZONTAL);
        mAnswerWrapper.addView(mAnswerIcon);
        mAnswerWrapper.addView(mAnswerTv);

        mDatePickerLayout.addView(mAnswerWrapper);

        mAnswerWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDateDialog(mAnswerTv, currentQuestionId, options);
            }
        });

    }

    private void showAddDateDialog(final TextView mAnswerTv, final Integer currentQuestionId, final List<OptionObject> options) {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String dateString = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        currentAnswer = dateString;

                        mAnswerTv.setText(dateString);
                        mAnswerTv.setError(null);
                        nextQuestionId = Integer.parseInt(options.get(0).getNq_id());

                        mCurrentApr = new SubAnswersPostResponse();
                        mCurrentApr.setQuestionId(currentQuestionId);
                        mCurrentApr.setAnswer(currentAnswer);
                        mCurrentApr.setNextQuestionId(nextQuestionId);


                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }


    private void addEditText(final SubQuestionObject qObject,
                             final Integer currentQuestionId, String answer,String qType) {

        final List<OptionObject> options = qObject.getOptions();

        mEditTextslayout.removeAllViews();

        mValidation = qObject.getSub_validation();


        EditText et = new EditText(this); // this refers to the activity or the context.
        if (null != answer) {
            et.setText(answer);
        } else {
            et.setText("");
        }

        if (qType.equalsIgnoreCase("Input_No") ||
                qType.equalsIgnoreCase("Input_Validation")) {
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
        }


        et.setMaxLines(1);
        et.setSingleLine();
        et.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et.setTextAppearance(this, R.style.BankDetailsText);
        et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        et.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    currentAnswer = s.toString();
                    nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                    mCurrentApr = new SubAnswersPostResponse();
                    mCurrentApr.setQuestionId(currentQuestionId);
                    mCurrentApr.setAnswer(currentAnswer);
                    mCurrentApr.setNextQuestionId(nextQuestionId);
                } catch (Exception e) {

                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditTextslayout.addView(et);


    }

    @SuppressLint("NewApi")
    public void addRadioButtons(final SubQuestionObject qObject,
                                final Integer currentQuestionId, String answer) {


        final List<OptionObject> options = qObject.getOptions();


        mCurrentApr = subQuestionSequence.get(mCurrentQuestionIndex);

        try {
            ll.removeAllViews();
        } catch (Exception e) {

        }

        int number = options.size();


        ll = new RadioGroup(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < number; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(options.get(i).getOption());
            rdbtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

            if (options.get(i).getOption().equalsIgnoreCase(answer)) {
                rdbtn.setChecked(true);
            }

            ll.addView(rdbtn);
        }


        ll.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                int idx = ll.indexOfChild(radioButton);


                nextQuestionId = Integer.parseInt(options.get(idx).getNq_id());
                currentAnswer = radioButton.getText().toString();

                mCurrentApr = new SubAnswersPostResponse();
                mCurrentApr.setQuestionId(currentQuestionId);
                mCurrentApr.setAnswer(currentAnswer);
                mCurrentApr.setNextQuestionId(nextQuestionId);


                // Toast.makeText(getBaseContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        mRadioGroupAnswers.addView(ll);

        changePreviousButtonState(mCurrentQuestionIndex);


    }

    private void showDatePickerView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
        mDatePickerLayout.setVisibility(View.VISIBLE);
    }

    private void showInputView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.VISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
        mDatePickerLayout.setVisibility(View.INVISIBLE);
    }

    private void showRadioView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.VISIBLE);
        mDatePickerLayout.setVisibility(View.INVISIBLE);
    }

    private void showCheckBoxView() {
        mCheckboxsLayout.setVisibility(View.VISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
        mDatePickerLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("");
    }

    public String resolveQuestion(String hashId) {

        switch (hashId) {
            case "#POSH-3#":
                if (mQValidation.get(hashId).equalsIgnoreCase("yes")) {
                    return "पूरक पोषाहार वितरण पंजी 3";
                } else {
                    return "पूरक पोषाहार वितरण सम्बंधित अन्य पंजी";
                }
            case "#SPSP-4#":
                if (mQValidation.get(hashId).equalsIgnoreCase("yes")) {
                    return "स्कूल पूर्व शिक्षा पंजी- 4";
                } else {
                    return "स्कूल पूर्व शिक्षा सम्बंधी अन्य पंजी";
                }
            case "#GPP-5#":
                if (mQValidation.get(hashId).equalsIgnoreCase("yes")) {
                    return "गर्भावस्था एवं प्रसव पंजी- 5";
                } else {
                    return "गर्भावस्था एवं प्रसव सम्बंधी अन्य पंजी";
                }
            case "#TVHSN#":
                if (mQValidation.get(hashId).equalsIgnoreCase("yes")) {
                    return "टीकाकरण एवं वी.एच.एस.एन.डी. पंजी- 6";
                } else {
                    return "टीकाकरण एवं वी.एच.एस.एन.डी. सम्बंधी अन्य पंजी";
                }

            default:
                return "";

        }
    }

    private static String resolveMonth(String key) {
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int foundMonth = 0;
        switch (key) {
            case "#LM#":
                cal.add(Calendar.MONTH, -1);
                foundMonth = cal.get(Calendar.MONTH);
                System.out.println(monthName[foundMonth]);
                return monthName[foundMonth];
            case "#NM#":
                cal.add(Calendar.MONTH, 1);
                foundMonth = cal.get(Calendar.MONTH);
                System.out.println(monthName[foundMonth]);
                return monthName[foundMonth];
            case "#5MB":
                cal.add(Calendar.MONTH, -5);
                foundMonth = cal.get(Calendar.MONTH);
                System.out.println(monthName[currentMonth] + " - " + monthName[foundMonth]);
                return monthName[foundMonth] + " - " + monthName[currentMonth];
            case "#10MB":
                cal.add(Calendar.MONTH, -10);
                foundMonth = cal.get(Calendar.MONTH);
                System.out.println(monthName[currentMonth] + " - " + monthName[foundMonth]);
                System.out.println(monthName[currentMonth] + " - " + monthName[foundMonth]);
                return monthName[foundMonth] + " - " + monthName[currentMonth];
        }
        return "";

    }



}
