package com.stayabode.features.login.activities;

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
import android.widget.Toast;

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
import com.stayabode.net.response.postmodels.SubAnswersPostBaseResponse;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
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
import utils.Validations;
import utils.ViewUtils;

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

    HashMap<Integer, String> mListGuideMapSubmissions = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        setActionBar();
        disableBackArrow();

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

//                    String previousAnswer;
//                    try {
//                        AnswersPostResponse previousAnswerResp = questionSequence.get(mCurrentQuestionIndex - 1);
//                        previousAnswer = previousAnswerResp.getAnswer();
//                    } catch (Exception e) {
//                        previousAnswer = "";
//                    }
//
//
//                    try {
//                        mValidation = questionSequence.get(mCurrentQuestionIndex).getValidation();
//                    } catch (Exception e) {
//                        mValidation = "";
//                    }
//
//
//                    if (mValidation.equalsIgnoreCase(Validations.VALIDATION_GT) &&
//                            Integer.parseInt(mCurrentApr.getAnswer()) > Integer.parseInt(previousAnswer)) {
//                        ViewUtils.showSnackBar(QuestionsActivity.this, mCoordinatorLayout, "Value should be greater than " + previousAnswer);
//                    } else if (mValidation.equalsIgnoreCase(Validations.VALIDATION_LT) &&
//                            Integer.parseInt(mCurrentApr.getAnswer()) < Integer.parseInt(previousAnswer)) {
//                        ViewUtils.showSnackBar(QuestionsActivity.this, mCoordinatorLayout, "Value should be less than " + previousAnswer);
//                    } else if (mValidation.equalsIgnoreCase(Validations.VALIDATION_GT20) &&
//                            Integer.parseInt(mCurrentApr.getAnswer()) > 20) {
//                        ViewUtils.showSnackBar(QuestionsActivity.this, mCoordinatorLayout, "Value should be greater than 20");
//                    } else if (mValidation.equalsIgnoreCase(Validations.VALIDATION_LT10) &&
//                            Integer.parseInt(mCurrentApr.getAnswer()) < 10) {
//                        ViewUtils.showSnackBar(QuestionsActivity.this, mCoordinatorLayout, "Value should be less than 10");
//                    } else {
//                        questionSequence.put(mCurrentQuestionIndex, mCurrentApr);
//                        mCurrentQuestionIndex++;
//                        showNextQuestion();
//                    }


//                    if(mCurrentApr.getAnswer()==null &&
//                            (mCurrentApr.getCheckboxAnswers()==null || mCurrentApr.getCheckboxAnswers().size()==0 )&&
//                            (mCurrentApr.getMultipleInputAnswers()==null || mCurrentApr.getMultipleInputAnswers().size()==0) &&
//                            null !=  mCurrentApr.getIsMandatory()
//                            mCurrentApr.getIsMandatory().equalsIgnoreCase("Yes")){
//
//                    }else{
//                        questionSequence.put(mCurrentQuestionIndex, mCurrentApr);
//                        mCurrentQuestionIndex++;
//                        showNextQuestion();
//                    }



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
                ArrayList<String> allanswers = multipleAnswersAdapter.getMultipleAnswersArray();
                startSubQuestionActivity(questionResponse.getData().get(mCurrentGroup).getData().get(mCurrentApr.getQuestionId()).getSubQuestions(), allanswers);
            }
        });
    }

    private void showAddAnswerDialog() {

        String message = Constants.REOPEN_ISSUE_DIALOG_LABEL;
        String positiveButtonText = Constants.ADD_ANSWER_POSITIVE_BUTTON;

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);


        new AlertDialog.Builder(this)
                .setMessage("")
                .setView(input)
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

    private void showNextQuestion() {

        nextQuestionId = mCurrentApr.getNextQuestionId();

        mCurrentApr = new AnswersPostResponse();

        if (nextQuestionId == -1) {

            showGenericErrorView();


        } else {

            mGroupName.setText(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getG_name());
            mQuestion.setText(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getQ_name());


            String answer;
            try {
                answer = questionSequence.get(nextQuestionId).getAnswer();
            } catch (Exception e) {
                answer = null;
            }

            String questionType = questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId).getQ_type();

            if (questionType.equalsIgnoreCase("radio")) {
                showRadioView();
                addRadioButtons(
                        questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId),
                        nextQuestionId, answer);

                mRadioGroupAnswers.setVisibility(View.VISIBLE);
            } else if (questionType.equalsIgnoreCase("Input") || questionType.equalsIgnoreCase("Input_validation")) {
                showInputView();

                addEditText(questionResponse.getData().get(mCurrentGroup).getData().get(nextQuestionId),
                        nextQuestionId, answer,questionType);

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
    }


    private void startSubQuestionActivity(List<SubQuestionObject> subQuestions, ArrayList<String> allanswers) {
        Intent i = new Intent(QuestionsActivity.this, SubQuestionsActivity.class);
        i.putParcelableArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONSRESP, (ArrayList<? extends Parcelable>) subQuestions);
        i.putStringArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONS_INPUTS, allanswers);
        startActivityForResult(i, 3);
    }

    private void showPreviousQuestion(AnswersPostResponse mTempCurrentApr) {
        mGroupName.setText(questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()).getG_name());
        mQuestion.setText(questionResponse.getData().get(mCurrentGroup).getData().get(questionSequence.get(mCurrentQuestionIndex).getQuestionId()).getQ_name());

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
                    answer,questionType);
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


        multipleAnswersAdapter = new MultipleAnswersAdapter(this, multipleAnswersArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMultipleTextslayout.setLayoutManager(linearLayoutManager);
        mMultipleTextslayout.setAdapter(multipleAnswersAdapter);


        nextQuestionId = Integer.parseInt(options.get(0).getNq_id());

        mCurrentAprMultiAnswers = new AnswersPostResponse();

        mCurrentAprMultiAnswers.setQuestionId(currentQuestionId);
        mCurrentAprMultiAnswers.setNextQuestionId(nextQuestionId);
        mCurrentAprMultiAnswers.setIsMandatory(qObject.getQ_mandatory());
        mCurrentAprMultiAnswers.setMultipleInputAnswers(multipleAnswersArray);


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
                cb.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);

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
                        mCurrentApr.setIsMandatory(qObject.getQ_mandatory());
                        mCurrentApr.setNextQuestionId(nextQuestionId);
                        mCurrentApr.setCheckboxAnswers(chkBoxAnswers);

                    }
                });


                mCheckboxsLayout.addView(cb);
            }
        }

        changePreviousButtonState(mCurrentQuestionIndex);

    }


    private void addEditText(final QuestionObject qObject, final Integer currentQuestionId, String answer,String qType) {

        final List<OptionObject> options = qObject.getOptions();

        mEditTextslayout.removeAllViews();


        EditText et = new EditText(this); // this refers to the activity or the context.
        if (null != answer) {
            et.setText(answer);
        } else {
            et.setText("");
        }

        if(qType.equalsIgnoreCase("Input_No") || qType.equalsIgnoreCase("Input_Validation")) {
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        et.setMaxLines(1);
        et.setSingleLine();
        et.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        et.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et.setTextAppearance(this, R.style.BankDetailsText);
        et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentAnswer = s.toString();
                nextQuestionId = Integer.parseInt(options.get(0).getNq_id());

                mCurrentApr = new AnswersPostResponse();
                mCurrentApr.setQuestionId(currentQuestionId);
                mCurrentApr.setAnswer(currentAnswer);
                mCurrentApr.setIsMandatory(qObject.getQ_mandatory());
                mCurrentApr.setNextQuestionId(nextQuestionId);
                mCurrentApr.setValidation(qObject.getQ_validation());
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

                mCurrentApr = new AnswersPostResponse();
                mCurrentApr.setQuestionId(currentQuestionId);
                mCurrentApr.setAnswer(currentAnswer);
                mCurrentApr.setNextQuestionId(nextQuestionId);


                //  Toast.makeText(getBaseContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
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

                mListGuideMapSubmissions.put(mCurrentApr.getQuestionId(), mCurrentApr.getMultipleInputAnswers().get(index));
            }catch (Exception e){

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
        if(mListGuideMapSubmissions != null && !mListGuideMapSubmissions.isEmpty()){
            for (Map.Entry <Integer, String> entry : mListGuideMapSubmissions.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                Timber.i("Response:: Key: %s, Value: %s", key, value);

            }
        }else{
            Timber.i("Response: Failure::" + "Empty Map mListGuideMapSubmissions");
        }
        Intent i = new Intent(QuestionsActivity.this, HomeVisitActivity.class);
        i.putExtra("listGuideMapSubmissions", mListGuideMapSubmissions);
        startActivity(i);
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("");
    }

}
