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

import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private List<Sach> sachList;
private Context context;

    private PhieuMuonAdapter.ImageViewClickListener imageViewClickListener;
    public void setImageViewClickListener(PhieuMuonAdapter.ImageViewClickListener listener) {
        this.imageViewClickListener = listener;
    }
    public interface ImageViewClickListener {
        void onImageViewClick(int position);
        void xoa(int position);
    }


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
        holder.tenSachTextView.setText("Tên sách: "+sach.getTenSach());
        holder.giaThueTextView.setText("Giá thuê: " +String.valueOf(sach.getGiaThue()));
        holder.maLoaiTextView.setText("Mã loại: "+String.valueOf(sach.getMaLoai()));
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
        return sachList.size();
    }
    public class SachViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSachTextView;
        public TextView giaThueTextView;
        public TextView maLoaiTextView;
        ImageView sua, xoa;

        public SachViewHolder(View itemView) {
            super(itemView);
            tenSachTextView = itemView.findViewById(R.id.textView2);
            giaThueTextView = itemView.findViewById(R.id.textView8);
            maLoaiTextView = itemView.findViewById(R.id.textView9);
            sua = itemView.findViewById(R.id.imageView6);
            xoa = itemView.findViewById(R.id.imageView7);
        }
    }

}
