package ph29875.fpoly.quanlithuvienDuAnMau;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiThanhVien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiThanhVien extends Fragment {


    public QuanLiThanhVien() {
        // Required empty public constructor
    }

    public static QuanLiThanhVien newInstance() {
QuanLiThanhVien quanLiPhieuMuon = new QuanLiThanhVien();

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
        return inflater.inflate(R.layout.fragment_quan_li_thanh_vien, container, false);
    }
}