package com.stayabode.features.login.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.stayabode.R;
import com.stayabode.base.activities.BaseActivity;
import com.stayabode.features.login.adapters.MultipleAnswersAdapter;
import com.stayabode.features.login.presenters.QuestionsPresenter;
import com.stayabode.features.login.views.QuestionsView;
import com.stayabode.net.response.getmodels.OptionObject;
import com.stayabode.net.response.getmodels.QuestionObject;
import com.stayabode.net.response.getmodels.QuestionsResponse;
import com.stayabode.net.response.getmodels.SubQuestionObject;
import com.stayabode.net.response.postmodels.AnswersPostResponse;
import com.stayabode.net.response.postmodels.HomeVisitObject;
import com.stayabode.net.response.postmodels.HomeVisitResponse;
import com.stayabode.net.response.postmodels.SubAnswersPostBaseResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;
import utils.Constants;
import utils.IntentKeys;
import utils.SharedPrefManager;

import static utils.Constants.LM;
import static utils.Constants.MB10;
import static utils.Constants.MB5;
import static utils.Constants.NM;
import static utils.Constants.RADIOGPP5;
import static utils.Constants.RADIOPOSH3;
import static utils.Constants.RADIOSPSP4;
import static utils.Constants.RADIOTVHSN;
import static utils.Constants.VALIDATION_CHECK;
import static utils.Constants.VALIDATION_EQ;
import static utils.Constants.VALIDATION_GOTO;
import static utils.Constants.VALIDATION_LIST;
import static utils.Constants.VALIDATION_LT;
import static utils.Constants.VALIDATION_MAX;

/**
 * Created by VarunBhalla on 16/11/16.
 */

public class QuestionsActivity extends BaseActivity implements QuestionsView {

    private QuestionsPresenter mQuestionsPresenter;

    private CoordinatorLayout mCoordinatorLayout;
    private LinearLayout mCheckboxsLayout;
    private LinearLayout mEditTextslayout;
    private Button mBtnNextQuestion;
    private QuestionsResponse questionResponse;
    private TextView mGroupName;
    private TextView mQuestion;
    private int mCurrentGroup = 0;
    private int mCurrentQuestionIndex = 0;
    private RadioGroup mRadioGroupAnswers;
    private Button mBtnPreviousQuestion;
    private Integer nextQuestionId;
    private String groupId;
    private RadioGroup ll;
    private int mPreviousQuestion;

    HashMap<Integer, AnswersPostResponse> questionSequence;
    private String currentAnswer;
    public AnswersPostResponse mCurrentApr;
    private RecyclerView mMultipleTextslayout;
    private MultipleAnswersAdapter multipleAnswersAdapter;

    private ArrayList<String> multipleAnswersArray;
    private CircleImageView mBtnAddAnswers;
    private Button mBtnAnswerSubQuestions;
    private SubAnswersPostBaseResponse allUsersSubQuestionAnswersResponse;
    private HashSet<String> chkBoxAnswers = new HashSet<>();
    private AnswersPostResponse mCurrentAprMultiAnswers;
    private String mValidation = "";

    HashMap<String, String> mQValidation = new HashMap<>();

    ArrayList<HomeVisitObject> mListGuideMapSubmissions = new ArrayList<>();
    private Integer mCurrentQuestionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        setActionBar();
        //disableBackArrow();
        disableActionBar();
        Intent questionResponseIntent = getIntent();
        questionResponse = (QuestionsResponse) questionResponseIntent.getParcelableExtra(IntentKeys.INTENT_QUESTIONSRESP);

        mGroupName = (TextView) findViewById(R.id.text_group);
        mQuestion = (TextView) findViewById(R.id.text_question);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mCheckboxsLayout = (LinearLayout) findViewById(R.id.checkboxes_answers);
        mEditTextslayout = (LinearLayout) findViewById(R.id.edittexts_answers);
        mMultipleTextslayout = (RecyclerView) findViewById(R.id.recycler_multiple_answers);
        mRadioGroupAnswers = (RadioGroup) findViewById(R.id.radiogroup_answers);
        mBtnNextQuestion = (Button) findViewById(R.id.button_next);
        mBtnPreviousQuestion = (Button) findViewById(R.id.button_previous);
        mBtnAddAnswers = (CircleImageView) findViewById(R.id.btn_add);
        mBtnAnswerSubQuestions = (Button) findViewById(R.id.button_answer_subquestions);

        mQuestionsPresenter = new QuestionsPresenter();
        mQuestionsPresenter.setView(this);

        mBtnPreviousQuestion.setEnabled(false);
        questionSequence = new HashMap<>();

        final Gson gson = new Gson();
        questionResponse = gson.fromJson(SharedPrefManager.getInstance().getQuestionsResponse(), QuestionsResponse.class);


        showFirstQuestion();

        mBtnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex--;
                mCurrentApr = questionSequence.get(mCurrentQuestionIndex);
                showPreviousQuestion(mCurrentApr);
            }
        });

        mBtnNextQuestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mCurrentApr != null) {
                    try {
                        if (mCurrentApr.getValidation().contains("#")) {
                            mQValidation.put(mCurrentApr.getValidation(), mCurrentApr.getAnswer());
                        }
                    } catch (Exception e) {

                    }


                    questionSequence.put(mCurrentQuestionIndex, mCurrentApr);
                    mCurrentQuestionIndex++;
                    showNextQuestion();
                }
            }
        });


        mBtnAddAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddAnswerDialog();
            }
        });


        mBtnAnswerSubQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<String> allanswers = multipleAnswersAdapter.getMultipleAnswersArray();
                    startSubQuestionActivity(questionResponse.getData().get(mCurrentGroup).getData().get(mCurrentApr.getQuestionId()).getSubQuestions(), allanswers);
                } catch (Exception e) {

                }
            }
        });
    }

    private void showAddAnswerDialog() {

        String message = Constants.REOPEN_ISSUE_DIALOG_LABEL;
        String positiveButtonText = Constants.ADD_ANSWER_POSITIVE_BUTTON;

        final LinearLayout wrapper = new LinearLayout(this);
        wrapper.setOrientation(LinearLayout.VERTICAL);

        final EditText input = new EditText(this);

        final TextView textQ1 = new EditText(this);
        RadioGroup rQq1 = new RadioGroup(this);
        RadioButton rb1q1 = new RadioButton(this);
        RadioButton rb2q1 = new RadioButton(this);
        rQq1.addView(rb1q1);
        rQq1.addView(rb2q1);

        final TextView textQ2 = new EditText(this);
        RadioGroup rQq2 = new RadioGroup(this);
        RadioButton rb1q2 = new RadioButton(this);
        RadioButton rb2q2 = new RadioButton(this);
        rQq2.addView(rb1q2);
        rQq2.addView(rb2q2);

        wrapper.addView(input);


//        if (mCurrentQuestionId == 112) {
//
//            textQ1.setText("dfSASAD");
//            textQ2.setText("dfSSDADSASAD");
//
//
//            wrapper.addView(textQ1);
//            wrapper.addView(rQq1);
//            wrapper.addView(textQ2);
//            wrapper.addView(rQq2);
//
//        }
//

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        wrapper.setLayoutParams(lp);


        new AlertDialog.Builder(this)
                .setMessage("")
                .setView(wrapper)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String inputAnswer = input.getText().toString().trim();
                        multipleAnswersArray.add(inputAnswer);
                        multipleAnswersAdapter.notifyItemInserted(multipleAnswersArray.size());
                        mCurrentAprMultiAnswers.setMultipleInputAnswers(multipleAnswersAdapter.getMultipleAnswersArray());


                        mCurrentApr = mCurrentAprMultiAnswers;


                    }
                })
                .setNegativeButton(Constants.DIALOG_NEGATIVE_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    private void changePreviousButtonState(int mCurrentQuestionIndex) {
        if (mCurrentQuestionIndex == 0) {
            mBtnPreviousQuestion.setEnabled(false);
        } else {
            mBtnPreviousQuestion.setEnabled(true);
        }
    }

    private void showFirstQuestion() {
        mGroupName.setText(questionResponse.getData().get(mCurrentGroup).getData().get(0).getG_name());
        mQuestion.setText(questionResponse.getData().get(mCurrentGroup).getData().get(0).getQ_name());

        if (questionResponse.getData().get(mCurrentGroup).getData().get(0).getQ_type().equalsIgnoreCase("radio")) {
            showRadioView();
            addRadioButtons(
                    questionResponse.getData().get(mCurrentGroup).getData().get(0),
                    0,
                    null);
            mRadioGroupAnswers.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {

        try {

            nextQuestionId = mCurrentApr.getNextQuestionId();
            groupId = mCurrentApr.getGroupID();

            if (nextQuestionId == 3) {
                nextQuestionId = 112;
            } else if (nextQuestionId == 113) {
                nextQuestionId = 116;
            } else if (nextQuestionId == 117) {
                nextQuestionId = 118;
            } else if (nextQuestionId == 119) {
                nextQuestionId = 121;
            } else if (nextQuestionId == 122) {
                nextQuestionId = 133;
            } else if (nextQuestionId == 134) {
                nextQuestionId = 145;
            } else if (nextQuestionId == 146) {
                nextQuestionId = 153;
            } else if (nextQuestionId == 154) {
                nextQuestionId = 162;
            } else if (nextQuestionId == 163) {
                nextQuestionId = 163;
            } else if (nextQuestionId == 164) {
                nextQuestionId = 164;
            } else if (nextQuestionId == 165) {
                nextQuestionId = 169;
            }

//            put(116, "अगले महीने सम्भावित प्रसव");
//            put(118, "पिछले माह मे मातृ जटिलता ");
//            put(121, "पिछले माह मे मातृ जटिलता ");
//            put(133, "पिछले माह मे प्रसव वाली महिला");
//            put(145, "कमजोर नवजात");
//            put(153, "अगले महीने 6 माह पूर्ण करने वाले बच्चे (बच्चे जो अभी 5 महीने के है) ");
//            put(162, "10 माह का बच्चा");
//            put(163, "पिछले माह मे मातृ मृत्यु");
//            put(164, "पिछले माह मे हुए 0 से 5 बर्ष के बच्चों की मृत्यु");
//            put(169, "पिछले तीन माह से लगातार नारंगी रंग मे आने वाला बच्चा जो बीमार भी है ");

            mCurrentApr = new AnswersPostResponse();

            if (nextQuestionId == -1) {

                showGenericErrorView();

            } else {

                mGroupName.setText(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getG_name());


                String answer;
                try {
                    answer = questionSequence.get(nextQuestionId).getAnswer();
                } catch (Exception e) {
                    answer = null;
                }


                String questionType = questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getQ_type();


                String qName = questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getQ_name();

                if (qName.contains(RADIOPOSH3)) {

                    String strReplace = resolveQuestion(RADIOPOSH3);
                    qName = qName.replace(RADIOPOSH3, strReplace);
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                } else if (qName.contains(RADIOSPSP4)) {
                    qName = qName.replace(RADIOSPSP4, resolveQuestion(RADIOSPSP4));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                } else if (qName.contains(RADIOGPP5)) {
                    qName = qName.replace(RADIOGPP5, resolveQuestion(RADIOGPP5));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                } else if (qName.contains(RADIOTVHSN)) {
                    qName = qName.replace(RADIOTVHSN, resolveQuestion(RADIOTVHSN));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);


                } else if (qName.contains(MB5)) {
                    qName = qName.replace(MB5, resolveMonth(MB5));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                } else if (qName.contains(MB10)) {
                    qName = qName.replace(MB10, resolveMonth(MB10));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                } else if (qName.contains(LM)) {
                    qName = qName.replace(LM, resolveMonth(LM));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                } else if (qName.contains(NM)) {
                    qName = qName.replace(NM, resolveMonth(NM));
                    questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).setQ_name(qName);
                }

                mCurrentQuestionId = Integer.parseInt(questionResponse.getData()
                        .get(mCurrentGroup).getData().get(nextQuestionId).getQ_id());

                mQuestion.setText(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getQ_id()
                        + ". " + questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getQ_name());


                if (questionType.equalsIgnoreCase("radio")) {
                    showRadioView();
                    addRadioButtons(
                            questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId),
                            nextQuestionId, answer);

                    mRadioGroupAnswers.setVisibility(View.VISIBLE);
                } else if (questionType.equalsIgnoreCase("Input") ||
                        questionType.equalsIgnoreCase("Input_validation") ||
                        questionType.equalsIgnoreCase("Input_No")) {
                    showInputView();

                    addEditText(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId),
                            nextQuestionId, answer, questionType);

                } else if (questionType.equalsIgnoreCase("Multiple_Input")) {

                    showMultipleInputView();

                    addMultipleInput(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId),
                            nextQuestionId,
                            answer,
                            questionSequence.get(mCurrentQuestionIndex));

                } else if (questionType.equalsIgnoreCase("CheckBox")) {

                    showCheckBoxView();
                    addCheckBoxes(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId)
                            , nextQuestionId,
                            answer,
                            questionSequence.get(mCurrentQuestionIndex));
                }

                mCurrentApr = questionSequence.get(mCurrentQuestionIndex);
            }
        } catch (Exception e) {
            System.out.println(e);
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


    private void startSubQuestionActivity(List<SubQuestionObject> subQuestions, ArrayList<String> allanswers) {
        Intent i = new Intent(QuestionsActivity.this, SubQuestionsActivity.class);
        i.putParcelableArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONSRESP, (ArrayList<? extends Parcelable>) subQuestions);
        i.putStringArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONS_INPUTS, allanswers);
        i.putExtra("mQValidation", mQValidation);
        startActivityForResult(i, 3);
    }

    @SuppressLint("SetTextI18n")
    private void showPreviousQuestion(AnswersPostResponse mTempCurrentApr) {
        mGroupName.setText(questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()).getG_name());

        mQuestion.setText(questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()).getQ_id()
                + ". " + questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()).getQ_name());


        String questionType = questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()).getQ_type();

        String answer;

        try {
            answer = questionSequence.get(mCurrentQuestionIndex).getAnswer();
        } catch (Exception e) {
            answer = null;
        }

        if (questionType.equalsIgnoreCase("radio")) {
            showRadioView();
            addRadioButtons(
                    questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()),
                    questionSequence.get(mCurrentQuestionIndex).getQuestionId(),
                    questionSequence.get(mCurrentQuestionIndex).getAnswer());
            mRadioGroupAnswers.setVisibility(View.VISIBLE);
        } else if (questionType.equalsIgnoreCase("Input") || questionType.equalsIgnoreCase("Input_validation")) {
            showInputView();
            addEditText(questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()),
                    questionSequence.get(mCurrentQuestionIndex).getQuestionId(),
                    answer, questionType);
        } else if (questionType.equalsIgnoreCase("Multiple_Input")) {
            showMultipleInputView();
            addMultipleInput(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId),
                    nextQuestionId,
                    answer,
                    questionSequence.get(mCurrentQuestionIndex));


        } else if (questionType.equalsIgnoreCase("CheckBox")) {
            showCheckBoxView();
            addCheckBoxes(questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId())
                    , questionSequence.get(mCurrentQuestionIndex).getQuestionId(),
                    answer, mTempCurrentApr);
        }
    }

    private void addMultipleInput(QuestionObject qObject, Integer currentQuestionId, String answer,
                                  AnswersPostResponse mTempCurrentApr) {

        final List<OptionObject> options = qObject.getOptions();

        if (qObject.getSubQuestions().size() > 0) {
            mBtnAnswerSubQuestions.setVisibility(View.VISIBLE);
        } else {
            mBtnAnswerSubQuestions.setVisibility(View.GONE);
        }

        try {
            multipleAnswersArray = mTempCurrentApr.getMultipleInputAnswers();
        } catch (Exception e) {
            multipleAnswersArray = new ArrayList<>();
        }


        if (multipleAnswersArray == null) {
            multipleAnswersArray = new ArrayList<>();
        }


        multipleAnswersAdapter = new MultipleAnswersAdapter(this, multipleAnswersArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMultipleTextslayout.setLayoutManager(linearLayoutManager);
        mMultipleTextslayout.setAdapter(multipleAnswersAdapter);


        nextQuestionId = Integer.parseInt(options.get(0).getNq_id());

        mCurrentAprMultiAnswers = new AnswersPostResponse();

        mCurrentAprMultiAnswers.setQuestionId(currentQuestionId);
        mCurrentAprMultiAnswers.setQuestionName(qObject.getQ_name());
        mCurrentAprMultiAnswers.setNextQuestionId(nextQuestionId);
        mCurrentAprMultiAnswers.setIsMandatory(qObject.getQ_mandatory());
        mCurrentAprMultiAnswers.setMultipleInputAnswers(multipleAnswersArray);
        mCurrentAprMultiAnswers.setValidation(qObject.getQ_validation());

    }


    private void addCheckBoxes(final QuestionObject qObject, final Integer currentQuestionId, String answer,
                               AnswersPostResponse mTempCurrentApr) {


        final List<OptionObject> options = qObject.getOptions();

        mCheckboxsLayout.removeAllViews();

        for (int row = 0; row < 1; row++) {

            try {
                chkBoxAnswers = mTempCurrentApr.getCheckboxAnswers();
            } catch (Exception e) {
                chkBoxAnswers = new HashSet<>();
            }

            for (int i = 0; i < options.size(); i++) {
                CheckBox cb = new CheckBox(this);
                cb.setId(View.generateViewId());
                cb.setText(options.get(i).getOption());
                cb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

                if (null != chkBoxAnswers && chkBoxAnswers.contains(options.get(i).getOption())) {
                    cb.setChecked(true);
                }

                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        CheckBox chk = (CheckBox) buttonView;
                        if (isChecked) {
                            chkBoxAnswers.add(chk.getText().toString());
                        } else {
                            chkBoxAnswers.remove(chk.getText().toString());
                        }

                        nextQuestionId = Integer.parseInt(options.get(0).getNq_id());

                        mCurrentApr = new AnswersPostResponse();
                        mCurrentApr.setQuestionId(currentQuestionId);
                        mCurrentApr.setQuestionName(qObject.getQ_name());
                        mCurrentApr.setIsMandatory(qObject.getQ_mandatory());
                        mCurrentApr.setNextQuestionId(nextQuestionId);
                        mCurrentApr.setCheckboxAnswers(chkBoxAnswers);
                        mCurrentApr.setValidation(qObject.getQ_validation());

                    }
                });


                mCheckboxsLayout.addView(cb);
            }
        }

        changePreviousButtonState(mCurrentQuestionIndex);

    }


    private void addEditText(final QuestionObject qObject, final Integer currentQuestionId, String answer, String qType) {


        int validationvalue = 0;
        int validationQuestionId = 0;
        int validationNextQuestionId = 0;

        final List<OptionObject> options = qObject.getOptions();

        mEditTextslayout.removeAllViews();

        final EditText et = new EditText(this); // this refers to the activity or the context.
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
        et.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        et.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et.setTextAppearance(this, R.style.BankDetailsText);
        et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        if (qObject.getQ_validation().contains(VALIDATION_EQ)) {
            String[] splited = qObject.getQ_validation().split(VALIDATION_EQ);


            splited = splited[1].split("GOTO");

            validationQuestionId = Integer.parseInt(splited[0]);
            validationNextQuestionId = Integer.parseInt(splited[1]);


            for (Map.Entry<Integer, AnswersPostResponse> entry : questionSequence.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                AnswersPostResponse apr = entry.getValue();

                if (apr.getQuestionId() == validationQuestionId) {
                    validationvalue = Integer.parseInt(apr.getAnswer());
                }

            }
        } else if (qObject.getQ_validation().contains(VALIDATION_CHECK)) {
            String[] splited = qObject.getQ_validation().split(VALIDATION_CHECK);

            validationQuestionId = Integer.parseInt(splited[1]);


            for (Map.Entry<Integer, AnswersPostResponse> entry : questionSequence.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                AnswersPostResponse apr = entry.getValue();

                if (apr.getQuestionId() == validationQuestionId) {
                    validationvalue = Integer.parseInt(apr.getAnswer());
                }
            }


        } else if (qObject.getQ_validation().contains(VALIDATION_GOTO)) {
            String[] splited = qObject.getQ_validation().split(VALIDATION_GOTO);

            validationQuestionId = Integer.parseInt(splited[1]);

            for (Map.Entry<Integer, AnswersPostResponse> entry : questionSequence.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                AnswersPostResponse apr = entry.getValue();

                if (apr.getQuestionId() == validationQuestionId) {

                    validationNextQuestionId = validationQuestionId;

                }

            }

        } else if (qObject.getQ_validation().contains(VALIDATION_LT)) {
            String[] splited = qObject.getQ_validation().split(VALIDATION_LT);

            validationQuestionId = Integer.parseInt(splited[1]);

            for (Map.Entry<Integer, AnswersPostResponse> entry : questionSequence.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                AnswersPostResponse apr = entry.getValue();

                if (apr.getQuestionId() == validationQuestionId) {
                    validationvalue = Integer.parseInt(apr.getAnswer());
                }
            }
        } else if (qObject.getQ_validation().contains(VALIDATION_MAX)) {
            String[] splited = qObject.getQ_validation().split(VALIDATION_MAX);

            //validationQuestionId = Integer.parseInt(splited[1]);

            for (Map.Entry<Integer, AnswersPostResponse> entry : questionSequence.entrySet()) {

                AnswersPostResponse apr = entry.getValue();

                if (apr.getQuestionId() == validationQuestionId) {
                    validationvalue = Integer.parseInt(splited[1]);
                }
            }
        }

        final int finalValidationvalue = validationvalue;
        final int finalvalidationNextQuestionId = validationNextQuestionId;
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int checkSum = 0;
                mCurrentApr = new AnswersPostResponse();
                boolean shoudSaveAnswer = true;
                currentAnswer = s.toString();

                if (qObject.getQ_validation().contains(VALIDATION_EQ)) {

                    try {
                        if (finalValidationvalue == Integer.parseInt(currentAnswer)) {
                            nextQuestionId = finalvalidationNextQuestionId;
                        } else {
                            nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                        }
                    } catch (Exception e) {
                        nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                    }

                } else if (qObject.getQ_validation().contains(VALIDATION_MAX)) {

                    nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                    try {
                        if (Integer.parseInt(currentAnswer) <= finalValidationvalue) {
                            shoudSaveAnswer = true;
                            mBtnNextQuestion.setEnabled(true);
                        } else {
                            shoudSaveAnswer = false;
                            et.setError("Maximum value can be " + finalValidationvalue);
                            mBtnNextQuestion.setEnabled(false);
                        }
                    } catch (Exception e) {
                        shoudSaveAnswer = false;
                        et.setError("Maximum value can be " + finalValidationvalue);
                        mBtnNextQuestion.setEnabled(false);
                    }

                } else if (qObject.getQ_validation().contains(VALIDATION_LT)) {

                    nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                    try {
                        if (Integer.parseInt(currentAnswer) <= finalValidationvalue) {
                            shoudSaveAnswer = true;
                            mBtnNextQuestion.setEnabled(true);
                        } else {
                            shoudSaveAnswer = false;
                            et.setError("Answer should be less than " + finalValidationvalue);
                            mBtnNextQuestion.setEnabled(false);
                        }
                    } catch (Exception e) {
                        shoudSaveAnswer = false;
                        et.setError("Answer should be less than " + finalValidationvalue);
                        mBtnNextQuestion.setEnabled(false);
                    }
                } else if (qObject.getQ_validation().contains(VALIDATION_CHECK)) {

                    nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                    for (Map.Entry<Integer, AnswersPostResponse> entry : questionSequence.entrySet()) {

                        AnswersPostResponse apr = entry.getValue();

                        try {
                            if (apr.getQuestionId() == 136 ||
                                    apr.getQuestionId() == 137 ||
                                    apr.getQuestionId() == 138) {

                                if (apr.getQuestionId() != Integer.parseInt(qObject.getQ_id())) {
                                    checkSum = checkSum + Integer.parseInt(apr.getAnswer());
                                }
                            }
                        } catch (Exception e) {

                        }

                    }

                    try {
                        checkSum = checkSum + Integer.parseInt(currentAnswer);
                    } catch (Exception e) {

                    }


                    if (checkSum <= finalValidationvalue) {
                        shoudSaveAnswer = true;
                        mBtnNextQuestion.setEnabled(true);
                    } else {
                        shoudSaveAnswer = false;
                        et.setError("Enter correct value");
                        mBtnNextQuestion.setEnabled(false);
                    }

                } else if (qObject.getQ_validation().contains(VALIDATION_GOTO)) {

                    try {
                        if (Integer.parseInt(currentAnswer) == 0) {
                            nextQuestionId = finalvalidationNextQuestionId;
                        } else {
                            nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                        }
                    } catch (Exception e) {
                        nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                    }

                } else {
                    nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                }


                if (shoudSaveAnswer) {


                    mCurrentApr.setQuestionId(currentQuestionId);
                    mCurrentApr.setAnswer(currentAnswer);
                    mCurrentApr.setQuestionName(qObject.getQ_name());
                    mCurrentApr.setNextQuestionId(nextQuestionId);
                    mCurrentApr.setIsMandatory(qObject.getQ_mandatory());
                    mCurrentApr.setValidation(qObject.getQ_validation());

                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditTextslayout.addView(et);


    }

    public void addRadioButtons(final QuestionObject qObject, final Integer currentQuestionId, String answer) {


        final List<OptionObject> options = qObject.getOptions();


        mCurrentApr = questionSequence.get(mCurrentQuestionIndex);

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

                mCurrentApr = new AnswersPostResponse();
                mCurrentApr.setQuestionId(currentQuestionId);
                mCurrentApr.setAnswer(currentAnswer);
                mCurrentApr.setQuestionName(qObject.getQ_name());
                mCurrentApr.setNextQuestionId(nextQuestionId);
                mCurrentApr.setValidation(qObject.getQ_validation());

            }
        });


        mRadioGroupAnswers.addView(ll);

        changePreviousButtonState(mCurrentQuestionIndex);


    }


    @Override
    public void onRegistrationVerified() {
        hideLoadingView();
//        Intent i = new Intent(QuestionsActivity.this, InputOtpActivity.class);
//        i.putExtra(IntentKeys.INTENT_PHONE, mEtUserName.getText().toString().trim());
//        startActivity(i);
    }

    @Override
    public void onRequestFailed(int errorCode, String message) {
        hideLoadingView();
//        if(errorCode == 400){
//            showRegistrationError(Constants.PHONE_REGISTRATION_ERROR);
//        }else{
//            ViewUtils.showSnackBar(QuestionsActivity.this, mCoordinatorLayout, message);
//        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            try {

                String allUsersSubQuestionAnswersResponseString = data.getStringExtra(IntentKeys.INTENT_SUB_QUESTIONS_ANSWERS_RESP);

                final Gson gson = new Gson();
                try {
                    allUsersSubQuestionAnswersResponse = gson.fromJson(allUsersSubQuestionAnswersResponseString, SubAnswersPostBaseResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mCurrentAprMultiAnswers.setSubAnswersResponse(allUsersSubQuestionAnswersResponse);
                mCurrentApr = mCurrentAprMultiAnswers;

                Random randomGenerator = new Random();
                int index = randomGenerator.nextInt(mCurrentApr.getMultipleInputAnswers().size());


                HomeVisitObject hvo = new HomeVisitObject();
                hvo.setQid(mCurrentApr.getQuestionId() + "");
                hvo.setQname(mCurrentApr.getQuestionName());
                hvo.setUuid("");
                hvo.setBenificiaryname(mCurrentApr.getMultipleInputAnswers().get(index));
                hvo.setGid(mCurrentApr.getGroupID());


                mListGuideMapSubmissions.add(hvo);
            } catch (Exception e) {

            }
        }
    }

    private void showInputView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.VISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
        mMultipleTextslayout.setVisibility(View.INVISIBLE);
        mBtnAddAnswers.setVisibility(View.GONE);
        mBtnAnswerSubQuestions.setVisibility(View.GONE);
    }

    private void showRadioView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.VISIBLE);
        mMultipleTextslayout.setVisibility(View.INVISIBLE);
        mBtnAddAnswers.setVisibility(View.GONE);
        mBtnAnswerSubQuestions.setVisibility(View.GONE);
    }

    private void showCheckBoxView() {
        mCheckboxsLayout.setVisibility(View.VISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
        mMultipleTextslayout.setVisibility(View.INVISIBLE);
        mBtnAddAnswers.setVisibility(View.GONE);
        mBtnAnswerSubQuestions.setVisibility(View.GONE);
    }

    private void showMultipleInputView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
        mMultipleTextslayout.setVisibility(View.VISIBLE);
        mBtnAddAnswers.setVisibility(View.VISIBLE);
        mBtnAnswerSubQuestions.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onErrorBtnClick() {
        super.onErrorBtnClick();
//        if (mListGuideMapSubmissions != null && !mListGuideMapSubmissions.isEmpty()) {
//            for (Map.Entry<Integer, HomeVisitObject> entry : mListGuideMapSubmissions.entrySet()) {
//                int key = entry.getKey();
//                HomeVisitObject value = entry.getValue();
//                Timber.i("Response:: Key: %s, Value: %s", key, value);
//            }
//        } else {
//            Timber.i("Response: Failure::" + "Empty Map mListGuideMapSubmissions");
//        }

        HomeVisitResponse homeVisitResponseold;

        String homeVisitResponseStr = SharedPrefManager.getInstance().getHomeVisitList();
        if (null != homeVisitResponseStr) {
            homeVisitResponseold = (new Gson()).fromJson(homeVisitResponseStr, HomeVisitResponse.class);
            mListGuideMapSubmissions.addAll(homeVisitResponseold.getmHomeVisitList());
        }


        HomeVisitResponse hvr = new HomeVisitResponse();
        hvr.setmHomeVisitList(mListGuideMapSubmissions);
        String hvrStr = (new Gson()).toJson(hvr);
        SharedPrefManager.getInstance().setHomeVisitList(hvrStr);


        Intent i = new Intent(QuestionsActivity.this, HomeVisitActivity.class);
        i.putParcelableArrayListExtra("listGuideMapSubmissions", mListGuideMapSubmissions);
        startActivity(i);
        finish();

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

}
