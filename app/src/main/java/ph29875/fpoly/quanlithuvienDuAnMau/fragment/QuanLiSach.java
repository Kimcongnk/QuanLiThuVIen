package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xml.sax.Parser;

import java.util.ArrayList;

import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.PhieuMuonAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.SachAdapter;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.SachDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.PhieuMuon;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiSach#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiSach extends Fragment {
private    SachDao sachDao;
private ArrayList<Sach> sachArrayList = new ArrayList<>();
private SachAdapter sachAdapter;

    public QuanLiSach() {
        // Required empty public constructor
    }

    public static QuanLiSach newInstance() {
QuanLiSach quanLiPhieuMuon = new QuanLiSach();

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
        View view = inflater.inflate(R.layout.fragment_quan_li_sach, container, false);
        FloatingActionButton floatingActionButton= view.findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        sachDao = new SachDao(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);


        sachArrayList = (ArrayList<Sach>) sachDao.getAllSach();

        // Create and set the adapter
        sachAdapter = new SachAdapter(sachArrayList, getContext());
        recyclerView.setAdapter(sachAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSachDialog();
            }
        });
        return view;
    }
    public void AddSachDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_sach, null);
        dialogBuilder.setView(dialogView);

        EditText editTextTenSach = dialogView.findViewById(R.id.editTextTenSach);
        EditText editTextGiaThue = dialogView.findViewById(R.id.editTextGiaThue);
        EditText editTextMaLoai = dialogView.findViewById(R.id.editTextMaLoai);


        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sach sach = new Sach();
                sach.setTenSach(editTextTenSach.getText().toString());
               sach.setGiaThue(Integer.parseInt(editTextGiaThue.getText().toString()));
                sach.setMaLoai(Integer.parseInt(editTextMaLoai.getText().toString()));
                Toast.makeText(getContext(), ""+ sach,Toast.LENGTH_SHORT).show();
                    if (sachDao.addSach(sach) > 0) {
                        Toast.makeText(getContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        // Refresh the book list if needed
                        // refreshBookList();
                    } else {
                        Toast.makeText(getContext(), "Failed to add book", Toast.LENGTH_SHORT).show();
                    }
                }

        });

        dialogBuilder.setNegativeButton("Cancel", null);
        AlertDialog dialog = dialogBuilder.create();

        // Show the dialog

        dialog.show();
    }


}
