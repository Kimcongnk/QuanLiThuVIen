package ph29875.fpoly.quanlithuvienDuAnMau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private List<Sach> sachList;
private Context context;

    public SachAdapter(List<Sach> sachList, Context context) {
        this.sachList = sachList;
        this.context = context;
    }


    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        return new SachViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = sachList.get(position);
        holder.tenSachTextView.setText(sach.getTenSach());
        holder.giaThueTextView.setText(String.valueOf(sach.getGiaThue()));
        holder.maLoaiTextView.setText(String.valueOf(sach.getMaLoai()));
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }
    public class SachViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSachTextView;
        public TextView giaThueTextView;
        public TextView maLoaiTextView;

        public SachViewHolder(View itemView) {
            super(itemView);
            tenSachTextView = itemView.findViewById(R.id.tenSachTextView);
            giaThueTextView = itemView.findViewById(R.id.giaThueTextView);
            maLoaiTextView = itemView.findViewById(R.id.maLoaiTextView);
        }
    }

}
