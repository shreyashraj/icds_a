package com.stayabode.features.login.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stayabode.R;
import com.stayabode.features.login.activities.QuestionsActivity;
import com.stayabode.features.login.activities.SubQuestionsActivity;
import com.stayabode.net.response.postmodels.ListGuideResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.IntentKeys;

/**
 * Created by VarunBhalla on 15/12/16.
 */
public class HomeVisitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String ISSUE_RATED_TRUE = "true";
    private final ArrayList<ListGuideResponse> mHomeVisitList;
    private Context mContext;
    private static final int STATIC_INTEGER_VALUE = 67;



    public HomeVisitAdapter(Context context, ArrayList<ListGuideResponse> homeVisitList) {
        mContext = context;
        mHomeVisitList=homeVisitList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View reportedIssuesView = inflater.inflate(R.layout.home_visit_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new IssuesViewHolder(reportedIssuesView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final IssuesViewHolder issuesViewHolder = (IssuesViewHolder) holder;

        issuesViewHolder.mSelectionText.setText(mHomeVisitList.get(position).getSelection());
        issuesViewHolder.mDescriptionText.setText(mHomeVisitList.get(position).getTitle());
        issuesViewHolder.cardViewListHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Test Click  *** " + issuesViewHolder.getAdapterPosition(),
                        Toast.LENGTH_SHORT).show();
                Log.i("TAG","GroupID is " +  mHomeVisitList.get(position).getGroupId());

                Intent i = new Intent(v.getContext(), QuestionsActivity.class);
              //  i.putParcelableArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONSRESP, (ArrayList<? extends Parcelable>) subQuestions);
                //i.putStringArrayListExtra(IntentKeys.INTENT_SUB_QUESTIONS_INPUTS, allanswers);
                //i.putExtra("mQValidation", mQValidation);
               // startActivityForResult(i, 3);
               v.getContext().startActivity(i);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mHomeVisitList.size();
    }

    public static class IssuesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_selection)
        TextView mSelectionText;

        @BindView(R.id.text_description)
        TextView mDescriptionText;

        @BindView(R.id.cardViewListHolder)
        RelativeLayout cardViewListHolder;


        public IssuesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mSelectionText = (TextView) itemView.findViewById(R.id.text_selection);
            mDescriptionText = (TextView) itemView.findViewById(R.id.text_description);
            cardViewListHolder = itemView.findViewById(R.id.cardViewListHolder);

        }
    }
}