package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.PhieuMuonAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.ThanhVienAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.PhieuMuonDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThanhVienDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.PhieuMuon;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThanhVien;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

public class QuanLiPhieuMuon extends Fragment {
private PhieuMuonDao phieuMuonDao;
private RecyclerView recyclerView;

   private PhieuMuonAdapter phieuMuonAdapter;
   private ArrayList<PhieuMuon> phieuMuonArrayList = new ArrayList<>();

    public QuanLiPhieuMuon() {
        // Required empty public constructor
    }


    public static QuanLiPhieuMuon newInstance() {
        QuanLiPhieuMuon quanLiPhieuMuon = new QuanLiPhieuMuon();

        return quanLiPhieuMuon;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        phieuMuonDao = new PhieuMuonDao(getContext());


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
phieuMuonDao.open();


        phieuMuonArrayList = (ArrayList<PhieuMuon>) phieuMuonDao.getAllPhieuMuon();

        // Create and set the adapter
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(), phieuMuonArrayList);
        recyclerView.setAdapter(phieuMuonAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPhieuMuonDialog();
            }
        });
        return view;
    }
    public void showAddPhieuMuonDialog() {
        // Inflate the dialog layout XML file
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_phieumuon, null);

        // Find the EditText views in the dialog
        EditText etMaTV = dialogView.findViewById(R.id.etMaTV);
        EditText etMaSach = dialogView.findViewById(R.id.etMaSach);
        EditText etTienThue = dialogView.findViewById(R.id.etTienThue);
        EditText etNgay = dialogView.findViewById(R.id.etNgay);
        EditText etTraSach = dialogView.findViewById(R.id.etTraSach);

        // Build the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("Add PhieuMuon");
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMaPM(Integer.parseInt(etMaTV.getText().toString()));
                phieuMuon.setMaSach(Integer.parseInt(etMaSach.getText().toString()));
                phieuMuon.setTienThue(Integer.parseInt(etTienThue.getText().toString()));
               phieuMuon.setNgay(etNgay.getText().toString());
                phieuMuon.setTraSach(Integer.parseInt(etTraSach.getText().toString()));

                // Create a PhieuMuon object with the input values


                // Insert the PhieuMuon object into the database
                if (phieuMuonDao.insertPhieuMuon(phieuMuon) > 0) {
                    Toast.makeText(getContext(), "PhieuMuon added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to add PhieuMuon", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", null);

        // Show the dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}