package com.stayabode.features.login.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.List;

import utils.IntentKeys;

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

    ArrayList<HashMap<Integer, SubAnswersPostResponse>> allUsersSubQuestionAnswersResponse = new ArrayList<>();

    private int subQuestionInputIndex = 0;
    private String mValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        setActionBar();
        disableBackArrow();

        mGroupName = (TextView) findViewById(R.id.text_group);
        mQuestion = (TextView) findViewById(R.id.text_question);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mCheckboxsLayout = (LinearLayout) findViewById(R.id.checkboxes_answers);
        mEditTextslayout = (LinearLayout) findViewById(R.id.edittexts_answers);
        mRadioGroupAnswers = (RadioGroup) findViewById(R.id.radiogroup_answers);
        mBtnNextQuestion = (Button) findViewById(R.id.button_next);
        mBtnPreviousQuestion = (Button) findViewById(R.id.button_previous);

        Intent subQuestionResponseIntent = getIntent();
        subQuestionResponse = subQuestionResponseIntent.getParcelableArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONSRESP);
        subQuestionInputs = subQuestionResponseIntent.getStringArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONS_INPUTS);

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

                subQuestionSequence.put(mCurrentQuestionIndex, mCurrentApr);
                mCurrentQuestionIndex++;
                showNextQuestion(mCurrentApr.getNextQuestionId());
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

        mQuestion.setText( question);

        if (subQuestionResponse.get(0).getSub_type().equalsIgnoreCase("radio")) {
            showRadioView();
            addRadioButtons(
                    subQuestionResponse.get(0),
                    0,
                    null);
            mRadioGroupAnswers.setVisibility(View.VISIBLE);
        }else{
            showInputView();
            addEditText( subQuestionResponse.get(0),
                    0, null);
        }
    }

    private void showNextQuestion(Integer nextQuestionId) {

        if (nextQuestionId == -1) {

            if (subQuestionInputIndex == subQuestionInputs.size() - 1) {
                allUsersSubQuestionAnswersResponse.add(subQuestionSequence);

                SubAnswersPostBaseResponse sapbs=new SubAnswersPostBaseResponse();
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
                subQuestionSequence=new HashMap<>();

                showFirstQuestion();
            }


        } else {

            mBtnPreviousQuestion.setEnabled(true);

            mGroupName.setText(subQuestionResponse.get(nextQuestionId).getG_name());

            String question = subQuestionResponse.get(nextQuestionId).getSub_question();
            question = question.replace("REPLACE", subQuestionInputs.get(subQuestionInputIndex));
            mQuestion.setText( question);

            String answer;
            try {
                answer = subQuestionSequence.get(nextQuestionId).getAnswer();
            } catch (Exception e) {
                answer = null;
            }

            String questionType = subQuestionResponse.get(nextQuestionId).getSub_type();

            if (questionType.equalsIgnoreCase("radio")) {
                showRadioView();
                addRadioButtons(
                        subQuestionResponse.get(nextQuestionId),
                        nextQuestionId, answer);

                mRadioGroupAnswers.setVisibility(View.VISIBLE);
            } else if (questionType.equalsIgnoreCase("Input") || questionType.equalsIgnoreCase("Input_validation")) {
                showInputView();
                addEditText(subQuestionResponse.get(nextQuestionId), nextQuestionId, answer);
            } else if (questionType.equalsIgnoreCase("Multiple_Input")) {
                showCheckBoxView();
            }

            mCurrentApr = subQuestionSequence.get(mCurrentQuestionIndex);
        }
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
                    answer);
        } else if (questionType.equalsIgnoreCase("Multiple_Input")) {

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


    private void addEditText(final SubQuestionObject qObject, final Integer currentQuestionId, String answer) {

        final List<OptionObject> options = qObject.getOptions();

        mEditTextslayout.removeAllViews();

        mValidation=qObject.getSub_validation();


        EditText et = new EditText(this); // this refers to the activity or the context.
        if (null != answer) {
            et.setText(answer);
        } else {
            et.setText("");
        }

        et.setMaxLines(1);
        et.setSingleLine();
        et.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et.setTextAppearance(this, R.style.BankDetailsText);
        et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        et.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentAnswer = s.toString();
                nextQuestionId = Integer.parseInt(options.get(0).getNq_id());
                mCurrentApr = new SubAnswersPostResponse();
                mCurrentApr.setQuestionId(currentQuestionId);
                mCurrentApr.setAnswer(currentAnswer);
                mCurrentApr.setNextQuestionId(nextQuestionId);
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditTextslayout.addView(et);


    }

    public void addRadioButtons(final SubQuestionObject qObject, final Integer currentQuestionId, String answer) {


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
            rdbtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);

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


    private void showInputView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.VISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
    }

    private void showRadioView() {
        mCheckboxsLayout.setVisibility(View.INVISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.VISIBLE);
    }

    private void showCheckBoxView() {
        mCheckboxsLayout.setVisibility(View.VISIBLE);
        mEditTextslayout.setVisibility(View.INVISIBLE);
        mRadioGroupAnswers.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("");
    }

}
