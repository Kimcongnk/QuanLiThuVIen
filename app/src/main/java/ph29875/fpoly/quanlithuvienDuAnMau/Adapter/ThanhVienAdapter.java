package ph29875.fpoly.quanlithuvienDuAnMau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThanhVien;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {
    private Context context;
    private List<ThanhVien> thanhVienList;
    private ImageViewClickListener imageViewClickListener;
    public void setImageViewClickListener(ThanhVienAdapter.ImageViewClickListener listener) {
        this.imageViewClickListener = listener;
    }
    public interface ImageViewClickListener {
        void onImageViewClick(int position);
        void xoa(int position);
    }
    public ThanhVienAdapter(Context context, List<ThanhVien> thanhVienList) {
        this.context = context;
        this.thanhVienList = thanhVienList;
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thanhvien, parent, false);
        return new ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        ThanhVien thanhVien = thanhVienList.get(position);
        holder.txtHoTen.setText("Họ tên: "+thanhVien.getHoTen());
        holder.txtNamSinh.setText("Năm sinh: " +String.valueOf(thanhVien.getNamSinh()));
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
        return thanhVienList.size();
    }

    public class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        TextView txtHoTen;
        TextView txtNamSinh;
        ImageView sua, xoa;


        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHoTen = itemView.findViewById(R.id.textView3);
            txtNamSinh = itemView.findViewById(R.id.textView4);
            sua = itemView.findViewById(R.id.imageView4);
            xoa = itemView.findViewById(R.id.imageView5);
        }
    }
}
