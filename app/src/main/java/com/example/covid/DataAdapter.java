package com.example.covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHoler> {

    private   List<DataCovid> data;
    private Context context;

    public DataAdapter(List<DataCovid> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

        holder.txt_updated.setText("Cập Nhật: "+(data.get(position).updated));
        holder.txt_city.setText("Thành Phố: "+(data.get(position).city));
        holder.txt_cases.setText("Số ca nhiễm: "+(data.get(position).cases));
        holder.txt_beingTreated.setText("Nhiễm từ ca: "+(data.get(position).beingTreated));
        holder.txt_recovered.setText("Đã phục hồi: "+(data.get(position).recovered));
        holder.txt_deaths.setText("Tử vong: "+(data.get(position).deaths));

    }

    @Override
    public int getItemCount() { return data.size();
    }


    public class ViewHoler extends RecyclerView.ViewHolder {
       TextView txt_updated;
       TextView txt_city;
       TextView txt_cases;
       TextView txt_beingTreated;
       TextView txt_recovered;
       TextView txt_deaths;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txt_updated = (TextView)itemView.findViewById(R.id.txt_updated);
            txt_city = (TextView)itemView.findViewById(R.id.txt_city);
            txt_cases = (TextView)itemView.findViewById(R.id.txt_cases);
            txt_beingTreated = (TextView)itemView.findViewById(R.id.txt_beingTreated);
            txt_recovered = (TextView)itemView.findViewById(R.id.txt_recovered);
            txt_deaths = (TextView)itemView.findViewById(R.id.txt_deaths);

        }
    }

}
