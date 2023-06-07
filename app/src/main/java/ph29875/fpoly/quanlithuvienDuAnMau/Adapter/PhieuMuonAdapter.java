package ph29875.fpoly.quanlithuvienDuAnMau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Dao.PhieuMuonDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.PhieuMuon;

import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhieuMuon> phieuMuonList;
    private PhieuMuonDao phieuMuonDao;
    private ImageViewClickListener imageViewClickListener;
    public void setImageViewClickListener(ImageViewClickListener listener) {
        this.imageViewClickListener = listener;
    }
    public interface ImageViewClickListener {
        void onImageViewClick(int position);
        void xoa(int position);
    }

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> phieuMuonList) {
        this.context = context;
        this.phieuMuonList = phieuMuonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phieumuon, parent, false);
        phieuMuonDao = new PhieuMuonDao(view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuMuon phieuMuon = phieuMuonList.get(position);
        holder.tvMaTV.setText("Mã thành viên: " + phieuMuon.getMaTV());
        holder.tvMaSach.setText("Mã sách: " + phieuMuon.getMaSach());
        holder.tvTienThue.setText("Tiền thuê: " + phieuMuon.getTienThue());
        holder.tvNgay.setText("Ngày: " + phieuMuon.getNgay());
        holder.tvTraSach.setText("Trả sách: " + phieuMuon.getTraSach());

        // Xử lý sự kiện click vào một mục
        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageViewClickListener != null) {
                    imageViewClickListener.onImageViewClick(position);
                }
            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageViewClickListener != null) {
                    imageViewClickListener.xoa(position);
                }
            }
        });
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
        ImageView sua, xoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.tvMaTV);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTraSach = itemView.findViewById(R.id.tvTraSach);
            sua = itemView.findViewById(R.id.imageView8);
            xoa = itemView.findViewById(R.id.imageView9);
        }
    }

}

