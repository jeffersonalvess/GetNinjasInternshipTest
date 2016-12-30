package com.getninjas.test.jeffersonalvess.getninjastest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jeffersonalvess on 12/29/16.
 */

public class InfosAdapter extends RecyclerView.Adapter<InfosAdapter.ViewHolder> {

    //  RecyclerView requires a view holder which represents each line of the list.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDetailsInfoLabel;
        public TextView txtDetailsInfoValue;
        public ImageView imgDetailsInfoIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDetailsInfoLabel = (TextView) itemView.findViewById(R.id.txtDetailsInfoLabel);
            txtDetailsInfoValue = (TextView) itemView.findViewById(R.id.txtDetailsInfoValue);
            imgDetailsInfoIcon = (ImageView) itemView.findViewById(R.id.imgDetailsInfoIcon);

        }

    }

    private List<Info> mInfos;
    private Context mContext;
    private String mFrom;


    public InfosAdapter(Context context, List<Info> infos, String from) {
        mFrom = from;
        mInfos = infos;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }


    @Override
    public InfosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View leadView = inflater.inflate(R.layout.list_item_details, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(leadView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfosAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Info info = mInfos.get(position);

        // Set item views based on your views and data model
        TextView txtDetailsInfoLabel = holder.txtDetailsInfoLabel;
        TextView txtDetailsInfoValue = holder.txtDetailsInfoValue;
        ImageView imgDetailsInfoIcon = holder.imgDetailsInfoIcon;

        txtDetailsInfoLabel.setText(info.getLabel());
        txtDetailsInfoValue.setText(info.getValue());

        if (mFrom.equals("Offer")) {
            imgDetailsInfoIcon.setColorFilter(Color.parseColor("#0091ea"));
        }
        else {
            imgDetailsInfoIcon.setColorFilter(Color.parseColor("#90e135"));
        }
    }

    @Override
    public int getItemCount() {
        return mInfos.size();
    }


}
