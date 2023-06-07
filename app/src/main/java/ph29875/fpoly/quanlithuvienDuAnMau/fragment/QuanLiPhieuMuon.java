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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.PhieuMuonAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.PhieuMuonDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.SachDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThanhVienDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.PhieuMuon;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
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
    public void onResume() {
        super.onResume();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        phieuMuonDao.open();
        phieuMuonArrayList = (ArrayList<PhieuMuon>) phieuMuonDao.getAllPhieuMuon();
        // Create and set the adapter
        phieuMuonAdapter = new PhieuMuonAdapter(getContext(), phieuMuonArrayList);
        recyclerView.setAdapter(phieuMuonAdapter);
        phieuMuonAdapter.setImageViewClickListener(new PhieuMuonAdapter.ImageViewClickListener() {
            @Override
            public void onImageViewClick(int position) {
              showUpdatePhieuMuonDialog(position);
            }

            @Override
            public void xoa(int position) {

                PhieuMuon phieuMuon = phieuMuonArrayList.get(position);
                int deletedRows = phieuMuonDao.deletePhieuMuon(phieuMuon);
                if (deletedRows > 0) {
                    phieuMuonArrayList.remove(position);
                    phieuMuonAdapter.notifyItemRemoved(position);
                    Toast.makeText(getContext(), "PhieuMuon deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to delete PhieuMuon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.recyclerView);
        phieuMuonDao = new PhieuMuonDao(getContext());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPhieuMuonDialog() ;
            }
        });
        return view;
    }
    public void showAddPhieuMuonDialog() {
        // Inflate the dialog layout XML file
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_phieumuon, null);

        // Find the EditText views in the dialog
        Spinner spinner = dialogView.findViewById(R.id.etMaTV);
        Spinner  spinner1 = dialogView.findViewById(R.id.etMaSach);
        EditText etTienThue = dialogView.findViewById(R.id.etTienThue);
        EditText etNgay = dialogView.findViewById(R.id.etNgay);
        EditText etTraSach = dialogView.findViewById(R.id.etTraSach);

        final ThanhVienDao dao = new ThanhVienDao(getContext());
        dao.open();
        final List<ThanhVien> listLoai = dao.getAllThanhVien();
        List<String> listTenLoai = new ArrayList<>();
        for (ThanhVien loai : listLoai) {
            listTenLoai.add(loai.getHoTen());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, listTenLoai);
        spinner.setAdapter(adapter);

        final SachDao sachDao = new SachDao(getContext());
        final List<Sach> loaiSachList = sachDao.getAllSach();
        List<String> listTen = new ArrayList<>();
        for (Sach sach : loaiSachList) {
            listTen.add(sach.getTenSach());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, listTen);
        spinner1.setAdapter(adapter1);
        // Build the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("Add PhieuMuon");
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               PhieuMuon phieuMuon = new PhieuMuon();
                int pos = spinner.getSelectedItemPosition();
                phieuMuon.setMaTV(listLoai.get(pos).getHoTen());
//                phieuMuon.setMaSach(Integer.parseInt(etMaSach.getText().toString()));
                int pos1 = spinner1.getSelectedItemPosition();
                phieuMuon.setMaSach(loaiSachList.get(pos1).getTenSach());
                phieuMuon.setTienThue(Integer.parseInt(etTienThue.getText().toString()));
               phieuMuon.setNgay(etNgay.getText().toString());
                phieuMuon.setTraSach(etTraSach.getText().toString());

                // Create a PhieuMuon object with the input values


                // Insert the PhieuMuon object into the database
                if (phieuMuonDao.insertPhieuMuon(phieuMuon) > 0) {
                    onResume();
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
    public void showUpdatePhieuMuonDialog(int position) {
        // Inflate the dialog layout XML file
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_phieumuon, null);

        // Find the EditText views in the dialog
        Spinner spinner = dialogView.findViewById(R.id.etMaTV);
        Spinner  spinner1 = dialogView.findViewById(R.id.etMaSach);
        EditText etTienThue = dialogView.findViewById(R.id.etTienThue);
        EditText etNgay = dialogView.findViewById(R.id.etNgay);
        EditText etTraSach = dialogView.findViewById(R.id.etTraSach);

        final ThanhVienDao dao = new ThanhVienDao(getContext());
        dao.open();
        final List<ThanhVien> listLoai = dao.getAllThanhVien();
        List<String> listTenLoai = new ArrayList<>();
        for (ThanhVien loai : listLoai) {
            listTenLoai.add(loai.getHoTen());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, listTenLoai);
        spinner.setAdapter(adapter);

        final SachDao sachDao = new SachDao(getContext());
        final List<Sach> loaiSachList = sachDao.getAllSach();
        List<String> listTen = new ArrayList<>();
        for (Sach sach : loaiSachList) {
            listTen.add(sach.getTenSach());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, listTen);
        spinner1.setAdapter(adapter1);
        // Build the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("Add PhieuMuon");
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhieuMuon phieuMuon = phieuMuonArrayList.get(position);

                int pos = spinner.getSelectedItemPosition();
                phieuMuon.setMaTV(listLoai.get(pos).getHoTen());
//                phieuMuon.setMaSach(Integer.parseInt(etMaSach.getText().toString()));
                int pos1 = spinner1.getSelectedItemPosition();
                phieuMuon.setMaSach(loaiSachList.get(pos1).getTenSach());
                phieuMuon.setTienThue(Integer.parseInt(etTienThue.getText().toString()));
                phieuMuon.setNgay(etNgay.getText().toString());
                phieuMuon.setTraSach(etTraSach.getText().toString());

                // Create a PhieuMuon object with the input values


                // Insert the PhieuMuon object into the database
                if (phieuMuonDao.updatePhieuMuon(phieuMuon) > 0) {
                    onResume();
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