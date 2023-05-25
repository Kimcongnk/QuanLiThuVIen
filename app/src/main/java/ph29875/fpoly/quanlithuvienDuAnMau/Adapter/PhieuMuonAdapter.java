package ph29875.fpoly.quanlithuvienDuAnMau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Model.PhieuMuon;

import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhieuMuon> phieuMuonList;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> phieuMuonList) {
        this.context = context;
        this.phieuMuonList = phieuMuonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuMuon phieuMuon = phieuMuonList.get(position);
        holder.tvMaTV.setText("MaTV: " + phieuMuon.getMaTV());
        holder.tvMaSach.setText("MaSach: " + phieuMuon.getMaSach());
        holder.tvTienThue.setText("TienThue: " + phieuMuon.getTienThue());
        holder.tvNgay.setText("Ngay: " + phieuMuon.getNgay());
        holder.tvTraSach.setText("TraSach: " + phieuMuon.getTraSach());
    }

    @Override
    public int getItemCount() {
        return phieuMuonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTV;
        TextView tvMaSach;
        TextView tvTienThue;
        TextView tvNgay;
        TextView tvTraSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.tvMaTV);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTraSach = itemView.findViewById(R.id.tvTraSach);
        }
    }
}

