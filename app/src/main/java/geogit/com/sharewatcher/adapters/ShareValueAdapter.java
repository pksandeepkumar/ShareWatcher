package geogit.com.sharewatcher.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import geogit.com.sharewatcher.R;
import geogit.com.sharewatcher.datamodels.ShareData;

/**
 * Created by sandeep on 25/2/16.
 */
public class ShareValueAdapter extends RecyclerView.Adapter<ShareValueAdapter.ViewHolder> {

    private ArrayList<ShareData> mDataset;
    private Context mContext;

    public ShareValueAdapter(Context context, ArrayList<ShareData> datasets) {
        mDataset = datasets;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvShareName;
        public TextView tvRsString;
        public TextView tvPrice;
        public TextView tvPercentage;
        public TextView tvPercentageString;

        public ViewHolder(View v) {
            super(v);
            tvShareName         = (TextView)    v.findViewById(R.id.tvShareName);
            tvRsString          = (TextView)    v.findViewById(R.id.tvRsString);
            tvPrice             = (TextView)    v.findViewById(R.id.tvPrice);
            tvPercentage        = (TextView)    v.findViewById(R.id.tvPercentage);
            tvPercentageString  = (TextView)    v.findViewById(R.id.tvPercentageString);
        }
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ShareValueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_element_share_value, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ShareData shareData = mDataset.get(position);
        holder.tvShareName.setText( shareData.mShareName );
        holder.tvPercentage.setText(formatValue(shareData.mCurrentPercentage));
        holder.tvPrice.setText(formatValue(shareData.mCurrentPrice));

        if(shareData.mCurrentPercentage >= shareData.mPreviousPercentage) {
            holder.tvPercentage.setTextColor(mContext.getResources().getColor(R.color.color_green));
            holder.tvPercentageString.setTextColor(mContext.getResources()
                    .getColor(R.color.color_green));
        } else {
            holder.tvPercentage.setTextColor(mContext.getResources().getColor(R.color.color_red));
            holder.tvPercentageString.setTextColor(mContext.getResources()
                    .getColor(R.color.color_red));
        }

        if(shareData.mCurrentPrice >= shareData.mPreviousPrice) {
            holder.tvPrice.setTextColor(mContext.getResources().getColor(R.color.color_green));
            holder.tvRsString.setTextColor(mContext.getResources().getColor(R.color.color_green));
        } else {
            holder.tvPrice.setTextColor(mContext.getResources().getColor(R.color.color_red));
            holder.tvRsString.setTextColor(mContext.getResources().getColor(R.color.color_red));
        }
    }


    private String formatValue( double value ) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        return decimalFormat.format(value);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
