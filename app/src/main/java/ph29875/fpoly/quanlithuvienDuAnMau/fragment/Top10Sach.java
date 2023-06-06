package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph29875.fpoly.quanlithuvienDuAnMau.Adapter.AdapterTopSach;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThongKeDAO;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Top10Sach#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Top10Sach extends Fragment {

    AdapterTopSach adapterTopSach;
    ArrayList<Sach> listtop;
    RecyclerView recyclerView;
    ThongKeDAO thongKeDAO;
    public Top10Sach() {
        // Required empty public constructor
    }

    public static Top10Sach newInstance() {
Top10Sach quanLiPhieuMuon = new Top10Sach();

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
        View view = inflater.inflate(R.layout.fragment_top10_sach, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        thongKeDAO = new ThongKeDAO(getContext());
        listtop = (ArrayList<Sach>) thongKeDAO.getTop();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapterTopSach = new AdapterTopSach(getContext(),listtop);
        recyclerView.setAdapter(adapterTopSach);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
}