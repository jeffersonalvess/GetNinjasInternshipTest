package com.getninjas.test.jeffersonalvess.getninjastest;

import android.content.Context;
import android.content.Intent;
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

public class LeadsAdapter extends RecyclerView.Adapter<LeadsAdapter.ViewHolder> {

    //  RecyclerView requires a view holder which represents each line of the list.
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtCardTitle;
        public TextView txtCardUser;
        public TextView txtCardDate;
        public TextView txtCardLocation;

        public ImageView imgViewPerson;
        public ImageView imgViewCalendar;
        public ImageView imgViewLocation;

        Context context;
        List<Lead> leads;

        public ViewHolder(View itemView, Context context, List<Lead> leads) {
            super(itemView);

            txtCardTitle = (TextView) itemView.findViewById(R.id.txtCardTitle);
            txtCardUser = (TextView) itemView.findViewById(R.id.txtCardUser);
            txtCardDate = (TextView) itemView.findViewById(R.id.txtCardDate);
            txtCardLocation = (TextView) itemView.findViewById(R.id.txtCardLocation);

            imgViewPerson = (ImageView) itemView.findViewById(R.id.imageViewPerson);
            imgViewCalendar = (ImageView) itemView.findViewById(R.id.imageViewCalendar);
            imgViewLocation = (ImageView) itemView.findViewById(R.id.imageViewLocation);

            this.context = context;
            this.leads = leads;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context, DetailsActivity.class);

                intent.putExtra("from", "Lead");
                intent.putExtra("link", leads.get(position).getSelfLink());

                context.startActivity(intent);
            }
        }
    }


    private List<Lead> mLeads;
    private Context mContext;


    public LeadsAdapter(Context context, List<Lead> leads) {
        mLeads = leads;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }


    @Override
    public LeadsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View leadView = inflater.inflate(R.layout.list_item_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(leadView, context, mLeads);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeadsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Lead lead = mLeads.get(position);

        // Set item views based on your views and data model
        TextView txtCardTitle = holder.txtCardTitle;
        TextView txtCardUser = holder.txtCardUser;
        TextView txtCardDate = holder.txtCardDate;
        TextView txtCardLocation = holder.txtCardLocation;

        ImageView imgViewPerson = holder.imgViewPerson;
        ImageView imgViewCalendar = holder.imgViewCalendar;
        ImageView imgViewLocation = holder.imgViewLocation;

        try {
            txtCardTitle.setText(lead.getTitle());
            txtCardUser.setText(lead.getName());
            txtCardDate.setText(FormatDate(lead.getCreatedAt()));
            txtCardLocation.setText(lead.getNeighborhood() + " - " + lead.getCity());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


        int primaryColor = Color.parseColor("#90e135");
        imgViewPerson.setColorFilter(primaryColor);
        imgViewCalendar.setColorFilter(primaryColor);
        imgViewLocation.setColorFilter(primaryColor);
    }

    @Override
    public int getItemCount() {
        return mLeads.size();
    }

    private String FormatDate (String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date newDate = df.parse(date.substring(0,10));

        Format formatter = new SimpleDateFormat("dd-MMM");
        String formattedDate = formatter.format(newDate).replace("-", " de ");

        return formattedDate;
    }
}
