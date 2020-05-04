package com.example.starsignpicker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StarSignPickerAdapter extends RecyclerView.Adapter<StarSignPickerAdapter.ViewHolder> {
    private String[] mStarSigns = {"Aries", "Taurus", "Gemini", "Cancer",
            "Leo", "Virgo", "Libra", "Scorpio",
            "Sagittarius", "Capricorn", "Aquarius",
            "Pisces"};

    public StarSignPickerAdapter() {
    }

    @Override
    public int getItemCount() {
        return mStarSigns == null ? 0 : mStarSigns.length;
    }

    @NonNull
    @Override
    public StarSignPickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create the new View
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new ViewHolder(v, null);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(mStarSigns[position]);

        holder.mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapterItemClickListener != null)
                    mAdapterItemClickListener.onItemClicked(mStarSigns[position]);
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public View.OnClickListener mListener;

        public ViewHolder(View v, View.OnClickListener listener) {
            super(v);
            mListener = listener;
            textView = v.findViewById(R.id.itemTextView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null)
                mListener.onClick(v);
        }
    }

    public interface IAdapterItemClick {
        void onItemClicked(String selectedItem);
    }

    IAdapterItemClick mAdapterItemClickListener;

    public void setOnAdapterItemClick(
            IAdapterItemClick adapterItemClickHandler) {
        mAdapterItemClickListener = adapterItemClickHandler;
    }
}
