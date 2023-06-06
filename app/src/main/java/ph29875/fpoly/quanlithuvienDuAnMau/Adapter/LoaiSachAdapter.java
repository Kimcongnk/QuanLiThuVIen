package ph29875.fpoly.quanlithuvienDuAnMau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Model.LoaiSach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {
    private List<LoaiSach> loaiSachList;
    private Context context;

    public LoaiSachAdapter(Context context, List<LoaiSach> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loai_sach, parent, false);
        return new LoaiSachViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, int position) {
        LoaiSach loaiSach = loaiSachList.get(position);
        holder.tenLoaiTextView.setText("Loại Sách: "+ loaiSach.getTenLoai());
    }

    @Override
    public int getItemCount() {
        return loaiSachList.size();
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        public TextView tenLoaiTextView;

        public LoaiSachViewHolder(View itemView) {
            super(itemView);
            tenLoaiTextView = itemView.findViewById(R.id.tenLoaiTextView);
        }
    }
}
