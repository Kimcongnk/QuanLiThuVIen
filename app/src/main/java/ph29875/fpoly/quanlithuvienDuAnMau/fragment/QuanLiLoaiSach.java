package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.LoaiSachAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.SachAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.LoaiSachDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.LoaiSach;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiLoaiSach#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiLoaiSach extends Fragment {
private LoaiSachDao loaiSachDao ;
private ArrayList<LoaiSach> loaiSachArrayList = new ArrayList<>();
private LoaiSachAdapter loaiSachAdapter;
private RecyclerView recyclerView;

    public QuanLiLoaiSach() {
        // Required empty public constructor
    }

    public static QuanLiLoaiSach newInstance() {
QuanLiLoaiSach quanLiPhieuMuon = new QuanLiLoaiSach();

        return quanLiPhieuMuon;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loaiSachArrayList = (ArrayList<LoaiSach>) loaiSachDao.getAllLoaiSach();
        // Create and set the adapter
        loaiSachAdapter = new LoaiSachAdapter(getContext(), loaiSachArrayList);
        recyclerView.setAdapter(loaiSachAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quan_li_loai_sach, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
         recyclerView = view.findViewById(R.id.recyclerView);
        loaiSachDao = new LoaiSachDao(getContext());


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLoaiSachDialog();
            }
        });
        return view;
    }
    private void showAddLoaiSachDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_loai_sach, null);
        builder.setView(dialogView);

        EditText tenLoaiEditText = dialogView.findViewById(R.id.tenLoaiEditText);

        builder.setTitle("Thêm Loại Sách")
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiSach loaiSach = new LoaiSach();
                       loaiSach.setTenLoai(tenLoaiEditText.getText().toString());

                        if (loaiSachDao.addLoaiSach(loaiSach) > 0) {
                            onResume();
                            // Thêm thành công
                            Toast.makeText(getContext(), "Thêm Loại Sách thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            // Thêm thất bại
                            Toast.makeText(getContext(), "Thêm Loại Sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}