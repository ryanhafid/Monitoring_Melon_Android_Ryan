package com.iotwae.monitoringmelon.pembibitan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iotwae.monitoringmelon.R;
import com.iotwae.monitoringmelon.pembibitan.Model.DataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private final Context ctx;
    private final List<DataModel> listMelon;

    public AdapterData(Context ctx, List<DataModel> listMelon) {
        this.ctx = ctx;
        this.listMelon = listMelon;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_pembibitan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listMelon.get(position);                                                                                 //set data misl data 1 utk card 1 dst dan setnya dari holder spt dibwh ini
        holder.tvnama.setText(dm.getNama());
        holder.tvvalue.setText(dm.getValue());

        Picasso.get()         //url img
                .load(dm.getImage())
                .resize(50, 50)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listMelon.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvnama, tvvalue, tvdatetime;
        ImageView image;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvnama = itemView.findViewById(R.id.nama);
            tvvalue = itemView.findViewById(R.id.value);
            image = itemView.findViewById(R.id.image);

        }
    }

}