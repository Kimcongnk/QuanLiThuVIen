package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
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
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.ThanhVienAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThanhVien;
import ph29875.fpoly.quanlithuvienDuAnMau.R;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThanhVienDao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiThanhVien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiThanhVien extends Fragment {
    private RecyclerView recyclerView;
    private ThanhVienAdapter thanhVienAdapter;
    private ArrayList<ThanhVien> thanhVienList = new ArrayList<>();

private ThanhVienDao thanhVienDao ;

    public QuanLiThanhVien() {
        // Required empty public constructor
    }

    public static QuanLiThanhVien newInstance() {
QuanLiThanhVien quanLiThanhVien = new QuanLiThanhVien();

        return quanLiThanhVien;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_li_thanh_vien, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);
        thanhVienDao = new ThanhVienDao(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        thanhVienDao.open(); // Open the database connection

        thanhVienList = (ArrayList<ThanhVien>) thanhVienDao.getAllThanhVien();

        // Create and set the adapter
        thanhVienAdapter = new ThanhVienAdapter(getContext(), thanhVienList);
        recyclerView.setAdapter(thanhVienAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertThanhVien();
            }
        });

        return view;
    }

    public void insertThanhVien() {
        // Inflate the dialog layout XML file
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_thanhvien, null);

        // Find the EditText views in the dialog
        EditText editTextHoTen = dialogView.findViewById(R.id.editTextHoTen);
        EditText editTextNamSinh = dialogView.findViewById(R.id.editTextNamSinh);



        // Build the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("Add Member");
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the input values
                ThanhVien thanhVien = new ThanhVien();

                thanhVien.setHoTen(editTextHoTen.getText().toString());
                thanhVien.setNamSinh(editTextNamSinh.getText().toString());

                // Create a ThanhVien object with the input values


                // Insert the ThanhVien object into the database
                if (thanhVienDao.insertThanhVien(thanhVien) > 0)
                {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", null);

        // Show the dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}