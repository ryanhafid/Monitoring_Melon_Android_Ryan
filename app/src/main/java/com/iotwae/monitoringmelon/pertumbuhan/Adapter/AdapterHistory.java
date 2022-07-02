package com.iotwae.monitoringmelon.pertumbuhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iotwae.monitoringmelon.R;
import com.iotwae.monitoringmelon.pertumbuhan.Model.HistoryModel;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<com.iotwae.monitoringmelon.pertumbuhan.Adapter.AdapterHistory.HolderData> {

    private final Context ctx;
    private final List<HistoryModel> listMelon;

    public AdapterHistory(Context ctx, List<HistoryModel> listMelon) {
        this.ctx = ctx;
        this.listMelon = listMelon;
    }

    @NonNull
    @Override
    public com.iotwae.monitoringmelon.pertumbuhan.Adapter.AdapterHistory.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_histori_pertumbuhan, parent, false);
        com.iotwae.monitoringmelon.pertumbuhan.Adapter.AdapterHistory.HolderData holder = new com.iotwae.monitoringmelon.pertumbuhan.Adapter.AdapterHistory.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.iotwae.monitoringmelon.pertumbuhan.Adapter.AdapterHistory.HolderData holder, int position) {
        HistoryModel hm = listMelon.get(position); //set data misl data 1 utk card 1 dst dan setnya dari holder spt dibwh ini

        holder.tvId.setText(": " + hm.getId());
        holder.tvWaktu.setText(": " +hm.getWaktu());
        holder.tvTempUdr.setText(": " +hm.getTemp_udr());
        holder.tvHumUdr.setText(": " +hm.getHum_udr());
        holder.tvTempTnh.setText(": " +hm.getTemp_tnh());
        holder.tvHumTnh.setText(": " +hm.getHum_tnh());
        holder.tvPh.setText(": " +hm.getPh());
        holder.tvCahaya.setText(": " +hm.getCahaya());

    }

    @Override
    public int getItemCount() {
        return listMelon.size();
    }


    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvWaktu, tvTempUdr, tvHumUdr, tvTempTnh, tvHumTnh, tvPh, tvCahaya;

        public HolderData(@NonNull View itemView) { //findviewkan tv suhu, tvkadargr dll ke posisi cardview
            super(itemView);

            tvId = itemView.findViewById(R.id.id);
            tvWaktu = itemView.findViewById(R.id.date);
            tvTempUdr = itemView.findViewById(R.id.temp_udr);
            tvHumUdr = itemView.findViewById(R.id.hum_udr);
            tvTempTnh = itemView.findViewById(R.id.temp_tnh);
            tvHumTnh = itemView.findViewById(R.id.hum_tnh);
            tvPh = itemView.findViewById(R.id.ph);
            tvCahaya = itemView.findViewById(R.id.cahaya);
        }
    }
}

