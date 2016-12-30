package com.getninjas.test.jeffersonalvess.getninjastest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

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
        List<Offer> offers;

        public ViewHolder(View itemView, Context context, List<Offer> offers) {
            super(itemView);

            txtCardTitle = (TextView) itemView.findViewById(R.id.txtCardTitle);
            txtCardUser = (TextView) itemView.findViewById(R.id.txtCardUser);
            txtCardDate = (TextView) itemView.findViewById(R.id.txtCardDate);
            txtCardLocation = (TextView) itemView.findViewById(R.id.txtCardLocation);

            imgViewPerson = (ImageView) itemView.findViewById(R.id.imageViewPerson);
            imgViewCalendar = (ImageView) itemView.findViewById(R.id.imageViewCalendar);
            imgViewLocation = (ImageView) itemView.findViewById(R.id.imageViewLocation);

            this.context = context;
            this.offers = offers;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context, DetailsActivity.class);

                intent.putExtra("from", "Offer");
                intent.putExtra("link", offers.get(position).getSelfLink());

                context.startActivity(intent);
            }
        }
    }


    private List<Offer> mOffers;
    private Context mContext;


    public OffersAdapter(Context context, List<Offer> offers) {
        mOffers = offers;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }


    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View offerView = inflater.inflate(R.layout.list_item_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(offerView, context, mOffers);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OffersAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Offer offer = mOffers.get(position);

        // Set item views based on your views and data model
        TextView txtCardTitle = holder.txtCardTitle;
        TextView txtCardUser = holder.txtCardUser;
        TextView txtCardDate = holder.txtCardDate;
        TextView txtCardLocation = holder.txtCardLocation;

        ImageView imgViewPerson = holder.imgViewPerson;
        ImageView imgViewCalendar = holder.imgViewCalendar;
        ImageView imgViewLocation = holder.imgViewLocation;

        try {
            txtCardTitle.setText(offer.getTitle());
            txtCardUser.setText(offer.getUserName());
            txtCardDate.setText(FormatDate(offer.getCreatedAt()));
            txtCardLocation.setText(offer.getNeighborhood() + " - " + offer.getCity());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


        if (offer.getState().equals("read"))
        {
            int primaryColor = Color.parseColor("#0091ea");
            imgViewPerson.setColorFilter(primaryColor);
            imgViewCalendar.setColorFilter(primaryColor);
            imgViewLocation.setColorFilter(primaryColor);
        }

    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    private String FormatDate (String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date newDate = df.parse(date.substring(0,10));

        Format formatter = new SimpleDateFormat("dd-MMM");
        String formattedDate = formatter.format(newDate).replace("-", " de ");

        return formattedDate;
    }
}
