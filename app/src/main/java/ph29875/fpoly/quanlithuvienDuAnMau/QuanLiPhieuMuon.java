package ph29875.fpoly.quanlithuvienDuAnMau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiPhieuMuon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiPhieuMuon extends Fragment {


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
        return inflater.inflate(R.layout.fragment_quan_li_phieu_muon, container, false);
    }
}