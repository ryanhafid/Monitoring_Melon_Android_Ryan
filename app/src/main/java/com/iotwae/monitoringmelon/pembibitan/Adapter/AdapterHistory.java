package com.iotwae.monitoringmelon.pembibitan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iotwae.monitoringmelon.R;
import com.iotwae.monitoringmelon.pembibitan.Model.HistoryModel;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HolderData> {

    private final Context ctx;
    private final List<HistoryModel> listMelon;

    public AdapterHistory(Context ctx, List<HistoryModel> listMelon) {
        this.ctx = ctx;
        this.listMelon = listMelon;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_histori_pembibitann, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        HistoryModel hm = listMelon.get(position); //set data misl data 1 utk card 1 dst dan setnya dari holder spt dibwh ini

        holder.tvId.setText(": " + hm.getId());
        holder.tvWaktu.setText(": " +hm.getWaktu());
        holder.tvTempUdr.setText(": " +hm.getTemp_udr());
        holder.tvHumUdr.setText(": " +hm.getHum_udr());
        holder.tvTempTnh.setText(": " +hm.getTemp_tnh());
        holder.tvHumTnh.setText(": " +hm.getHum_tnh());
        holder.tvCahaya.setText(": " +hm.getCahaya());

    }

    @Override
    public int getItemCount() {
        return listMelon.size();
    }


    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvWaktu, tvTempUdr, tvHumUdr, tvTempTnh, tvHumTnh, tvCahaya;

        public HolderData(@NonNull View itemView) { //findviewkan tv suhu, tvkadargr dll ke posisi cardview
            super(itemView);

            tvId = itemView.findViewById(R.id.id);
            tvWaktu = itemView.findViewById(R.id.date);
            tvTempUdr = itemView.findViewById(R.id.temp_udr);
            tvHumUdr = itemView.findViewById(R.id.hum_udr);
            tvTempTnh = itemView.findViewById(R.id.temp_tnh);
            tvHumTnh = itemView.findViewById(R.id.hum_tnh);
            tvCahaya = itemView.findViewById(R.id.cahaya);
        }
    }
}
