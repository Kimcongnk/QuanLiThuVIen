package ph29875.fpoly.quanlithuvienDuAnMau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;


public class AdapterTopSach extends RecyclerView.Adapter<AdapterTopSach.ViewHolder> {
    Context context;
    ArrayList<Sach> listtop;

    public AdapterTopSach(Context context, ArrayList<Sach> listtop) {
        this.context = context;
        this.listtop = listtop;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensach,soluongsach;

        RelativeLayout topsach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tensach = itemView.findViewById(R.id.itemtop_tensach);
           soluongsach = itemView.findViewById(R.id.itemtop_soluong);
           topsach = itemView.findViewById(R.id.itemtop);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sach_muon_nhieu_nhat,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tensach.setText(listtop.get(position).getTenSach());
        holder.soluongsach.setText(String.valueOf(listtop.get(position).getSoLuong()));

    }

    @Override
    public int getItemCount() {
        return listtop.size();
    }


}
