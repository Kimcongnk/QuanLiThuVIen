package ph29875.fpoly.quanlithuvienDuAnMau;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiLoaiSach#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiLoaiSach extends Fragment {


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_li_loai_sach, container, false);
    }
}