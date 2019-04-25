package com.stayabode.features.login.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stayabode.R;
import com.stayabode.features.login.activities.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import utils.Constants;
import utils.IntentKeys;

/**
 * Created by VarunBhalla on 15/12/16.
 */
public class MultipleAnswersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String ISSUE_RATED_TRUE = "true";
    private final ArrayList<String> mMultipleAnswersArray;
    private Context mContext;
    private static final int STATIC_INTEGER_VALUE = 67;



    public MultipleAnswersAdapter(Context context, ArrayList<String> multipleAnswersArray) {
        mContext = context;
        mMultipleAnswersArray=multipleAnswersArray;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View reportedIssuesView = inflater.inflate(R.layout.multiple_answers_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new IssuesViewHolder(reportedIssuesView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final IssuesViewHolder issuesViewHolder = (IssuesViewHolder) holder;


        issuesViewHolder.mDescriptionText.setText(mMultipleAnswersArray.get(position));
        issuesViewHolder.mDeleteAnswer.setTag(position);


        issuesViewHolder.mDeleteAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedTag = (int) issuesViewHolder.mDeleteAnswer.getTag();
                deleteItem(clickedTag);

            }
        });



    }

    private void showChangeStatusDialog(final String issueStatus, final String issueId, final int tag) {

        String message, positiveButtonText;
        if (issueStatus.equals(mContext.getResources().getString(R.string.issue_cancel))) {
            message = Constants.CANCEL_ISSUE_DIALOG_LABEL;
            positiveButtonText = Constants.CANCEL_ISSUE_POSITIVE_BUTTON;
        } else {
            message = Constants.REOPEN_ISSUE_DIALOG_LABEL;
            positiveButtonText = Constants.ADD_ANSWER_POSITIVE_BUTTON;
        }
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(Constants.DIALOG_NEGATIVE_BUTTON_TEXT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    @Override
    public int getItemCount() {
        return mMultipleAnswersArray.size();
    }

    public static class IssuesViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.text_description)
        TextView mDescriptionText;

        @BindView(R.id.btn_delete)
        CircleImageView mDeleteAnswer;

        @BindView(R.id.img_status)
        ImageView mChangeStatusImage;


        public IssuesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mChangeStatusImage=(ImageView)itemView.findViewById(R.id.img_status);
            mDescriptionText = (TextView) itemView.findViewById(R.id.text_description);
            mDeleteAnswer = (CircleImageView) itemView.findViewById(R.id.btn_delete);
        }
    }


    private void deleteItem(int position) {
        mMultipleAnswersArray.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mMultipleAnswersArray.size());
    }

    public ArrayList<String> getMultipleAnswersArray() {
       return mMultipleAnswersArray;
    }


}